package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.renderers.ExplosiveArrowEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class TestingmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Testingmod.EXPLOSIVE_ARROW_ENTITY, ExplosiveArrowEntityRenderer::new);
    }
}