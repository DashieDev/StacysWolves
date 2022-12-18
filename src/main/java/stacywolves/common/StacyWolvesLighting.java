package stacywolves.common;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import com.google.common.collect.Maps;

import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stacywolves.ChopinLogger;
import stacywolves.common.lib.Constants;

public class StacyWolvesLighting {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);

    public static final RegistryObject<TorchWolfAirBlock> TORCH_WOLF_AIR = register("torch_wolf_air", TorchWolfAirBlock::new);
    public static final RegistryObject<TorchWolfCaveAirBlock> TORCH_WOLF_CAVE_AIR = register("torch_wolf_cave_air", TorchWolfCaveAirBlock::new);

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    //
    public static Map<Block, Block> lightSubstitudeMap = Maps.newConcurrentMap();

    //Target -> Substitute
    public static void init() {
        lightSubstitudeMap.put(Blocks.AIR, TORCH_WOLF_AIR.get());
        lightSubstitudeMap.put(Blocks.CAVE_AIR, TORCH_WOLF_CAVE_AIR.get());
    }

    public static class TorchWolfAirBlock extends AirBlock {

        public TorchWolfAirBlock() {
            super(BlockBehaviour.Properties.of(Material.AIR).noCollission().noLootTable().air().lightLevel((x) -> 14));
        }
        
    }

    public static class TorchWolfCaveAirBlock extends AirBlock {

        public TorchWolfCaveAirBlock() {
            super(BlockBehaviour.Properties.of(Material.AIR).noCollission().noLootTable().air().lightLevel((x) -> 14));
        }

    }

}
