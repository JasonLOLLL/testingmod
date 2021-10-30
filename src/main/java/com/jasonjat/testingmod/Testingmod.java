package com.jasonjat.testingmod;

import com.jasonjat.testingmod.abilities.AbilityRegistry;
import com.jasonjat.testingmod.enchantments.LaunchEnchantment;
import com.jasonjat.testingmod.entities.*;
import com.jasonjat.testingmod.items.*;
import com.jasonjat.testingmod.model.PenguinEntityModel;
import com.jasonjat.testingmod.modpackets.ModPacketsC2S;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Testingmod implements ModInitializer {

    public static final String MOD_ID = "testingmod";
    public static final Item WAND = new Wand();
    public static final Item TNT_BOW = new TNT_Bow();
    public static final Item EXPLOSIVE_ARROW = new ExplosiveArrowItem();
    public static final Item TNT_SWORD = new TNTSword();
    public static final Item TNT_PICKAXE = new TNTPickaxe();
    public static final Item MINER_PICKAXE = new MinerPickaxe();
    public static final Item ENDER_ARROW = new EnderArrowItem();
    public static final Enchantment LAUNCH = new LaunchEnchantment();

    public static final Logger LOGGER = LogManager.getLogger(Testingmod.class);


    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "explosive_arrow"),
            FabricEntityTypeBuilder.<ExplosiveArrowEntity>create(SpawnGroup.MISC, ExplosiveArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    public static final EntityType<EnderArrowEntity> ENDER_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "ender_arrow"),
            FabricEntityTypeBuilder.<EnderArrowEntity>create(SpawnGroup.MISC, EnderArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    public static final EntityType<PenguinEntity> PENGUIN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "penguin"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PenguinEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.75f)).build()
    );

    public static final EntityType<DuckEntity> DUCK_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "duck"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DuckEntity::new).dimensions(EntityDimensions.fixed(0.5f,0.75f)).build()
    );

    public static final EntityType<AmogusEntity> AMOGUS_ENTITY =  Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "amogus"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AmogusEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );
    public static final Item AMOGUS_SPAWN_EGG = new SpawnEggItem(AMOGUS_ENTITY, 12895428, 11382189, new Item.Settings().group(ItemGroup.MISC));

    public static final EntityModelLayer MODEL_PENGUIN_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "penguin"), "main");

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wand"), WAND);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_bow"), TNT_BOW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "explosive_arrow"), EXPLOSIVE_ARROW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_sword"), TNT_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_pickaxe"), TNT_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "miner_pickaxe"), MINER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ender_arrow"), ENDER_ARROW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "amogus_spawn_egg"), AMOGUS_SPAWN_EGG);

        //enchantments
        Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "launch"), LAUNCH);


        FabricDefaultAttributeRegistry.register(PENGUIN, PenguinEntity.createMobAttributes());
        EntityModelLayerRegistry.registerModelLayer(MODEL_PENGUIN_LAYER, PenguinEntityModel::getTexturedModelData);

        FabricDefaultAttributeRegistry.register(DUCK_ENTITY, DuckEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10));
        FabricDefaultAttributeRegistry.register(AMOGUS_ENTITY   , DuckEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10));


        ModPacketsC2S.register();
        AbilityRegistry.init();
    }

}