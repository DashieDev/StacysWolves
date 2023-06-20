package stacywolves.common.entity.ai.ender_wolf;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;
import stacywolves.ChopinLogger;
import stacywolves.common.entity.wolf.EnderWolf;

public class EnderWolfRandomTeleportGoal extends Goal {
    
    private EnderWolf wolf;
    private int ticksTillCanUseCheck;
    private Vec3 toTeleport;

    public EnderWolfRandomTeleportGoal(EnderWolf wolf) {
        this.wolf = wolf;
    }

    @Override
    public boolean canUse() {
        if (--ticksTillCanUseCheck > 0) return false;
        
        ticksTillCanUseCheck = 60 + this.wolf.getRandom().nextInt(16)*20; // from 3 to 16 seconds.
        
        //if (this.wolf.getRandom().nextInt(4) != 0) return false; // 1/3 chance
        if (this.wolf.isInSittingPose()) return false;
        if (this.wolf.getOwner() == null) return false;
        this.toTeleport = LandRandomPos.getPos(this.wolf, 15, 7);
        if (toTeleport == null) return false;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return false;        
    }

    @Override
    public void start() {
        // if (this.toTeleport == null) return;
        // var target = new BlockPos(toTeleport);
        // if (!DogUtil.wantToTeleportToThePosition(wolf, target)) return;
        // wolf.moveTo(target.getX() + 0.5F, target.getY(), target.getZ() + 0.5F, wolf.getYRot(), wolf.getXRot());
    }

}
