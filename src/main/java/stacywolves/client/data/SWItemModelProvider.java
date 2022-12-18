package stacywolves.client.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import stacywolves.common.StacyWolvesItems;
import stacywolves.common.lib.Constants;

import java.util.function.Supplier;

public class SWItemModelProvider extends ItemModelProvider {

    public SWItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Stacy Wolves Item Models";
    }

    @Override
    protected void registerModels() {
        
        egg(StacyWolvesItems.AIR_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.BIRCH_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.BOOKSHELF_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.CAKE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.COAL_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.COOKIE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.COW_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.CRAFTING_TABLE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.DESERT_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.DIAMOND_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.DONKEY_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.EARTH_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.EMERALD_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.ENDER_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.END_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.FIRE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.FLOWER_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.GOLD_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.GUARDIAN_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.ICE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.IRON_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.LAPIS_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.MELON_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.MESA_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.MUSHROOM_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.NETHER_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.OCELOT_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.PRISMARINE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.RED_SAND_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.REDSTONE_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.SAVANNAH_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.SKELETON_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.SLIME_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.SQUID_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.SUNFLOWER_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.TORCH_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.WATER_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.WITCH_WOLF_SPAWN_EGG);
        egg(StacyWolvesItems.ZOMBIE_WOLF_SPAWN_EGG);

        bone( StacyWolvesItems.AIR_BONE );
        bone( StacyWolvesItems.BIRCH_BONE );
        bone( StacyWolvesItems.BOOKSHELF_BONE );
        bone( StacyWolvesItems.CAKE_BONE );
        bone( StacyWolvesItems.COAL_BONE );
        bone( StacyWolvesItems.COOKIE_BONE );
        bone( StacyWolvesItems.WHEAT_BONE );
        bone( StacyWolvesItems.CRAFTING_TABLE_BONE );
        bone( StacyWolvesItems.DESERT_BONE );
        bone( StacyWolvesItems.DIAMOND_BONE );
        bone( StacyWolvesItems.APPLE_BONE );
        bone( StacyWolvesItems.EARTH_BONE );
        bone( StacyWolvesItems.EMERALD_BONE );
        bone( StacyWolvesItems.ENDER_BONE );
        bone( StacyWolvesItems.END_BONE );
        bone( StacyWolvesItems.FIRE_BONE );
        bone( StacyWolvesItems.FLOWER_BONE );
        bone( StacyWolvesItems.GOLD_BONE );
        bone( StacyWolvesItems.SOLID_PRISMARINE_BONE );
        bone( StacyWolvesItems.ICE_BONE );
        bone( StacyWolvesItems.IRON_BONE );
        bone( StacyWolvesItems.BLUE_BONE );
        bone( StacyWolvesItems.MELON_BONE );
        bone( StacyWolvesItems.MESA_BONE );
        bone( StacyWolvesItems.MUSHROOM_BONE );
        bone( StacyWolvesItems.NETHER_BONE );
        bone( StacyWolvesItems.FISH_BONE );
        bone( StacyWolvesItems.PRISMARINE_BONE );
        bone( StacyWolvesItems.RED_SAND_BONE );
        bone( StacyWolvesItems.REDSTONE_BONE );
        bone( StacyWolvesItems.SAVANNAH_BONE );
        bone( StacyWolvesItems.SKELETON_BONE );
        bone( StacyWolvesItems.SLIME_BONE );
        bone( StacyWolvesItems.SQUID_BONE );
        bone( StacyWolvesItems.SUNFLOWER_BONE );
        bone( StacyWolvesItems.TORCH_BONE );
        bone( StacyWolvesItems.WATER_BONE );
        bone( StacyWolvesItems.WITCH_BONE );
        bone( StacyWolvesItems.ZOMBIE_BONE );   
    
    }

    private ResourceLocation itemTexture(Supplier<? extends ItemLike> item) {
        return modLoc(ModelProvider.ITEM_FOLDER + "/" + name(item));
    }

    private ResourceLocation boneTexture(Supplier<? extends ItemLike> item) {
        return modLoc(ModelProvider.ITEM_FOLDER + "/wolf_food/" + name(item));
    }


    private String name(Supplier<? extends ItemLike> item) {
        return ForgeRegistries.ITEMS.getKey(item.get().asItem()).getPath();
    }

    private ItemModelBuilder blockItem(Supplier<? extends Block> block) {
        return blockItem(block, "");
    }


    private ItemModelBuilder egg(Supplier<? extends ItemLike> item) {
        return getBuilder(name(item)).parent(new UncheckedModelFile(ModelProvider.ITEM_FOLDER + "/template_spawn_egg"));
    }

    private ItemModelBuilder generated(Supplier<? extends ItemLike> item) {
        return generated(item, itemTexture(item));
    }

    private ItemModelBuilder generated(Supplier<? extends ItemLike> item, ResourceLocation texture) {
        return getBuilder(name(item)).parent(new UncheckedModelFile(ModelProvider.ITEM_FOLDER + "/generated")).texture("layer0", texture);
    }

    private ItemModelBuilder bone(Supplier<? extends ItemLike> item) {
        return handheld(item, boneTexture(item));
    }

    private ItemModelBuilder handheld(Supplier<? extends ItemLike> item) {
        return handheld(item, itemTexture(item));
    }

    private ItemModelBuilder handheld(Supplier<? extends ItemLike> item, ResourceLocation texture) {
        return getBuilder(name(item)).parent(new UncheckedModelFile(ModelProvider.ITEM_FOLDER + "/handheld")).texture("layer0", texture);
    }

    private ItemModelBuilder blockItem(Supplier<? extends Block> block, String suffix) {
        return withExistingParent(name(block), modLoc(ModelProvider.BLOCK_FOLDER + "/" + name(block) + suffix));
    }
}
