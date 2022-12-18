package stacywolves.common.entity.wolf;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FlowerBlock;
import stacywolves.ChopinLogger;


public class FlowerWolf extends BaseWolf {
   public FlowerWolf(EntityType<FlowerWolf> p_30369_, Level p_30370_) {
    super(p_30369_, p_30370_);
    }

    @Override
    public String getResourceName() {
 
      return "flower";
   }

   public static boolean checkWolfSpawnRules(EntityType<? extends BaseWolf> dogIn, LevelAccessor level, MobSpawnType p_186246_, BlockPos blockpos, Random p_186248_) {
      if (p_186246_ == MobSpawnType.CHUNK_GENERATION) {
         boolean spn = false;
         for (BlockPos dp : BlockPos.betweenClosed(blockpos.offset(-3, -3, -3), blockpos.offset(3, 3, 3))) {
            if (level.hasChunk(dp.getX(), dp.getZ()) && level.getBlockState(dp).getBlock() instanceof FlowerBlock) {
               spn = true; break;
            }
         }
         if (!spn) return false;
         ChopinLogger.l("Succesfully spanwed :))) Flower Wolf");
      }
      
      return level.getBlockState(blockpos.below()).is(BlockTags.WOLVES_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, blockpos);
   }
}
