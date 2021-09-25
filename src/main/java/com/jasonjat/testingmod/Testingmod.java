package com.jasonjat.testingmod;

import com.jasonjat.testingmod.entities.ExplosiveArrowEntity;
import com.jasonjat.testingmod.items.ExplosiveArrowItem;
import com.jasonjat.testingmod.items.TNT_Bow;
import com.jasonjat.testingmod.items.Wand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Testingmod implements ModInitializer {

    public static final String MOD_ID = "testingmod";
    public static final Item WAND = new Wand();
    public static final Item TNT_BOW = new TNT_Bow();
    public static final Item EXPLOSIVE_ARROW = new ExplosiveArrowItem();
    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "explosive_arrow"),
            FabricEntityTypeBuilder.<ExplosiveArrowEntity>create(SpawnGroup.MISC, ExplosiveArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wand"), WAND);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "tnt_bow"), TNT_BOW);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "explosive_arrow"), EXPLOSIVE_ARROW);

    }

}