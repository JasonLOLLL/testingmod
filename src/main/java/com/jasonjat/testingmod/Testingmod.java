package com.jasonjat.testingmod;

import com.jasonjat.testingmod.entities.ExplosiveArrowEntity;
import com.jasonjat.testingmod.entities.PenguinEntity;
import com.jasonjat.testingmod.items.*;
import com.jasonjat.testingmod.model.PenguinEntityModel;
import com.jasonjat.testingmod.modpackets.ModPacketsC2S;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Testingmod implements ModInitializer {

    public static final String MOD_ID = "testingmod";
    public static final Item WAND = new Wand();
    public static final Item TNT_BOW = new TNT_Bow();
    public static final Item EXPLOSIVE_ARROW = new ExplosiveArrowItem();
    public static final Item TNT_SWORD = new TNTSword();
    public static final Item TNT_PICKAXE = new TNTPickaxe();

    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "explosive_arrow"),
            FabricEntityTypeBuilder.<ExplosiveArrowEntity>create(SpawnGroup.MISC, ExplosiveArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    public static final EntityType<PenguinEntity> PENGUIN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "penguin"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PenguinEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.75f)).build()
    );

    public static final EntityModelLayer MODEL_PENGUIN_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "penguin"), "main");


    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wand"), WAND);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_bow"), TNT_BOW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "explosive_arrow"), EXPLOSIVE_ARROW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_sword"), TNT_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_pickaxe"), TNT_PICKAXE);

        FabricDefaultAttributeRegistry.register(PENGUIN, PenguinEntity.createMobAttributes());
        EntityModelLayerRegistry.registerModelLayer(MODEL_PENGUIN_LAYER, PenguinEntityModel::getTexturedModelData);

        ModPacketsC2S.register();
    }

}