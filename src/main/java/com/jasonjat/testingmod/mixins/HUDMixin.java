package com.jasonjat.testingmod.mixins;

import com.jasonjat.testingmod.client.Keybinds;
import com.jasonjat.testingmod.modpackets.ModPackets;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
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
    private final int maxCount = 3;

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At(value = "TAIL"))
    private void renderInfo(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        if (client.player != null && client.world != null) {

            while (Keybinds.keybindSelect.wasPressed()) {
                if (count + 1 > maxCount) {
                    count = 0;
                } else {
                    count++;
                }
            }

            while (Keybinds.keybindUse.wasPressed()) {
                PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
                packetByteBuf.writeString("Use");
                packetByteBuf.writeInt(count);

                ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, packetByteBuf);
            }

            TextRenderer textRenderer = getTextRenderer();

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            textRenderer.drawWithShadow(matrixStack, ""+count, 10, 10, 0xFFFFFF);

            //itemRenderer.renderInGui(item, 10, 50);

            // points to a sprite texture or atlas

            // renders texture on screen

            int x = 10;
            int y = 80;

            RenderSystem.setShaderTexture(0, new Identifier("textures/gui/widgets.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);
            this.drawTexture(matrixStack, x, y, 24, 23, 22, 22);
            this.drawTexture(matrixStack, x, y+=22, 24, 23, 22, 22);
            this.drawTexture(matrixStack, x, y+=22, 24, 23, 22, 22);
            this.drawTexture(matrixStack, x, y+=22, 24, 23, 22, 22);

            y = 80;

            //selected item
            RenderSystem.setShaderColor(1.0F, 1.0F, 2.0F, 1F);
            this.drawTexture(matrixStack, x, 80+(count*22), 24, 23, 22, 22);

            itemRenderer.renderGuiItemIcon(new ItemStack(Items.DIAMOND), x+3, y+3);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.DIAMOND_SWORD), x+3, y+22+3);

            // enum these stuff
            textRenderer.drawWithShadow(matrixStack, "Suskiller ability", x+25, 85, 0xf51142);

            RenderSystem.disableBlend();
        }
    }
}
