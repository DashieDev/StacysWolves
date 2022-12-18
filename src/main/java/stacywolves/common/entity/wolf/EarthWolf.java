package stacywolves.common.entity.wolf;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import stacywolves.ChopinLogger;


public class EarthWolf extends BaseWolf {
   public EarthWolf(EntityType<EarthWolf> p_30369_, Level p_30370_) {
    super(p_30369_, p_30370_);
    }

    @Override
    public String getResourceName() {
   
      return "earth";
   }
}
