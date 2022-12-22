package stacywolves.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.RegistryObject;
import stacywolves.common.entity.wolf.*;
import stacywolves.common.lib.Constants;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class StacyWolvesEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MODID);
    
    public static final RegistryObject<EntityType<AirWolf>> AIR_WOLF = registerWolf("air_wolf", AirWolf::new);
    public static final RegistryObject<EntityType<BirchWolf>> BIRCH_WOLF = registerWolf("birch_wolf", BirchWolf::new);
    public static final RegistryObject<EntityType<BookshelfWolf>> BOOKSHELF_WOLF = registerWolf("bookshelf_wolf", BookshelfWolf::new);
    public static final RegistryObject<EntityType<CakeWolf>> CAKE_WOLF = registerWolf("cake_wolf", CakeWolf::new);
    public static final RegistryObject<EntityType<CoalWolf>> COAL_WOLF = registerWolf("coal_wolf", CoalWolf::new);
    public static final RegistryObject<EntityType<CookieWolf>> COOKIE_WOLF = registerWolf("cookie_wolf", CookieWolf::new);
    public static final RegistryObject<EntityType<CowWolf>> COW_WOLF = registerWolf("cow_wolf", CowWolf::new);
    public static final RegistryObject<EntityType<CraftingTableWolf>> CRAFTING_TABLE_WOLF = registerWolf("crafting_table_wolf", CraftingTableWolf::new);
    public static final RegistryObject<EntityType<DesertWolf>> DESERT_WOLF = registerWolf("desert_wolf", DesertWolf::new);
    public static final RegistryObject<EntityType<DiamondWolf>> DIAMOND_WOLF = registerWolf("diamond_wolf", DiamondWolf::new);
    public static final RegistryObject<EntityType<DonkeyWolf>> DONKEY_WOLF = registerWolf("donkey_wolf", DonkeyWolf::new);
    public static final RegistryObject<EntityType<EarthWolf>> EARTH_WOLF = registerWolf("earth_wolf", EarthWolf::new);
    public static final RegistryObject<EntityType<EmeraldWolf>> EMERALD_WOLF = registerWolf("emerald_wolf", EmeraldWolf::new);
    public static final RegistryObject<EntityType<EnderWolf>> ENDER_WOLF = registerWolf("ender_wolf", EnderWolf::new);
    public static final RegistryObject<EntityType<EndWolf>> END_WOLF = registerWolf("end_wolf", EndWolf::new);
    public static final RegistryObject<EntityType<FireWolf>> FIRE_WOLF = registerFireImmuneWolf("fire_wolf", FireWolf::new);
    public static final RegistryObject<EntityType<FlowerWolf>> FLOWER_WOLF = registerWolf("flower_wolf", FlowerWolf::new);
    public static final RegistryObject<EntityType<GoldWolf>> GOLD_WOLF = registerWolf("gold_wolf", GoldWolf::new);
    public static final RegistryObject<EntityType<GuardianWolf>> GUARDIAN_WOLF = registerWolf("guardian_wolf", GuardianWolf::new);
    public static final RegistryObject<EntityType<IceWolf>> ICE_WOLF = registerWolf("ice_wolf", IceWolf::new);
    public static final RegistryObject<EntityType<IronWolf>> IRON_WOLF = registerWolf("iron_wolf", IronWolf::new);
    public static final RegistryObject<EntityType<LapisWolf>> LAPIS_WOLF = registerWolf("lapis_wolf", LapisWolf::new);
    public static final RegistryObject<EntityType<MelonWolf>> MELON_WOLF = registerWolf("melon_wolf", MelonWolf::new);
    public static final RegistryObject<EntityType<MesaWolf>> MESA_WOLF = registerWolf("mesa_wolf", MesaWolf::new);
    public static final RegistryObject<EntityType<MushroomWolf>> MUSHROOM_WOLF = registerWolf("mushroom_wolf", MushroomWolf::new);
    public static final RegistryObject<EntityType<NetherWolf>> NETHER_WOLF = registerWolf("nether_wolf", NetherWolf::new);
    public static final RegistryObject<EntityType<OcelotWolf>> OCELOT_WOLF = registerWolf("ocelot_wolf", OcelotWolf::new);
    public static final RegistryObject<EntityType<PrismarineWolf>> PRISMARINE_WOLF = registerWolf("prismarine_wolf", PrismarineWolf::new);
    public static final RegistryObject<EntityType<RedSandWolf>> RED_SAND_WOLF = registerWolf("red_sand_wolf", RedSandWolf::new);
    public static final RegistryObject<EntityType<RedstoneWolf>> REDSTONE_WOLF = registerWolf("redstone_wolf", RedstoneWolf::new);
    public static final RegistryObject<EntityType<SavannahWolf>> SAVANNAH_WOLF = registerWolf("savannah_wolf", SavannahWolf::new);
    public static final RegistryObject<EntityType<SkeletonWolf>> SKELETON_WOLF = registerWolf("skeleton_wolf", SkeletonWolf::new);
    public static final RegistryObject<EntityType<SlimeWolf>> SLIME_WOLF = registerWolf("slime_wolf", SlimeWolf::new);
    public static final RegistryObject<EntityType<SquidWolf>> SQUID_WOLF = registerWolf("squid_wolf", SquidWolf::new);
    public static final RegistryObject<EntityType<SunflowerWolf>> SUNFLOWER_WOLF = registerWolf("sunflower_wolf", SunflowerWolf::new);
    public static final RegistryObject<EntityType<TorchWolf>> TORCH_WOLF = registerWolf("torch_wolf", TorchWolf::new);
    public static final RegistryObject<EntityType<WaterWolf>> WATER_WOLF = registerWolf("water_wolf", WaterWolf::new);
    public static final RegistryObject<EntityType<WitchWolf>> WITCH_WOLF = registerWolf("witch_wolf", WitchWolf::new);
    public static final RegistryObject<EntityType<ZombieWolf>> ZOMBIE_WOLF = registerWolf("zombie_wolf", ZombieWolf::new);


    private static <T extends Entity> RegistryObject<EntityType<T>> registerWolf(String name, EntityFactory<T> sup ) {
        return register(name, sup, MobCategory.CREATURE, (b) -> b
            .sized(0.6F, 0.85F)
            .setUpdateInterval(3)
            .setTrackingRange(16)
            .setShouldReceiveVelocityUpdates(true)); 
    
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerFireImmuneWolf(String name, EntityFactory<T> sup ) {
        return register(name, sup, MobCategory.CREATURE, (b) -> b
            .sized(0.6F, 0.85F)
            .setUpdateInterval(3)
            .setTrackingRange(16)
            .setShouldReceiveVelocityUpdates(true)
            .fireImmune()
        );
    
    }

    private static <E extends Entity, T extends EntityType<E>> RegistryObject<EntityType<E>> register(final String name, final EntityType.EntityFactory<E> sup, final MobCategory classification, final Function<EntityType.Builder<E>, EntityType.Builder<E>> builder) {
         return register(name, () -> builder.apply(EntityType.Builder.of(sup, classification)).build(new ResourceLocation(Constants.MODID, name).toString()));
    }

    private static <E extends Entity, T extends EntityType<E>> RegistryObject<T> register(final String name, final Supplier<T> sup) {
        return ENTITIES.register(name, sup);
    }

    public static void addEntityAttributes(EntityAttributeCreationEvent e) {
                
        addDefaultAttributesWithAdditional(e, AIR_WOLF, AirWolf.getAddtionalAttributes());
        addDefaultAttributes(e, BIRCH_WOLF);
        addDefaultAttributes(e, BOOKSHELF_WOLF);
        addDefaultAttributes(e, CAKE_WOLF);
        addDefaultAttributes(e, COAL_WOLF);
        addDefaultAttributes(e, COOKIE_WOLF);
        addDefaultAttributes(e, COW_WOLF);
        addDefaultAttributes(e, CRAFTING_TABLE_WOLF);
        addDefaultAttributes(e, DESERT_WOLF);
        addDefaultAttributes(e, DIAMOND_WOLF);
        addDefaultAttributes(e, DONKEY_WOLF);
        addDefaultAttributes(e, EARTH_WOLF);
        addDefaultAttributes(e, EMERALD_WOLF);
        addDefaultAttributes(e, ENDER_WOLF);
        addDefaultAttributes(e, END_WOLF);
        addDefaultAttributes(e, FIRE_WOLF);
        addDefaultAttributes(e, FLOWER_WOLF);
        addDefaultAttributes(e, GOLD_WOLF);
        addDefaultAttributes(e, GUARDIAN_WOLF);
        addDefaultAttributes(e, ICE_WOLF);
        addDefaultAttributes(e, IRON_WOLF);
        addDefaultAttributes(e, LAPIS_WOLF);
        addDefaultAttributes(e, MELON_WOLF);
        addDefaultAttributes(e, MESA_WOLF);
        addDefaultAttributes(e, MUSHROOM_WOLF);
        addDefaultAttributes(e, NETHER_WOLF);
        addDefaultAttributes(e, OCELOT_WOLF);
        addDefaultAttributes(e, PRISMARINE_WOLF);
        addDefaultAttributes(e, RED_SAND_WOLF);
        addDefaultAttributes(e, REDSTONE_WOLF);
        addDefaultAttributes(e, SAVANNAH_WOLF);
        addDefaultAttributes(e, SKELETON_WOLF);
        addDefaultAttributes(e, SLIME_WOLF);
        addDefaultAttributes(e, SQUID_WOLF);
        addDefaultAttributes(e, SUNFLOWER_WOLF);
        addDefaultAttributes(e, TORCH_WOLF);
        addDefaultAttributesWithAdditional(e, WATER_WOLF, WaterWolf.getAddtionalAttributes());
        addDefaultAttributes(e, WITCH_WOLF);
        addDefaultAttributes(e, ZOMBIE_WOLF);

     }

     private static <T extends LivingEntity> void addDefaultAttributes(EntityAttributeCreationEvent e, RegistryObject<EntityType<T>> x) {
        e.put(x.get(),
                 Mob.createMobAttributes()
                 .add(Attributes.MAX_HEALTH, 8.0D)
                 .add(Attributes.MOVEMENT_SPEED, 0.3D)
                 .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                 .add(Attributes.ATTACK_DAMAGE, 2.0D)
                 //.add(DoggyAttributes.JUMP_POWER.get(), 0.42D)
                 //.add(DoggyAttributes.CRIT_CHANCE.get(), 0.01D)
                //.add(DoggyAttributes.CRIT_BONUS.get(), 1D)
                 .build()
         );
     }

     private static <T extends LivingEntity> void addDefaultAttributesWithAdditional(
        EntityAttributeCreationEvent e, RegistryObject<EntityType<T>> x, Consumer<Builder> apply) {
        var builder = Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 8.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.3D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.0D);
        apply.accept(builder);
        e.put(x.get(), builder.build());
     }
}

