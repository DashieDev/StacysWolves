package stacywolves.common.entity.wolf;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import stacywolves.client.screen.Screens;


public class CraftingTableWolf extends BaseWolf {
   public CraftingTableWolf(EntityType<CraftingTableWolf> p_30369_, Level p_30370_) {
    super(p_30369_, p_30370_);
    }

    @Override
    public String getResourceName() {
 
      return "crafting_table";
   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      
      if (player.isShiftKeyDown()) {
         if (!this.level.isClientSide) {
            if (player instanceof ServerPlayer sPlayer) {
               Screens.openCraftingWolfScreen(sPlayer, this);
            }
         }
         return InteractionResult.SUCCESS;
      }

      return super.mobInteract(player, hand);
   }
}
