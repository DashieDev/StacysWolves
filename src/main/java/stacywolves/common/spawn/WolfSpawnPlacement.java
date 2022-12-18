package stacywolves.common.spawn;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.Event;
import stacywolves.common.StacyWolvesEntities;
import stacywolves.common.entity.wolf.BirchWolf;
import stacywolves.common.entity.wolf.FireWolf;
import stacywolves.common.entity.wolf.FlowerWolf;

public class WolfSpawnPlacement {
    public static void setupSpawnPlacements() {
        SpawnPlacements.register(StacyWolvesEntities.BIRCH_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BirchWolf::checkWolfSpawnRules);
        SpawnPlacements.register(StacyWolvesEntities.FIRE_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireWolf::checkWolfSpawnRules);
        SpawnPlacements.register(StacyWolvesEntities.FLOWER_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerWolf::checkWolfSpawnRules);
    
    }
}
  