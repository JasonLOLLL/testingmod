package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.items.TNT_Bow;
import com.jasonjat.testingmod.renderers.ExplosiveArrowEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class TestingmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Testingmod.EXPLOSIVE_ARROW_ENTITY, ExplosiveArrowEntityRenderer::new);

        FabricModelPredicateProviderRegistry.register(Testingmod.TNT_BOW, new Identifier("pull"), (itemStack, clientWorld, livingEntity, x) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        FabricModelPredicateProviderRegistry.register(Testingmod.TNT_BOW, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, x) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}