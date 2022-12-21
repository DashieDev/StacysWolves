package stacywolves.common.entity.ai.base;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import stacywolves.common.entity.wolf.BaseWolf;

public class DogNearestToOwnerAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    
    private BaseWolf dog;
    
    public DogNearestToOwnerAttackableTargetGoal(BaseWolf dog, Class<T> type, boolean p_26062_) {
        super(dog, type ,p_26062_);
        this.dog = dog;
    }

    @Override
    protected void findTarget() {
        var owner = this.dog.getOwner();
        if (owner == null) return;
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
           this.target = owner.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> {
              return true;
           }), this.targetConditions, this.dog, owner.getX(), owner.getEyeY(), owner.getZ());
        } else {
           this.target = owner.level.getNearestPlayer(this.targetConditions, this.dog, owner.getX(), owner.getEyeY(), owner.getZ());
        }
  
    }
}
