package stacywolves.common.entity.wolf;

import java.util.Map;

import com.mojang.math.Vector3d;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import stacywolves.common.StacyWolvesLighting;
import stacywolves.common.utils.Maths;

public class TorchWolf extends BaseWolf {
    
    BlockPos lightingPos;

    BlockState oldBlockState;

    public TorchWolf(EntityType<TorchWolf> p_30369_, Level p_30370_) {
        super(p_30369_, p_30370_);
     }

    @Override
    public void tick() {
        super.tick();
        

        //Light
        BlockPos p = this.blockPosition();

        // Add light where the dog goes to
        if (!p.equals(this.lightingPos) && this.level.getBlockState(p).getBlock() instanceof AirBlock) { 
            this.addLight(p); //add light into new block
            if (this.lightingPos != null) this.removeLight(this.lightingPos); //remove old block
            this.lightingPos = new BlockPos(p.getX(), p.getY(), p.getZ());//update coord
        }

        if (this.lightingPos != null && this.distanceToSqr(Vec3.atBottomCenterOf(this.lightingPos)) > 9) {
            this.removeLight(this.lightingPos); 
        }


        //Smoke
        if (this.level.isClientSide && this.random.nextInt(20) == 9) {
            this.generateTorchParticles();
        }

    }

    @Override
    public String getResourceName() {
        return "torch";
    }

    public void addLight(BlockPos b) {
        this.oldBlockState = this.level.getBlockState(b);
        this.level.setBlock(b, Blocks.LIGHT.defaultBlockState(), 3);
    }

    public void removeLight(BlockPos b) {
        if (this.oldBlockState != null) {
            this.level.setBlock(b, this.oldBlockState, 3);
        } else {
            this.level.setBlock(b, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    protected void generateTorchParticles() {
		for (int count = 0; count < 2; ++count) {
			final float rotationOffset = 0; //8f;

			final Vector3d facing = Maths.getFacing(this.yBodyRot + rotationOffset, this.getXRot());
			final double particleX = this.getX() + facing.x * 0.4;
			final double particleY = this.getY() + (this.random.nextDouble() * 0.2) + (double) this.getBbHeight() + 0.2;
			final double particleZ = this.getZ() + facing.z * 0.4;
			this.level.addParticle(ParticleTypes.SMOKE, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
		}
	}

    

    @Override
    public void onRemovedFromWorld() {
        this.removeLight(this.lightingPos);
        super.onRemovedFromWorld();
    }

    @Override
    public void die(DamageSource x) {
        this.removeLight(this.lightingPos);
        super.die(x);
    }



}
