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
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.eventbus.api.Event;
import stacywolves.common.entity.ai.base.*;
import stacywolves.common.entity.ai.fire_wolf.FireWolfFollowOwnerGoal;
import stacywolves.common.entity.ai.fire_wolf.FireWolfGoAwayFromWaterGoal;
import stacywolves.ChopinLogger;

public class FireWolf extends BaseWolf {
    

    public FireWolf(EntityType<FireWolf> p_30369_, Level p_30370_) {
        super(p_30369_, p_30370_);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 8);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8);
    }

    @Override
    protected void registerGoals() {
        int p = 1;
          this.goalSelector.addGoal(p, new DogFloatGoal(this));
          //this.goalSelector.addGoal(1, new PatrolAreaGoal(this));
          ++p;
          this.goalSelector.addGoal(p, new FireWolfGoAwayFromWaterGoal(this));
          ++p;
          this.goalSelector.addGoal(p, new SitWhenOrderedToGoal(this));
        //   ++p;
        //   this.goalSelector.addGoal(p, new DogHungryGoal(this, 1.0f, 2.0f));
          ++p;
          this.goalSelector.addGoal(p, new DogLowHealthGoal(this, 1.0f, 2.0f));
          //this.goalSelector.addGoal(4, new DogLeapAtTargetGoal(this, 0.4F));
          ++p;
          // TODO this.goalSelector.addGoal(p, new DogEatFromChestDogGoal(this, 1.0));
          this.goalSelector.addGoal(p, new DogMeleeAttackGoal(this, 1.0D, true, 20, 40));
          ++p;
          this.goalSelector.addGoal(p, new FireWolfFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
          ++p;
          this.goalSelector.addGoal(p, new BreedGoal(this, 1.0D));
          ++p;
          this.goalSelector.addGoal(p, new DogRandomStrollGoal(this, 1.0D));
          ++p;
          this.goalSelector.addGoal(p, new DogBegGoal(this, 8.0F));
          ++p;
          this.goalSelector.addGoal(p, new LookAtPlayerGoal(this, Player.class, 8.0F));
          this.goalSelector.addGoal(p, new RandomLookAroundGoal(this));
  
  
  
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        //this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        //this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
     }

    // @Override
    // public float getBrightness() {
    //     return 1.0F;
    // }

    // @Override
    // private SoundEvent getFallDamageSound(int p_21313_) {
    //     return p_21313_ > 4 ? this.getFallSounds().big() : this.getFallSounds().small();
    // }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            this.hurtIfWet();
            this.healIfInFire();
            
        } else { 
            for (int count = 0; count < 2; ++count) {
                this.level.addParticle(ParticleTypes.SMOKE,
                        this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(),
                        this.getY() + this.random.nextDouble() * (double) this.getBbHeight() + 0.3,
                        this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() * 3, 0.0D, 0.0D, 0.0D);
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
        return false;
    }

    @Override
    public boolean canWetLava() {
        return true;
    }

    @Override
    public void furtherAffectTarget(Entity x) {
        x.setSecondsOnFire(3);
    }

    @Override
    public String getResourceName() {
        
        return "fire";
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        
        return false;
     }

    @Override
    public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
        return 10F;
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

}
