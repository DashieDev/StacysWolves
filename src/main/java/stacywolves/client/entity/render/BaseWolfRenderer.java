package stacywolves.client.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import stacywolves.client.ClientSetup;
import stacywolves.client.entity.model.BaseWolfModel;
import stacywolves.client.entity.render.layer.BaseWolfCollarLayer;
import stacywolves.common.entity.wolf.BaseWolf;
import stacywolves.common.entity.wolf.FireWolf;
import stacywolves.common.lib.Constants;

@OnlyIn(Dist.CLIENT)
public class BaseWolfRenderer<T extends BaseWolf> extends MobRenderer<T, BaseWolfModel<T>> {

   private BaseWolfRenderResourcePath PATHS;

   public BaseWolfRenderer(EntityRendererProvider.Context p_174452_) {
      super(p_174452_, new BaseWolfModel(p_174452_.bakeLayer(ClientSetup.BASE_WOLF)), 0.5F);
      this.addLayer(new BaseWolfCollarLayer<T>(this));
   }

   protected float getBob(T p_116528_, float p_116529_) {
      return p_116528_.getTailAngle();
   }

   public void render(T p_116531_, float p_116532_, float p_116533_, PoseStack p_116534_, MultiBufferSource p_116535_, int p_116536_) {
      if (p_116531_.isWet()) {
         float f = p_116531_.getWetShade(p_116533_);
         this.model.setColor(f, f, f);
      }

      super.render(p_116531_, p_116532_, p_116533_, p_116534_, p_116535_, p_116536_);
      if (p_116531_.isWet()) {
         this.model.setColor(1.0F, 1.0F, 1.0F);
      }

   }

   public ResourceLocation getTextureLocation(T wolf) {
      if (this.PATHS == null) this.PATHS = new BaseWolfRenderResourcePath(wolf.getResourceName());
      if (wolf.isTame()) {
         return this.PATHS.getTame();
      } else {
         return wolf.isAngry() ? this.PATHS.getAngry() : this.PATHS.getBase();
      }
   }
}