package com.jasonjat.testingmod.mixins;

import com.jasonjat.testingmod.Testingmod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class HUDMixin extends DrawableHelper{

    @Shadow public abstract TextRenderer getTextRenderer();

    private MinecraftClient client;

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "TAIL"))
    private void renderInfo(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        if (client.player != null && client.world != null) {
            TextRenderer textRenderer = getTextRenderer();

            textRenderer.drawWithShadow(matrixStack, "Hello sir", 10, 10, 0xFFFFFF);
            //idk what this does?
//            RenderSystem.enableBlend();

            // points to a sprite texture


            // renders the texture on screen? idk
//            drawSprite(matrixStack, 20, 20, 0, 10, 10, texture);
        }
    }
}
