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
import stacywolves.common.StacyWolvesLighting;
import stacywolves.common.utils.Maths;

public class TorchWolf extends BaseWolf {
    
    BlockPos lightingPos;

    public TorchWolf(EntityType<TorchWolf> p_30369_, Level p_30370_) {
        super(p_30369_, p_30370_);
     }

    @Override
    public void tick() {
        super.tick();
        

        //Light
        BlockPos p = this.blockPosition();

        // Add light where the dog goes to
        if (!p.equals(this.lightingPos)) { 
            this.addLight(p); //add light into new block
            if (this.lightingPos != null) this.removeLight(this.lightingPos); //remove old block
            this.lightingPos = new BlockPos(p.getX(), p.getY(), p.getZ());//update coord
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
        for (var i : StacyWolvesLighting.lightSubstitudeMap.entrySet()) {
            if (this.level.getBlockState(b).getBlock() == i.getKey()) {
                this.level.setBlock(b, i.getValue().defaultBlockState(), 3);
            }
        }
    }

    public void removeLight(BlockPos b) {
        for (var i : StacyWolvesLighting.lightSubstitudeMap.entrySet()) {
            if (this.level.getBlockState(b).getBlock() == i.getValue()) {
                this.level.setBlock(b, i.getKey().defaultBlockState(), 3);
            }
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
