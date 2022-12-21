package stacywolves.common;

import java.util.function.Supplier;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stacywolves.common.entity.wolf.CraftingTableWolf;
import stacywolves.common.lib.Constants;
import stacywolves.common.menu.CraftingWolfMenu;

public class StacyWolvesMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.Keys.MENU_TYPES, Constants.MODID);

    public static final RegistryObject<MenuType<CraftingWolfMenu>> CRAFTING_WOLF_MENU = register("crafting_table_wolf_menu", (windowId, inv, data) -> {
        int dogId = data.readInt();
        var e = inv.player.level.getEntity(dogId);
        if (!(e instanceof CraftingTableWolf wolf)) return null;
        return new CraftingWolfMenu(wolf, windowId, inv);
    });

    private static <X extends AbstractContainerMenu, T extends MenuType<X>> RegistryObject<MenuType<X>> register(final String name, final IContainerFactory<X> factory) {
        return register(name, () -> IForgeMenuType.create(factory));
    }

    private static <T extends MenuType<?>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return MENUS.register(name, sup);
    }

}
