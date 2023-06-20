package stacywolves.common.entity.wolf;

import java.util.Random;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.jfr.event.ChunkGenerationEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.eventbus.api.Event;
import stacywolves.common.entity.ai.base.*;
import stacywolves.ChopinLogger;

public class FireWolf extends BaseWolf {
    

    public FireWolf(EntityType<FireWolf> p_30369_, Level p_30370_) {
        super(p_30369_, p_30370_);
    }

    // @Override
    // public float getBrightness() {
    //     return 1.0F;
    // }

    // @Override
    // private SoundEvent getFallDamageSound(int p_21313_) {
    //     return p_21313_ > 4 ? this.getFallSounds().big() : this.getFallSounds().small();
    // }

    public static boolean checkWolfSpawnRules(EntityType<? extends BaseWolf> p_186244_, ServerLevelAccessor p_186245_, MobSpawnType p_186246_, BlockPos p_186247_, RandomSource p_186248_) {
      return p_186245_.getBlockState(p_186247_.below()).is(BlockTags.WOLVES_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_186245_, p_186247_);
   }

    @Override
    public void tick() {
        if (!this.level.isClientSide && !this.isTame()) {
            this.hurtIfWet();
            //this.healIfInFire();
            
        } else { 
            for (int count = 0; count < 2; ++count) {
                this.level.addParticle(ParticleTypes.SMOKE,
                        this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(),
                        this.getY() + this.random.nextDouble() * (double) this.getBbHeight() + 0.3,
                        this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), 0.0D, 0.0D, 0.0D);
            }

            for (int count = 0; count < 2; count++) {
                this.level.addParticle(ParticleTypes.FLAME,
                        this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(),
                        this.getY() + this.random.nextDouble() * (double) this.getBbHeight() + 0.3,
                        this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), 0.0D, 0.0D, 0.0D);
            }

        } 

        super.tick();
    }

    @Override
    public boolean canWet() {
        return true;
    }

    @Override
    public boolean canWetLava() {
        return false;
    }

    @Override
    public void furtherAffectTarget(Entity x) {
        x.setSecondsOnFire(3);
    }

    @Override
    public String getResourceName() {
        
        return "fire";
    }

    int tickTillHurt = 0;
    protected void hurtIfWet() {
		if (/*!this.isTame() &&*/ this.isInWaterRainOrBubble() && --this.tickTillHurt <= 0)
		{
            this.tickTillHurt = 10;
			if (this.level instanceof ServerLevel) {
                ((ServerLevel) this.level).sendParticles(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY(), this.getZ(), 15, this.getBbWidth(), 0.8f, this.getBbWidth(), 0.3);
                this.playSound(SoundEvents.FIRE_EXTINGUISH,0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F);
            }

            this.hurt(DamageSource.DROWN, 1.0F);
            
            
            
		}
	}     

    int tickTillHeal = 0;
    protected void healIfInFire() {
        if (this.isInLava() && --this.tickTillHeal <= 0) {
            this.tickTillHeal = 5;
            this.heal(1);
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        if (stack.getItem() instanceof BucketItem) return false;
        if (net.minecraftforge.common.ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0) {
            return true;
        }
        return super.isFood(stack);
    }

    @Override
    public float getHealValue(ItemStack stack) {
        var burnValue = net.minecraftforge.common.ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
        if (burnValue > 0) return burnValue;
        return super.getHealValue(stack);
    }

}
