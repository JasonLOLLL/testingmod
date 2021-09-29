package com.jasonjat.testingmod.mixins;

import com.jasonjat.testingmod.client.Keybinds;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class HUDMixin extends DrawableHelper{

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow @Final private ItemRenderer itemRenderer;
    private MinecraftClient client;

    private int count = 0;

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "TAIL"))
    private void renderInfo(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        if (client.player != null && client.world != null) {
            TextRenderer textRenderer = getTextRenderer();



            if (Keybinds.keybindC.wasPressed()) {
                count++;
            }

            textRenderer.drawWithShadow(matrixStack, ""+count, 10, 10, 0xFFFFFF);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            ItemStack item = new ItemStack(Items.DIAMOND);

            itemRenderer.renderInGui(item, 10, 50);

            // points to a sprite texture or atlas
            RenderSystem.setShaderTexture(0, new Identifier("testingmod:textures/gui/hud/sprite.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);


            int s = 22;
            int r = 22;
            int t = 0;

            // renders texture on screen
            this.drawTexture(matrixStack, s, r, 0, 0, 20, 20);
            this.drawTexture(matrixStack, s, r+20, 25, 3, 15, 18);
            //this.drawTexture(matrixStack, s, r + 18 - t, 18, 112 - t, 18, t);
            RenderSystem.disableBlend();
        }
    }
}
