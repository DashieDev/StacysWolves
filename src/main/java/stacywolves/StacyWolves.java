package stacywolves;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import stacywolves.client.ClientSetup;
import stacywolves.client.data.SWItemModelProvider;
import stacywolves.common.EventHandler;
import stacywolves.common.StacyWolvesEntities;
import stacywolves.common.StacyWolvesItemGroup;
import stacywolves.common.StacyWolvesItems;
import stacywolves.common.StacyWolvesLighting;
import stacywolves.common.lib.Constants;
import stacywolves.common.spawn.WolfSpawnPlacement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Constants.MODID)
public class StacyWolves
{   
    //TODO WolfGen

    public StacyWolves() {

        var meb = FMLJavaModLoadingContext.get().getModEventBus();

        meb.addListener(this::setup);
        meb.addListener(this::gatherData);
        
        StacyWolvesEntities.ENTITIES.register(meb);
        StacyWolvesLighting.BLOCKS.register(meb);
        StacyWolvesItems.ITEMS.register(meb);

        meb.addListener(StacyWolvesEntities::addEntityAttributes);

        var eventBus = MinecraftForge.EVENT_BUS;
        
        eventBus.register(new EventHandler());



        // Client Events
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            meb.addListener(ClientSetup::setupTileEntityRenderers);
            meb.addListener(ClientSetup::setupEntityRenderers);
        });

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        StacyWolvesLighting.init();

        WolfSpawnPlacement.setupSpawnPlacements();
        var eventbus = MinecraftForge.EVENT_BUS;
        eventbus.register(new ChopinLogger());
    }

    private void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        if (event.includeClient()) {
            gen.addProvider(true, new SWItemModelProvider(gen, event.getExistingFileHelper()));
        }

        if (event.includeServer()) {
        }
    }

}
