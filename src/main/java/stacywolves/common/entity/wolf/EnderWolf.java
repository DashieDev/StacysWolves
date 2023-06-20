package stacywolves.common.entity.wolf;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import stacywolves.common.entity.ai.ender_wolf.EnderWolfRandomTeleportGoal;

public class EnderWolf extends BaseWolf {
   public EnderWolf(EntityType<EnderWolf> p_30369_, Level p_30370_) {
      super(p_30369_, p_30370_);
   }

   @Override
   protected void registerGoals() {   
      super.registerGoals();
   }

   @Override
   public String getResourceName() {

      return "ender";
   }

   @Override
   public void tick() {
      if (!this.level.isClientSide) {
      } else {
         for (int count = 0; count < 2; ++count) {
            var x = this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth();
            var y = this.getY() + this.random.nextDouble() * (double) this.getBbHeight();// + 0.3;
            var z = this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth();
            var dx = this.getX() - x;
            var dy = this.getY() - y;
            var dz = this.getZ() - z;
            var scale = -0.4;
            this.level.addParticle(ParticleTypes.PORTAL,
                  x, y, z, dx * scale, dy * scale, dz * scale);
         }
      }

      super.tick();
   }

   @Override
   public void moveTo(double p_20108_, double p_20109_, double p_20110_, float p_20111_, float p_20112_) {
      this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0f, 1.0f);
      super.moveTo(p_20108_, p_20109_, p_20110_, p_20111_, p_20112_);
   }

}
