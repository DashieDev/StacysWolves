package stacywolves;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import stacywolves.common.entity.wolf.BaseWolf;

public class ChopinLogger {
    private static final Logger LOGGER = LogManager.getLogger("chopin");
    public static void l(String s) {
        ChopinLogger.LOGGER.info(s); 
    }
    public static void lwn(BaseWolf d, String s) {
        ChopinLogger.LOGGER.info("<dog : " + d.getName().getString() + "> " + s);
    }
    public static void lwn(Entity e, String s) {
        if (! (e instanceof BaseWolf) ) {
            ChopinLogger.LOGGER.info("< that's not a dog >" + s);
            return;
        }
        BaseWolf d = (BaseWolf) e;
        ChopinLogger.LOGGER.info("<dog : " + d.getName().getString() + "> " + s);
    }

    public static void sendToOwner(BaseWolf d, String s) {
        if (d.getOwner() == null) return;
        d.getOwner().sendSystemMessage(Component.literal("<" + d.getName().getString() + "> : " + s));
    }

    //For debugging purpose only
    @SubscribeEvent
    public void onWolfOrDogDeath(LivingDeathEvent ev) {
        var e = ev.getEntity();
        if (
            e instanceof BaseWolf
            || e instanceof Wolf
        ) {
            ev.setCanceled(true);
            e.setHealth(e.getMaxHealth());
            if (e instanceof BaseWolf) {
                ChopinLogger.lwn(e, "💩");
            } else {
                ChopinLogger.l("💩");
            }
        }
    }
}
