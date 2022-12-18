package stacywolves.common.entity.ai.base;


import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import stacywolves.common.entity.wolf.BaseWolf;

public class DogFloatGoal extends FloatGoal {

    private BaseWolf dog;


    public DogFloatGoal(BaseWolf dog) {
        super(dog);
        this.dog = dog;
    }
    
    @Override
    public boolean canUse() {
        return super.canUse();
    }

    
}
