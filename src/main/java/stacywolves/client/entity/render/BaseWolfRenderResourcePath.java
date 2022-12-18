package stacywolves.client.entity.render;

import net.minecraft.resources.ResourceLocation;
import stacywolves.common.lib.Constants;

public class BaseWolfRenderResourcePath {

    protected static String textureBasePath = "textures/entity/wolf";

    private ResourceLocation base;
    private ResourceLocation angry;
    private ResourceLocation tame;

    public ResourceLocation getBase() {
        return base;
    }

    public ResourceLocation getAngry() {
        return angry;
    }

    public ResourceLocation getTame() {
        return tame;
    }

    public BaseWolfRenderResourcePath(String folderName) {
        base = new ResourceLocation(Constants.MODID, textureBasePath + "/" + folderName + "/" + folderName + "_wolf.png");
        angry = new ResourceLocation(Constants.MODID, textureBasePath + "/" + folderName + "/" + folderName + "_wolf_angry.png");
        tame = new ResourceLocation(Constants.MODID, textureBasePath + "/" + folderName + "/" + folderName + "_wolf_tame.png");
    }
}

