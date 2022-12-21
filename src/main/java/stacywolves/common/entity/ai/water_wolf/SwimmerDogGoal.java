package stacywolves.common.entity.ai.water_wolf;

import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraftforge.common.ForgeMod;
import stacywolves.common.entity.wolf.WaterWolf;

public class SwimmerDogGoal extends Goal {

    

    //TODO Something's not right, need to re look at the dog goal enable pattern while riding
    private WaterWolf dog;
    //private boolean isGettingAir = false;
    private SmoothSwimmingMoveControl moveControl;
    private WaterBoundPathNavigation navigator;
    private float oldWaterCost;
    private float oldWaterBorderCost;

    public SwimmerDogGoal(WaterWolf d) {
        this.dog = d;
        this.moveControl = new SmoothSwimmingMoveControl(d, d.getMaxHeadXRot(), d.getMaxHeadYRot(), 1, 1, false);
        this.navigator = new WaterWolf.DogWaterBoundNavigation(d, d.level);
    }                 
    
    @Override
    public boolean canUse() {
        return this.dog.isInWater() && !this.checkSurroundingForLand(this.dog, this.dog.blockPosition()) ;
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.dog.isInWater()) return false;
        if (this.checkSurroundingForLand(this.dog, this.dog.blockPosition())) return false;
        return true;
    }

    @Override
    public void start() {
        this.startSwimming();
    }

    @Override
    public void stop() {
        this.stopSwimming();
    }

    private boolean checkSurroundingForLand(WaterWolf dogIn, BlockPos p) {
        for (BlockPos dp : BlockPos.betweenClosed(p.offset(-1, -1, -1), p.offset(1, 1, 1))) {
            BlockPathTypes pn = WalkNodeEvaluator.getBlockPathTypeStatic(dogIn.level, dp.mutable());
            if (pn == BlockPathTypes.WALKABLE || pn == BlockPathTypes.WATER_BORDER) return true;
        }
        return false;
    }
    
    private void startSwimming() {
        this.dog.setJumping(false);
        this.dog.setNavigation(this.navigator);
        this.dog.setMoveControl(this.moveControl);
        if (this.dog.isInSittingPose()) { 
            this.dog.setInSittingPose(false);
        }
        this.oldWaterCost = this.dog.getPathfindingMalus(BlockPathTypes.WATER);
        this.oldWaterBorderCost = this.dog.getPathfindingMalus(BlockPathTypes.WATER_BORDER);
        this.dog.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0);
        this.dog.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.dog.setWolfSwimming(true);
    }

    private void stopSwimming() {
        this.dog.resetMoveControl();
        this.dog.resetNavigation();
        this.dog.setPathfindingMalus(BlockPathTypes.WATER_BORDER, this.oldWaterBorderCost);
        this.dog.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
        this.dog.setWolfSwimming(false);
    }
}
