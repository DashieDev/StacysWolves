package stacywolves.common.entity.ai.base;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import stacywolves.common.entity.wolf.BaseWolf;
import stacywolves.common.utils.DogUtil;

import java.util.EnumSet;

public class DogFollowOwnerGoal extends Goal {

    protected final BaseWolf dog;
    protected final Level world;
    protected final double followSpeed;

    // If closer than stopDist stop moving towards owner
    protected final float stopDist; 
     // If further than startDist moving towards owner
    protected final float startDist;

    protected LivingEntity owner;
    protected int timeToRecalcPath;
    protected float oldWaterCost;

    public DogFollowOwnerGoal(BaseWolf dogIn, double speedIn, float minDistIn, float maxDistIn) {
        this.dog = dogIn;
        this.world = dogIn.level;
        this.followSpeed = speedIn;
        this.startDist = minDistIn;
        this.stopDist = maxDistIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = this.dog.getOwner();
        if (owner == null) {
            return false;
        } else if (owner.isSpectator()) {
            return false;
        } else if (this.dog.isInSittingPose()) {
            return false;
        } else if (this.dog.distanceToSqr(owner) < this.getMinStartDistanceSq()) {
            return false;
        } else {
            this.owner = owner;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.dog.getNavigation().isDone()) {
            return false;
        } else if (this.dog.isInSittingPose()) {
            return false;
        } else {
            return this.dog.distanceToSqr(this.owner) > this.stopDist * this.stopDist;
        }
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.dog.getPathfindingMalus(BlockPathTypes.WATER);
        this.dog.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.dog.getNavigation().stop();
        this.dog.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.dog.getLookControl().setLookAt(this.owner, 10.0F, this.dog.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.dog.isLeashed() && !this.dog.isPassenger()) {
                if (this.dog.distanceToSqr(this.owner) >= 144.0D) {
                    DogUtil.guessAndTryToTeleportToOwner(dog, 4);
                } else {
                    this.dog.getNavigation().moveTo(this.owner, this.followSpeed);
                }
            }
        }
    }

    public float getMinStartDistanceSq() {

        return this.startDist * this.startDist;
    }
}
