package stacywolves.common.entity.wolf;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class ChopinWolf extends BaseWolf {
    public ChopinWolf(EntityType<ChopinWolf> p_30369_, Level p_30370_) {
        super(p_30369_, p_30370_);
        }
    
        @Override
        public String getResourceName() {
     
          return "chopin";
       }
       
       @Override
       public Boolean isTameItem(Item x) {
          return x == Items.BONE;
      }
}
