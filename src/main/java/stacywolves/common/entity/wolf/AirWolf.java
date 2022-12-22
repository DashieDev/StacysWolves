package stacywolves.common.entity.wolf;

import java.util.function.Consumer;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;

public class AirWolf extends BaseWolf {
   public AirWolf(EntityType<AirWolf> p_30369_, Level p_30370_) {
      super(p_30369_, p_30370_);
      var attribute = this.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
      if (attribute != null)
         attribute.setBaseValue(0.005);
   }

   @Override
   public String getResourceName() {

      return "air";
   }

   @Override
   protected int calculateFallDamage(float p_21237_, float p_21238_) {
       return 0;
   }

   public static Consumer<Builder> getAddtionalAttributes() {
      return b -> {
        b.add(ForgeMod.ENTITY_GRAVITY.get(), 0.015); 
      };
   }
}
