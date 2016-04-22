package au.lyrael.stacywolves.entity.wolf;

import au.lyrael.stacywolves.annotation.WolfMetadata;
import au.lyrael.stacywolves.annotation.WolfSpawn;
import au.lyrael.stacywolves.client.render.IRenderableWolf;
import au.lyrael.stacywolves.registry.ItemRegistry;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static au.lyrael.stacywolves.utility.WorldHelper.canSeeTheSky;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

@WolfMetadata(name = "EntitySkeletonWolf", primaryColour = 0xDBD8D8, secondaryColour = 0x737373,
        spawns = {
                @WolfSpawn(biomeTypes = PLAINS, probability = 10, min = 1, max = 4),
                @WolfSpawn(biomeTypes = FOREST, probability = 10, min = 1, max = 4),
                @WolfSpawn(biomeTypes = HILLS, probability = 10, min = 1, max = 4),
        })
public class EntitySkeletonWolf extends EntityWolfBase implements IRenderableWolf {

    public EntitySkeletonWolf(World worldObj) {
        super(worldObj);
        addLikedItem(ItemRegistry.getWolfFood("skeleton_bone"));
        this.addEdibleItem(new ItemStack(Items.beef));
        this.addEdibleItem(new ItemStack(Items.chicken));
    }

    @Override
    public float getHealAmount(ItemStack itemstack) {
        if (canEat(itemstack))
            return 2F;
        else
            return 0F;
    }

    @Override
    public EntityWolfBase createChild(EntityAgeable parent) {
        EntityWolfBase child = new EntitySkeletonWolf(this.worldObj);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0) {
            child.func_152115_b(s);
            child.setTamed(true);
        }

        return child;
    }

    @Override
    public String getTextureFolderName() {
        return "skeleton";
    }

    @Override
    public void onLivingUpdate() {

        if (!this.worldObj.isRemote && this.worldObj.isDaytime() && !this.isChild()) {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.getRNG().nextFloat() * 30.0F < (f - 0.4F) * 2.0F &&
                    canSeeTheSky(getWorldObj(), posX, posY, posZ)) {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public boolean canSpawnHereAndNow(World world, float x, float y, float z) {
        if (world.isDaytime())
            return false;
        else
            return true;
    }
}
