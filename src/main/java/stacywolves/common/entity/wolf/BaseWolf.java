package stacywolves.common.entity.wolf;

import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BegGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import stacywolves.ChopinLogger;
import stacywolves.common.entity.ai.base.WolfBegGoal;
import stacywolves.common.item.StacyBoneItem;


public abstract class BaseWolf extends TamableAnimal implements NeutralMob {
   private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID = SynchedEntityData.defineId(BaseWolf.class, EntityDataSerializers.BOOLEAN);
   private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(BaseWolf.class, EntityDataSerializers.INT);
   private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(BaseWolf.class, EntityDataSerializers.INT);
   public static final Predicate<LivingEntity> PREY_SELECTOR = (p_30437_) -> {
      EntityType<?> entitytype = p_30437_.getType();
      return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
   };
   private static final float START_HEALTH = 8.0F;
   private static final float TAME_HEALTH = 20.0F;
   private float interestedAngle;
   private float interestedAngleO;
   private boolean isWet;
   private boolean isWetLava;
   private boolean isShaking;
   private boolean shakeFire = false;
   private float shakeAnim;
   private float shakeAnimO;
   private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
   @Nullable
   private UUID persistentAngerTarget;

   public BaseWolf(EntityType<? extends BaseWolf> p_30369_, Level p_30370_) {
      super(p_30369_, p_30370_);
      this.setTame(false);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
      this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(9, new WolfBegGoal(this, 8.0F));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
      this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
      this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
      this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
      this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 2.0D);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_INTERESTED_ID, false);
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
   }

   protected void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
      this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
   }

   public void addAdditionalSaveData(CompoundTag p_30418_) {
      super.addAdditionalSaveData(p_30418_);
      p_30418_.putByte("CollarColor", (byte)this.getCollarColor().getId());
      this.addPersistentAngerSaveData(p_30418_);
   }

   public void readAdditionalSaveData(CompoundTag p_30402_) {
      super.readAdditionalSaveData(p_30402_);
      if (p_30402_.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(p_30402_.getInt("CollarColor")));
      }

      this.readPersistentAngerSaveData(this.level, p_30402_);
   }

   protected SoundEvent getAmbientSound() {
      //Disable this to not interfere with Creeper Detecting Wolves
      // if (this.isAngry()) {
      //    return SoundEvents.WOLF_GROWL;
      // } 
      if (this.random.nextInt(3) == 0) {
         return this.isTame() && this.getHealth() < 10.0F ? SoundEvents.WOLF_WHINE : SoundEvents.WOLF_PANT;
      } else {
         return SoundEvents.WOLF_AMBIENT;
      }
   }

   protected SoundEvent getHurtSound(DamageSource p_30424_) {
      return SoundEvents.WOLF_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.WOLF_DEATH;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   public void aiStep() {
      super.aiStep();
      if (!this.level.isClientSide && this.canNaturallyShake(false)) {
         this.startShakingAndBroadcast(false);
      }

      if (!this.level.isClientSide && this.canNaturallyShake(true)) {
         this.startShakingAndBroadcast(true);
      }

      if (!this.level.isClientSide) {
         this.updatePersistentAnger((ServerLevel)this.level, true);
      }

   }

   public void tick() {
      super.tick();
      if (this.isAlive()) {
         this.interestedAngleO = this.interestedAngle;
         if (this.isInterested()) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
         } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
         }

         if (this.isInWaterRainOrBubble() && this.canWet()) {
            this.isWet = true;
            //When a wolf is shaking and got pushed into water, he will stop immediately.
            if (this.isShaking && !this.level.isClientSide) {
               this.level.broadcastEntityEvent(this, (byte)56);
               this.finishShaking();
            }
         } else if (this.isInLava() && this.canWetLava()) {
            this.isWetLava = true; 
            //When a wolf is shaking and got pushed into lava, he will stop immediately.
            if (this.isShaking && !this.level.isClientSide) {
               this.level.broadcastEntityEvent(this, (byte)56);
               this.finishShaking();
            }
         } else if (this.isShaking) {
            if (this.shakeAnim == 0.0F) {
               if (!shakeFire) this.playSound(SoundEvents.WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
               this.gameEvent(GameEvent.ENTITY_SHAKE);
            }

            this.shakeAnimO = this.shakeAnim;
            this.shakeAnim += 0.05F;
            if (this.shakeAnimO >= 2.0F) {
               this.isWet = false;
               this.isWetLava = false;
               this.finishShaking();
            }

            if (this.shakeAnim > 0.4F) {
               float f = (float)this.getY();
               int i = (int)(Mth.sin((this.shakeAnim - 0.4F) * (float)Math.PI) * 7.0F);
               Vec3 vec3d = this.getDeltaMovement();

               for (int j = 0; j < i; ++j) {
                  float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                  float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getBbWidth() * 0.5F;
                  if (this.shakeFire) {
                      byte r = (byte) this.getRandom().nextInt(3);
                      if (r==0)
                          this.level.addParticle(ParticleTypes.LAVA, this.getX() + f1, f + 0.8F, this.getZ() + f2, vec3d.x, vec3d.y, vec3d.z);
                      else if (r==1)
                          this.level.addParticle(ParticleTypes.FLAME, this.getX() + f1, f + 0.8F, this.getZ() + f2, vec3d.x, vec3d.y, vec3d.z);
                      else if (r==2)
                          this.level.addParticle(ParticleTypes.SMOKE, this.getX() + f1, f + 0.8F, this.getZ() + f2, vec3d.x, vec3d.y, vec3d.z);
                  } else
                  this.level.addParticle(ParticleTypes.SPLASH, this.getX() + f1, f + 0.8F, this.getZ() + f2, vec3d.x, vec3d.y, vec3d.z);
              }

               if (this.shakeAnim > 0.8) {
                     if (this.shakeFire) this.playSound(SoundEvents.FIRE_EXTINGUISH, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
               }  
            }
         }

      }
   }

   public void startShakingAndBroadcast(boolean shakeFire) {
      if (this.isShaking) return; //Already shaking
      if (this.level.isClientSide) return;
      if (shakeFire && this.canWetLava()) {
         this.startShakingLava();
         return;
      }
      if (!this.canWet()) return;
      this.startShaking();
      this.level.broadcastEntityEvent(this, (byte)8);
   }

   private void startShaking() {
      if (this.isShaking) return; // don't shake if already shaking
      this.isShaking = true;
      this.shakeFire = false;
      this.shakeAnim = 0.0F;
      this.shakeAnimO = 0.0F;
   }

   public void startShakingLava() {
      if (this.isShaking) return; // don't shake if already shaking
      this.isShaking = true;
      this.shakeFire = true;
      this.shakeAnim = 0.0F;
      this.shakeAnimO = 0.0F;
   }

   private void finishShaking() {
      this.isShaking = false;
      this.shakeFire = false;
      this.shakeAnim = 0.0F;
      this.shakeAnimO = 0.0F;
   }

   public boolean canNaturallyShake(boolean shakeFire) {
      boolean flag = shakeFire? 
         this.isWetLava : this.isWet;

      return flag && !this.isShaking && !this.isPathFinding() && this.onGround;
   }

   public void die(DamageSource p_30384_) {
      this.isWet = false;
      this.isWetLava = false;
      this.isShaking = false;
      this.shakeAnimO = 0.0F;
      this.shakeAnim = 0.0F;
      super.die(p_30384_);
   }

   public boolean isWet() {
      return this.isWet;
   }

   public float getWetShade(float p_30447_) {
      return Math.min(0.5F + Mth.lerp(p_30447_, this.shakeAnimO, this.shakeAnim) / 2.0F * 0.5F, 1.0F);
   }

   public float getBodyRollAngle(float p_30433_, float p_30434_) {
      float f = (Mth.lerp(p_30433_, this.shakeAnimO, this.shakeAnim) + p_30434_) / 1.8F;
      if (f < 0.0F) {
         f = 0.0F;
      } else if (f > 1.0F) {
         f = 1.0F;
      }

      return Mth.sin(f * (float)Math.PI) * Mth.sin(f * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
   }

   public float getHeadRollAngle(float p_30449_) {
      return Mth.lerp(p_30449_, this.interestedAngleO, this.interestedAngle) * 0.15F * (float)Math.PI;
   }

   protected float getStandingEyeHeight(Pose p_30409_, EntityDimensions p_30410_) {
      return p_30410_.height * 0.8F;
   }

   public int getMaxHeadXRot() {
      return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
   }

   public boolean hurt(DamageSource p_30386_, float p_30387_) {
      if (this.isInvulnerableTo(p_30386_)) {
         return false;
      } else {
         Entity entity = p_30386_.getEntity();
         this.setOrderedToSit(false);
         if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            p_30387_ = (p_30387_ + 1.0F) / 2.0F;
         }

         return super.hurt(p_30386_, p_30387_);
      }
   }

   public boolean doHurtTarget(Entity p_30372_) {
      boolean flag = p_30372_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
      if (flag) {
         this.doEnchantDamageEffects(this, p_30372_);
      }

      this.furtherAffectTarget(p_30372_);

      return flag;
   }

   public void setTame(boolean p_30443_) {
      super.setTame(p_30443_);
      if (p_30443_) {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
   }

   public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
      ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
      Item item = itemstack.getItem();
      if (this.level.isClientSide) {
         boolean flag = this.isOwnedBy(p_30412_) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
         return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
      } else {
         if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
               if (!p_30412_.getAbilities().instabuild) {
                  itemstack.shrink(1);
               }

               this.heal(this.getHealValue(itemstack));
               return InteractionResult.SUCCESS;
            }

            if (!(item instanceof DyeItem)) {
               InteractionResult interactionresult = super.mobInteract(p_30412_, p_30413_);
               if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(p_30412_)) {
                  this.setOrderedToSit(!this.isOrderedToSit());
                  this.jumping = false;
                  this.navigation.stop();
                  this.setTarget((LivingEntity)null);
                  return InteractionResult.SUCCESS;
               }

               return interactionresult;
            }

            DyeColor dyecolor = ((DyeItem)item).getDyeColor();
            if (dyecolor != this.getCollarColor()) {
               this.setCollarColor(dyecolor);
               if (!p_30412_.getAbilities().instabuild) {
                  itemstack.shrink(1);
               }

               return InteractionResult.SUCCESS;
            }
         } else if (this.isTameItem(itemstack.getItem()) && !this.isAngry()) {
            if (!p_30412_.getAbilities().instabuild) {
               itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_30412_)) {
               this.tame(p_30412_);
               this.navigation.stop();
               this.setTarget((LivingEntity)null);
               this.setOrderedToSit(true);
               this.level.broadcastEntityEvent(this, (byte)7);
            } else {
               this.level.broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
         }

         //No breed using food 
         return InteractionResult.PASS;
      }
   }
   public void handleEntityEvent(byte p_30379_) {
      if (p_30379_ == 8) {
         this.isShaking = true;
         this.shakeAnim = 0.0F;
         this.shakeAnimO = 0.0F;
      } else if (p_30379_ == 56) {
         this.finishShaking();
      } else {
         super.handleEntityEvent(p_30379_);
      }

   }

   public float getTailAngle() {
      if (this.isAngry()) {
         return 1.5393804F;
      } else {
         return this.isTame() ? (0.55F - (this.getMaxHealth() - this.getHealth()) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F);
      }
   }

   public boolean isFood(ItemStack p_30440_) {
      Item item = p_30440_.getItem();
      return item.isEdible() && item.getFoodProperties().isMeat();
   }

   public float getHealValue(ItemStack stack) {
      return (float)stack.getItem().getFoodProperties().getNutrition();
   }

   @Override
   public int getMaxFallDistance() {
      //There is no reason for wolves to hurt themselves.
      return 3;
   }

   public int getMaxSpawnClusterSize() {
      return 8;
   }

   public int getRemainingPersistentAngerTime() {
      return this.entityData.get(DATA_REMAINING_ANGER_TIME);
   }

   public void setRemainingPersistentAngerTime(int p_30404_) {
      this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
   }

   public void startPersistentAngerTimer() {
      this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
   }

   @Nullable
   public UUID getPersistentAngerTarget() {
      return this.persistentAngerTarget;
   }

   public void setPersistentAngerTarget(@Nullable UUID p_30400_) {
      this.persistentAngerTarget = p_30400_;
   }

   public DyeColor getCollarColor() {
      return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
   }

   public void setCollarColor(DyeColor p_30398_) {
      this.entityData.set(DATA_COLLAR_COLOR, p_30398_.getId());
   }

   //TODO Mate ?? 
   public Wolf getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
      Wolf wolf = EntityType.WOLF.create(p_149088_);
      UUID uuid = this.getOwnerUUID();
      if (uuid != null) {
         wolf.setOwnerUUID(uuid);
         wolf.setTame(true);
      }

      return wolf;
   }

   public void setIsInterested(boolean p_30445_) {
      this.entityData.set(DATA_INTERESTED_ID, p_30445_);
   }

   public boolean canMate(Animal animal) {
      if (animal == this) {
         return false;
      } else if (!this.isTame()) {
         return false;
      } else if ((animal.getClass() != this.getClass())) {
         return false;
      } else {
         BaseWolf wolf = (BaseWolf)animal;
         if (!wolf.isTame()) {
            return false;
         } else if (wolf.isInSittingPose()) {
            return false;
         } else {
            return this.isInLove() && wolf.isInLove();
         }
      }
   }

   public boolean isInterested() {
      return this.entityData.get(DATA_INTERESTED_ID);
   }

   public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
      if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
         if (p_30389_ instanceof Wolf) {
            Wolf wolf = (Wolf)p_30389_;
            return !wolf.isTame() || wolf.getOwner() != p_30390_;
         } else if (p_30389_ instanceof Player && p_30390_ instanceof Player && !((Player)p_30390_).canHarmPlayer((Player)p_30389_)) {
            return false;
         } else if (p_30389_ instanceof AbstractHorse && ((AbstractHorse)p_30389_).isTamed()) {
            return false;
         } else {
            return !(p_30389_ instanceof TamableAnimal) || !((TamableAnimal)p_30389_).isTame();
         }
      } else {
         return false;
      }
   }

   public boolean canBeLeashed(Player p_30396_) {
      return !this.isAngry() && super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public static boolean checkWolfSpawnRules(EntityType<? extends BaseWolf> p_186244_, ServerLevelAccessor p_186245_, MobSpawnType p_186246_, BlockPos p_186247_, RandomSource p_186248_) {
      return p_186245_.getBlockState(p_186247_.below()).is(BlockTags.WOLVES_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_186245_, p_186247_);
   }
   
   public boolean canWet() {
      return true;
   }

   public boolean canWetLava() {
      return false;
   }

   public Boolean isTameItem(Item x) {
      if (!(x instanceof StacyBoneItem)) return false;
      var bone = (StacyBoneItem)x;
      return bone.getTarget().isInstance(this);
   }

   public void furtherAffectTarget(Entity x) {

   }

   public abstract String getResourceName();
   
}
