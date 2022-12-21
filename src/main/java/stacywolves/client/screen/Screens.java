package stacywolves.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraftforge.network.NetworkHooks;
import stacywolves.common.entity.wolf.CraftingTableWolf;
import stacywolves.common.menu.CraftingWolfMenu;

public class Screens {

    public static class CraftingWolfMenuProvider implements MenuProvider {

        private CraftingTableWolf dog;

        public CraftingWolfMenuProvider(CraftingTableWolf dog) {
            this.dog = dog;
        }

        @Override
        public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
            return new CraftingWolfMenu(dog, windowId, inventory, 
                ContainerLevelAccess.create(player.level, player.blockPosition()));
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("container.stacywolves.crafting_table_container");
        }
    }


    public static void openCraftingWolfScreen(ServerPlayer sPlayer, CraftingTableWolf wolf) {
        if (wolf.isAlive()) {
            NetworkHooks.openScreen(sPlayer, new CraftingWolfMenuProvider(wolf), (buf) -> {
                buf.writeInt(wolf.getId());
            });
        }
    }
}
