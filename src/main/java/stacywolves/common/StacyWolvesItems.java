package stacywolves.common;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stacywolves.common.entity.wolf.BookshelfWolf;
import stacywolves.common.entity.wolf.CraftingTableWolf;
import stacywolves.common.entity.wolf.DiamondWolf;
import stacywolves.common.entity.wolf.EarthWolf;
import stacywolves.common.entity.wolf.FireWolf;
import stacywolves.common.entity.wolf.FlowerWolf;
import stacywolves.common.entity.wolf.GoldWolf;
import stacywolves.common.entity.wolf.SunflowerWolf;
import stacywolves.common.entity.wolf.TorchWolf;
import stacywolves.common.entity.wolf.WaterWolf;
import stacywolves.common.item.StacyBoneItem;
import stacywolves.common.lib.Constants;

public class StacyWolvesItems {
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    
    public static final RegistryObject<Item> AIR_WOLF_SPAWN_EGG = register("air_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.AIR_WOLF.get(), 0xEDECEC, 0xC9EFF1,  createInitialProp()));
    public static final RegistryObject<Item> BIRCH_WOLF_SPAWN_EGG = register("birch_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.BIRCH_WOLF.get(), 0x2F332C, 0xEEEEE9,  createInitialProp()));
    public static final RegistryObject<Item> BOOKSHELF_WOLF_SPAWN_EGG = register("bookshelf_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.BOOKSHELF_WOLF.get(), 0x254975, 0xB50F0F,  createInitialProp()));
    public static final RegistryObject<Item> CAKE_WOLF_SPAWN_EGG = register("cake_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.CAKE_WOLF.get(), 0xB35922, 0xE41717,  createInitialProp()));
    public static final RegistryObject<Item> COAL_WOLF_SPAWN_EGG = register("coal_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.COAL_WOLF.get(), 0x7F7F7F, 0x343434,  createInitialProp()));
    public static final RegistryObject<Item> COOKIE_WOLF_SPAWN_EGG = register("cookie_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.COOKIE_WOLF.get(), 0xD7813C, 0x894929,  createInitialProp()));
    public static final RegistryObject<Item> COW_WOLF_SPAWN_EGG = register("cow_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.COW_WOLF.get(), 0xA0A0A0, 0x3E2F23,  createInitialProp()));
    public static final RegistryObject<Item> CRAFTING_TABLE_WOLF_SPAWN_EGG = register("crafting_table_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.CRAFTING_TABLE_WOLF.get(), 0xB38F59, 0x572C19,  createInitialProp()));
    public static final RegistryObject<Item> DESERT_WOLF_SPAWN_EGG = register("desert_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.DESERT_WOLF.get(), 0xE0D6A6, 0xBDB286,  createInitialProp()));
    public static final RegistryObject<Item> DIAMOND_WOLF_SPAWN_EGG = register("diamond_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.DIAMOND_WOLF.get(), 0x7F7F7F, 0x5DECF5,  createInitialProp()));
    public static final RegistryObject<Item> DONKEY_WOLF_SPAWN_EGG = register("donkey_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.DONKEY_WOLF.get(), 0x514439, 0x514439,  createInitialProp()));
    public static final RegistryObject<Item> EARTH_WOLF_SPAWN_EGG = register("earth_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.EARTH_WOLF.get(), 0xB8845B, 0x583C28,  createInitialProp()));
    public static final RegistryObject<Item> EMERALD_WOLF_SPAWN_EGG = register("emerald_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.EMERALD_WOLF.get(), 0x7F7F7F, 0x17DD62,  createInitialProp()));
    public static final RegistryObject<Item> ENDER_WOLF_SPAWN_EGG = register("ender_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.ENDER_WOLF.get(), 0x000000, 0xCC00FA,  createInitialProp()));
    public static final RegistryObject<Item> END_WOLF_SPAWN_EGG = register("end_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.END_WOLF.get(), 0xF9F9C5, 0xC3BD89,  createInitialProp()));
    public static final RegistryObject<Item> FIRE_WOLF_SPAWN_EGG = register("fire_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.FIRE_WOLF.get(), 0xF4C923, 0x6D2C04,  createInitialProp()));
    public static final RegistryObject<Item> FLOWER_WOLF_SPAWN_EGG = register("flower_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.FLOWER_WOLF.get(), 0x0E5C00, 0xB95E9A,  createInitialProp()));
    public static final RegistryObject<Item> GOLD_WOLF_SPAWN_EGG = register("gold_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.GOLD_WOLF.get(), 0x7F7F7F, 0xF8AF2B,  createInitialProp()));
    public static final RegistryObject<Item> GUARDIAN_WOLF_SPAWN_EGG = register("guardian_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.GUARDIAN_WOLF.get(), 0x395F4E, 0xD7793A,  createInitialProp()));
    public static final RegistryObject<Item> ICE_WOLF_SPAWN_EGG = register("ice_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.ICE_WOLF.get(), 0xEDFEFE, 0x9EBAE7,  createInitialProp()));
    public static final RegistryObject<Item> IRON_WOLF_SPAWN_EGG = register("iron_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.IRON_WOLF.get(), 0x7F7F7F, 0xD8AF93,  createInitialProp()));
    public static final RegistryObject<Item> LAPIS_WOLF_SPAWN_EGG = register("lapis_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.LAPIS_WOLF.get(), 0x7F7F7F, 0x102CB5,  createInitialProp()));
    public static final RegistryObject<Item> MELON_WOLF_SPAWN_EGG = register("melon_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.MELON_WOLF.get(), 0x444F0F, 0xB92013,  createInitialProp()));
    public static final RegistryObject<Item> MESA_WOLF_SPAWN_EGG = register("mesa_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.MESA_WOLF.get(), 0x9A6147, 0x4D3424,  createInitialProp()));
    public static final RegistryObject<Item> MUSHROOM_WOLF_SPAWN_EGG = register("mushroom_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.MUSHROOM_WOLF.get(), 0xB11917, 0xD5D5D5,  createInitialProp()));
    public static final RegistryObject<Item> NETHER_WOLF_SPAWN_EGG = register("nether_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.NETHER_WOLF.get(), 0x830D0D, 0xD55910,  createInitialProp()));
    public static final RegistryObject<Item> OCELOT_WOLF_SPAWN_EGG = register("ocelot_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.OCELOT_WOLF.get(), 0xD99C53, 0x1D6D30,  createInitialProp()));
    public static final RegistryObject<Item> PRISMARINE_WOLF_SPAWN_EGG = register("prismarine_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.PRISMARINE_WOLF.get(), 0x42689B, 0x68516F,  createInitialProp()));
    public static final RegistryObject<Item> RED_SAND_WOLF_SPAWN_EGG = register("red_sand_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.RED_SAND_WOLF.get(), 0xA2501C, 0x5A3305,  createInitialProp()));
    public static final RegistryObject<Item> REDSTONE_WOLF_SPAWN_EGG = register("redstone_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.REDSTONE_WOLF.get(), 0x7F7F7F, 0x8F0303,  createInitialProp()));
    public static final RegistryObject<Item> SAVANNAH_WOLF_SPAWN_EGG = register("savannah_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.SAVANNAH_WOLF.get(), 0xE4D7B0, 0xB0B252,  createInitialProp()));
    public static final RegistryObject<Item> SKELETON_WOLF_SPAWN_EGG = register("skeleton_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.SKELETON_WOLF.get(), 0xDBD8D8, 0x737373,  createInitialProp()));
    public static final RegistryObject<Item> SLIME_WOLF_SPAWN_EGG = register("slime_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.SLIME_WOLF.get(), 0x8ABD7C, 0x2E4628,  createInitialProp()));
    public static final RegistryObject<Item> SQUID_WOLF_SPAWN_EGG = register("squid_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.SQUID_WOLF.get(), 0x000000, 0x2222AA,  createInitialProp()));
    public static final RegistryObject<Item> SUNFLOWER_WOLF_SPAWN_EGG = register("sunflower_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.SUNFLOWER_WOLF.get(), 0xEBBD32, 0x5A9841,  createInitialProp()));
    public static final RegistryObject<Item> TORCH_WOLF_SPAWN_EGG = register("torch_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.TORCH_WOLF.get(), 0x665130, 0xFFD800,  createInitialProp()));
    public static final RegistryObject<Item> WATER_WOLF_SPAWN_EGG = register("water_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.WATER_WOLF.get(), 0xDDD9DA, 0x91C5B7,  createInitialProp()));
    public static final RegistryObject<Item> WITCH_WOLF_SPAWN_EGG = register("witch_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.WITCH_WOLF.get(), 0x340000, 0x51a03e,  createInitialProp()));
    public static final RegistryObject<Item> ZOMBIE_WOLF_SPAWN_EGG = register("zombie_wolf_spawn_egg", () -> new ForgeSpawnEggItem(() -> StacyWolvesEntities.ZOMBIE_WOLF.get(), 0x04AEAE, 0x447230,  createInitialProp()));

    public static final RegistryObject<Item> AIR_BONE = register("air_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.AIR_WOLF .get()));
    public static final RegistryObject<Item> BIRCH_BONE = register("birch_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.BIRCH_WOLF .get()));
    public static final RegistryObject<Item> BOOKSHELF_BONE = register("book_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.BOOKSHELF_WOLF .get()));
    public static final RegistryObject<Item> CAKE_BONE = register("cake_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.CAKE_WOLF .get()));
    public static final RegistryObject<Item> COAL_BONE = register("coal_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.COAL_WOLF .get()));
    public static final RegistryObject<Item> COOKIE_BONE = register("cookie_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.COOKIE_WOLF .get()));
    public static final RegistryObject<Item> WHEAT_BONE = register("wheat_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.COW_WOLF .get()));
    public static final RegistryObject<Item> CRAFTING_TABLE_BONE = register("crafty_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.CRAFTING_TABLE_WOLF .get()));
    public static final RegistryObject<Item> DESERT_BONE = register("desert_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.DESERT_WOLF .get()));
    public static final RegistryObject<Item> DIAMOND_BONE = register("diamond_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.DIAMOND_WOLF .get()));
    public static final RegistryObject<Item> APPLE_BONE = register("apple_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.DONKEY_WOLF .get()));
    public static final RegistryObject<Item> EARTH_BONE = register("earth_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.EARTH_WOLF .get()));
    public static final RegistryObject<Item> EMERALD_BONE = register("emerald_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.EMERALD_WOLF .get()));
    public static final RegistryObject<Item> ENDER_BONE = register("ender_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.ENDER_WOLF .get()));
    public static final RegistryObject<Item> END_BONE = register("end_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.END_WOLF .get()));
    public static final RegistryObject<Item> FIRE_BONE = register("fire_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.FIRE_WOLF .get()));
    public static final RegistryObject<Item> FLOWER_BONE = register("flower_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.FLOWER_WOLF .get()));
    public static final RegistryObject<Item> GOLD_BONE = register("gold_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.GOLD_WOLF .get()));
    public static final RegistryObject<Item> SOLID_PRISMARINE_BONE = register("solid_prismarine_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.GUARDIAN_WOLF .get()));
    public static final RegistryObject<Item> ICE_BONE = register("ice_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.ICE_WOLF .get()));
    public static final RegistryObject<Item> IRON_BONE = register("iron_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.IRON_WOLF .get()));
    public static final RegistryObject<Item> BLUE_BONE = register("lapis_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.LAPIS_WOLF .get()));
    public static final RegistryObject<Item> MELON_BONE = register("melon_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.MELON_WOLF .get()));
    public static final RegistryObject<Item> MESA_BONE = register("mesa_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.MESA_WOLF .get()));
    public static final RegistryObject<Item> MUSHROOM_BONE = register("mushroom_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.MUSHROOM_WOLF .get()));
    public static final RegistryObject<Item> NETHER_BONE = register("nether_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.NETHER_WOLF .get()));
    public static final RegistryObject<Item> FISH_BONE = register("fish_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.OCELOT_WOLF .get()));
    public static final RegistryObject<Item> PRISMARINE_BONE = register("prismarine_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.PRISMARINE_WOLF .get()));
    public static final RegistryObject<Item> RED_SAND_BONE = register("red_sand_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.RED_SAND_WOLF .get()));
    public static final RegistryObject<Item> REDSTONE_BONE = register("redstone_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.REDSTONE_WOLF .get()));
    public static final RegistryObject<Item> SAVANNAH_BONE = register("savannah_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.SAVANNAH_WOLF .get()));
    public static final RegistryObject<Item> SKELETON_BONE = register("skeleton_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.SKELETON_WOLF .get()));
    public static final RegistryObject<Item> SLIME_BONE = register("slime_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.SLIME_WOLF .get()));
    public static final RegistryObject<Item> SQUID_BONE = register("inky_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.SQUID_WOLF .get()));
    public static final RegistryObject<Item> SUNFLOWER_BONE = register("sunflower_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.SUNFLOWER_WOLF .get()));
    public static final RegistryObject<Item> TORCH_BONE = register("torch_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.TORCH_WOLF .get()));
    public static final RegistryObject<Item> WATER_BONE = register("water_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.WATER_WOLF .get()));
    public static final RegistryObject<Item> WITCH_BONE = register("glow_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.WITCH_WOLF .get()));
    public static final RegistryObject<Item> ZOMBIE_BONE = register("zombie_bone", () -> new StacyBoneItem(createInitialProp(), () -> StacyWolvesEntities.ZOMBIE_WOLF .get()));
    
    private static Item.Properties createInitialProp() {
        
        return new Item.Properties().tab(StacyWolvesItemGroup.GENERAL);

    }


    private static <T extends Item> RegistryObject<T> register(final String name, Function<Item.Properties, T> itemConstructor) {
        return register(name, () -> itemConstructor.apply(createInitialProp()));
    }

    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return ITEMS.register(name, sup);
    }
}
