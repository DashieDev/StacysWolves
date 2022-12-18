package stacywolves.common.entity.ai.fire_wolf;

import stacywolves.common.entity.ai.base.DogFollowOwnerGoal;
import stacywolves.common.entity.wolf.BaseWolf;

public class FireWolfFollowOwnerGoal extends DogFollowOwnerGoal {

    public FireWolfFollowOwnerGoal(BaseWolf dogIn, double speedIn, float minDistIn, float maxDistIn) {
        super(dogIn, speedIn, minDistIn, maxDistIn);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.owner = null;
        this.dog.getNavigation().stop();
    }
}
