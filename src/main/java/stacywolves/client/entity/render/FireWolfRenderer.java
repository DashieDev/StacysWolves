package stacywolves.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import stacywolves.client.ClientSetup;
import stacywolves.client.entity.model.BaseWolfModel;
import stacywolves.common.entity.wolf.FireWolf;

public class FireWolfRenderer extends BaseWolfRenderer<FireWolf> {
    public FireWolfRenderer(EntityRendererProvider.Context p_174452_) {
        super(p_174452_);
    }

    @Override
    protected int getBlockLightLevel(FireWolf p_113910_, BlockPos p_113911_) {
        return 15;
    }
}
