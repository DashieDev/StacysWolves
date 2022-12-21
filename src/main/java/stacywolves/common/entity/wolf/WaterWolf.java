package stacywolves.common.entity.wolf;

import java.util.UUID;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import stacywolves.common.entity.ai.base.DogFloatGoal;
import stacywolves.common.entity.ai.water_wolf.SwimmerDogGoal;

public class WaterWolf extends BaseWolf {


   private static final UUID SWIM_BOOST_ID = UUID.fromString("70ff021b-df44-498f-98ec-8c8c789c86e5");

   private boolean isWolfSwimming = false;

   private PathNavigation defaultNavigation;
   private MoveControl defaultMoveControl;

   public WaterWolf(EntityType<WaterWolf> p_30369_, Level p_30370_) {
      super(p_30369_, p_30370_);
      var attribute = this.getAttribute(ForgeMod.SWIM_SPEED.get());

        //Check if attribute exist, if so then remove
        if (attribute != null && attribute.getModifier(SWIM_BOOST_ID) == null) {
            var newAttribute = new AttributeModifier(
               SWIM_BOOST_ID, "Water Wolf Swim Boost", 
               6, Operation.MULTIPLY_BASE
            );
         
            attribute.addTransientModifier(newAttribute);
        }
        this.defaultNavigation = this.navigation;
        this.defaultMoveControl = this.moveControl;
      
   }

   @Override
    protected void registerGoals() {
      super.registerGoals();
      
      //Replace DogFloatGoal with WaterWolfFloatGoal.
      var dogGoals = this.goalSelector.getAvailableGoals();
      Goal goalToRemove = null;
      for (var x : dogGoals) {
         if (x.getGoal() instanceof DogFloatGoal x1) {
            goalToRemove = x1;
         }
      }

      if (goalToRemove != null)
         this.goalSelector.removeGoal(goalToRemove);

      this.goalSelector.addGoal(1, new WaterWolfFloatGoal(this));

      this.goalSelector.addGoal(5, new SwimmerDogGoal(this));
    }

   @Override
   public String getResourceName() {
      return "water";
   }

   @Override
   public boolean canDrownInFluidType(FluidType type) {
      return false;
   }

   public void setWolfSwimming(boolean swimming) {
      this.isWolfSwimming = swimming;
   }

   public boolean getWolfSwimming() {
      return this.isWolfSwimming;
   }

    public void resetNavigation() {
        this.setNavigation(this.defaultNavigation);
    }

    public void resetMoveControl() {
        this.setMoveControl(this.defaultMoveControl);
    }

    public void setNavigation(PathNavigation p) {
      if (this.navigation == p) return;
      this.navigation.stop();
      this.navigation = p;
  }

  //TODO try to replicate the bug and check if moveControl.haveWantedPosition using debug magic
  public void setMoveControl(MoveControl m) {
      /*
       * Force the MoveControl To Reset :
       * this will set the dog's wanted Position to his current Position
       * which will cause the moveControl to halt movement and reset in the 
       * next tick(). 
       * And then immediately update the moveControl by calling tick() so 
       * that everything is resolved before anything else.
       */
      this.moveControl.setWantedPosition(
          this.getX(), 
          this.getY(), 
          this.getZ(), 1.0
      );
      this.moveControl.tick();

      //Also reset jump just to be sure.
      this.setJumping(false);

      //Also reset accelerations just to be sure.
      this.setSpeed(0.0F);
      this.setXxa(0.0F);
      this.setYya(0.0F);
      this.setZza(0.0F);

      this.moveControl = m;
  }

   public static class DogWaterBoundNavigation extends WaterBoundPathNavigation {

      public DogWaterBoundNavigation(WaterWolf p_26594_, Level p_26595_) {
         super(p_26594_, p_26595_);
      }

      @Override
      protected boolean canUpdatePath() {
         return this.isInLiquid();
      }

   }

   public class WaterWolfFloatGoal extends DogFloatGoal {

      private WaterWolf dog;
  
  
      public WaterWolfFloatGoal(WaterWolf dog) {
          super(dog);
          this.dog = dog;
      }
      
      @Override
      public boolean canUse() {
          return super.canUse() && !this.dog.getWolfSwimming();
      }
  
      
  }
  

}
