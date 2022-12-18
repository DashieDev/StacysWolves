package stacywolves.common.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import stacywolves.common.entity.wolf.BaseWolf;
import stacywolves.common.utils.StringUtils;

public class StacyBoneItem extends Item {

    private Supplier<EntityType<? extends BaseWolf>> target;

    public StacyBoneItem(Properties p_41383_, Supplier<EntityType<? extends BaseWolf>> target ) {
        super(p_41383_);
        this.target = target;
        //TODO Auto-generated constructor stub
    }

    public Class<?> getTarget() {
        return this.target.get().getBaseClass(); 
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> tooltips, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, tooltips, p_41424_);

        StringUtils.formatTooltip("item.stacywolves."+ ForgeRegistries.ITEMS.getKey(this).getPath() + ".tooltip", null, tooltips);
    }
    
}
