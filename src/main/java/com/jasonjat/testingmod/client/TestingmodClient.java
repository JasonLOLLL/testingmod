package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.renderers.EnderArrowEntityRenderer;
import com.jasonjat.testingmod.renderers.ExplosiveArrowEntityRenderer;
import com.jasonjat.testingmod.renderers.PenguinEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TestingmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Testingmod.EXPLOSIVE_ARROW_ENTITY, ExplosiveArrowEntityRenderer::new);
        EntityRendererRegistry.register(Testingmod.ENDER_ARROW_ENTITY, EnderArrowEntityRenderer::new);

        Keybinds.register();

        // stuff for detecting bow pulling... predicates
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


        EntityRendererRegistry.register(Testingmod.PENGUIN, PenguinEntityRenderer::new);
    }
}