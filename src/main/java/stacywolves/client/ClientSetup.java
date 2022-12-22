package stacywolves.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import stacywolves.client.entity.model.BaseWolfModel;
import stacywolves.client.entity.render.BaseWolfRenderer;
import stacywolves.client.entity.render.FireWolfRenderer;
import stacywolves.client.screen.CraftingWolfScreen;
import stacywolves.common.StacyWolvesEntities;
import stacywolves.common.StacyWolvesMenuTypes;
import stacywolves.common.lib.Constants;

public class ClientSetup {
    public static final ModelLayerLocation BASE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "base_wolf"), "main");
    // public static final ModelLayerLocation AIR_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "air_wolf"), "main");
    // public static final ModelLayerLocation BIRCH_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "birch_wolf"), "main");
    // public static final ModelLayerLocation BOOKSHELF_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "bookshelf_wolf"), "main");
    // public static final ModelLayerLocation CAKE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "cake_wolf"), "main");
    // public static final ModelLayerLocation COAL_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "coal_wolf"), "main");
    // public static final ModelLayerLocation COOKIE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "cookie_wolf"), "main");
    // public static final ModelLayerLocation COW_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "cow_wolf"), "main");
    // public static final ModelLayerLocation CRAFTING_TABLE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "crafting_table_wolf"), "main");
    // public static final ModelLayerLocation DESERT_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "desert_wolf"), "main");
    // public static final ModelLayerLocation DIAMOND_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "diamond_wolf"), "main");
    // public static final ModelLayerLocation DONKEY_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "donkey_wolf"), "main");
    // public static final ModelLayerLocation EARTH_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "earth_wolf"), "main");
    // public static final ModelLayerLocation EMERALD_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "emerald_wolf"), "main");
    // public static final ModelLayerLocation ENDER_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "ender_wolf"), "main");
    // public static final ModelLayerLocation END_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "end_wolf"), "main");
    // public static final ModelLayerLocation FIRE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "fire_wolf"), "main");
    // public static final ModelLayerLocation FLOWER_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "flower_wolf"), "main");
    // public static final ModelLayerLocation GOLD_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "gold_wolf"), "main");
    // public static final ModelLayerLocation GUARDIAN_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "guardian_wolf"), "main");
    // public static final ModelLayerLocation ICE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "ice_wolf"), "main");
    // public static final ModelLayerLocation IRON_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "iron_wolf"), "main");
    // public static final ModelLayerLocation LAPIS_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "lapis_wolf"), "main");
    // public static final ModelLayerLocation MELON_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "melon_wolf"), "main");
    // public static final ModelLayerLocation MESA_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "mesa_wolf"), "main");
    // public static final ModelLayerLocation MUSHROOM_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "mushroom_wolf"), "main");
    // public static final ModelLayerLocation NETHER_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "nether_wolf"), "main");
    // public static final ModelLayerLocation OCELOT_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "ocelot_wolf"), "main");
    // public static final ModelLayerLocation PRISMARINE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "prismarine_wolf"), "main");
    // public static final ModelLayerLocation RED_SAND_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "red_sand_wolf"), "main");
    // public static final ModelLayerLocation REDSTONE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "redstone_wolf"), "main");
    // public static final ModelLayerLocation SAVANNAH_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "savannah_wolf"), "main");
    // public static final ModelLayerLocation SKELETON_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "skeleton_wolf"), "main");
    // public static final ModelLayerLocation SLIME_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "slime_wolf"), "main");
    // public static final ModelLayerLocation SQUID_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "squid_wolf"), "main");
    // public static final ModelLayerLocation SUNFLOWER_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "sunflower_wolf"), "main");
    // public static final ModelLayerLocation TORCH_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "torch_wolf"), "main");
    // public static final ModelLayerLocation WATER_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "water_wolf"), "main");
    // public static final ModelLayerLocation WITCH_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "witch_wolf"), "main");
    // public static final ModelLayerLocation ZOMBIE_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "zombie_wolf"), "main");
    // public static final ModelLayerLocation CHOPIN_WOLF = new ModelLayerLocation(new ResourceLocation(Constants.MODID, "chopin_wolf"), "main");
       
    public static void setupScreenManagers(final FMLClientSetupEvent event) {
        MenuScreens.register(StacyWolvesMenuTypes.CRAFTING_WOLF_MENU.get(), CraftingWolfScreen::new);
    }

    public static void setupEntityRenderers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BASE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(AIR_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(BIRCH_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(BOOKSHELF_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(CAKE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(COAL_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(COOKIE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(COW_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(CRAFTING_TABLE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(DESERT_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(DIAMOND_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(DONKEY_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(EARTH_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(EMERALD_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(ENDER_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(END_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(FIRE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(FLOWER_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(GOLD_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(GUARDIAN_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(ICE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(IRON_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(LAPIS_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(MELON_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(MESA_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(MUSHROOM_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(NETHER_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(OCELOT_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(PRISMARINE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(RED_SAND_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(REDSTONE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(SAVANNAH_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(SKELETON_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(SLIME_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(SQUID_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(SUNFLOWER_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(TORCH_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(WATER_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(WITCH_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(ZOMBIE_WOLF, BaseWolfModel::createBodyLayer);
        // event.registerLayerDefinition(CHOPIN_WOLF, BaseWolfModel::createBodyLayer); 
    }

    public static void setupTileEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(StacyWolvesEntities.AIR_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.BIRCH_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.BOOKSHELF_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.CAKE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.COAL_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.COOKIE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.COW_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.CRAFTING_TABLE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.DESERT_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.DIAMOND_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.DONKEY_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.EARTH_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.EMERALD_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.ENDER_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.END_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.FIRE_WOLF.get(), FireWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.FLOWER_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.GOLD_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.GUARDIAN_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.ICE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.IRON_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.LAPIS_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.MELON_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.MESA_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.MUSHROOM_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.NETHER_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.OCELOT_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.PRISMARINE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.RED_SAND_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.REDSTONE_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.SAVANNAH_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.SKELETON_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.SLIME_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.SQUID_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.SUNFLOWER_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.TORCH_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.WATER_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.WITCH_WOLF.get(), BaseWolfRenderer::new);
        event.registerEntityRenderer(StacyWolvesEntities.ZOMBIE_WOLF.get(), BaseWolfRenderer::new);
    }

}
