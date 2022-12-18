package stacywolves.common.utils;

import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFix;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

public class StringUtils {


    public static void formatTooltip(String langName, ImmutableMap<String, String> toFormat, List<Component> list) {
        String langTooltip = (Component.translatable(langName)).getString();
        if (langTooltip == null || langTooltip.equals(langName))
            return;
        if (toFormat != null) {
            for (Entry<String, String> toReplace : toFormat.entrySet()) {
                langTooltip = langTooltip.replace("{{" + toReplace.getKey() + "}}", toReplace.getValue());
            }
        }

        for (String descriptionLine : langTooltip.split("(?<!\\\\);")) {
            final String unescapedLine = descriptionLine.replaceAll("\\\\;", ";");
            if (unescapedLine != null && descriptionLine.length() > 0)
                list.add((Component.literal( unescapedLine)).withStyle(ChatFormatting.GRAY));
        }
    }
}
