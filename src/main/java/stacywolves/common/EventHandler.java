package stacywolves.common;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stacywolves.ChopinLogger;
import stacywolves.common.entity.wolf.BaseWolf;

public class EventHandler {
    
    // @SubscribeEvent
    // public void onBiomeGeneration(Biome e) {
    //     switch (e.getCategory()) {

    //         case FOREST : {
    //             e.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(StacyWolvesEntities.BIRCH_WOLF.get(), 6, 4, 4));
    //             break;
    //         }
    //         case NETHER : {
    //             e.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(StacyWolvesEntities.FIRE_WOLF.get(), 6, 1, 4));
    //             break;
    //         }
    //         case PLAINS : {
    //             e.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(StacyWolvesEntities.FLOWER_WOLF.get(), 6, 1, 4));
    //             break;
    //         }
    //     }
    // }

    // @SubscribeEvent(priority = EventPriority.HIGH) //haiii 🥴🥴🥴🥴🥴🥴🥴🥴
    // public void onStructureSpawnGather(StructureSpawnListGatherEvent e) {
    //     if (e.getStructure() == StructureFeature.NETHER_BRIDGE) {
    //         e.addEntitySpawn(MobCategory.CREATURE, 
    //             new MobSpawnSettings.SpawnerData(StacyWolvesEntities.FIRE_WOLF.get(), 7, 1, 2));
    //         ChopinLogger.l(e.getEntitySpawns(MobCategory.CREATURE).toString());
    //     }
    // }

    
    @SubscribeEvent
    public void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(StacyWolvesEntities.BIRCH_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWolf::checkWolfSpawnRules, Operation.REPLACE);
        event.register(StacyWolvesEntities.FIRE_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWolf::checkWolfSpawnRules, Operation.REPLACE);
        event.register(StacyWolvesEntities.FLOWER_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWolf::checkWolfSpawnRules, Operation.REPLACE);
    
    }


}
