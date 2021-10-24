package com.jasonjat.testingmod.mixins;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.abilities.Ability;
import com.jasonjat.testingmod.abilities.AbilityRegistry;
import com.jasonjat.testingmod.client.Keybinds;
import com.jasonjat.testingmod.components.MyComponents;
import com.jasonjat.testingmod.modpackets.ModPackets;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class HUDMixin extends DrawableHelper{

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow @Final private ItemRenderer itemRenderer;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    private MinecraftClient client = MinecraftClient.getInstance();

    private int count = 0;
    private final int maxCount = 6;

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

            List<Identifier> identifiers = MyComponents.UNLOCKED_ABILITIES.get(client.player).getUnlockedAbilities();

            while (Keybinds.keybindUse.wasPressed()) {
                if (count < identifiers.size()) {
                    Identifier id = MyComponents.UNLOCKED_ABILITIES.get(client.player).getUnlockedAbilities().get(count);
                    int currentCooldown = MyComponents.UNLOCKED_ABILITIES.get(client.player).getCooldown(id);

                    if (currentCooldown <= 0) {
                        PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
                        packetByteBuf.writeString("Use");
                        packetByteBuf.writeInt(count);

                        ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, packetByteBuf);
                    } else {
                        System.out.println(id + " on cooldown!");
                    }
                } else if (count >= AbilityRegistry.getAbilities().size()) {
                    PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
                    packetByteBuf.writeString("Use");
                    packetByteBuf.writeInt(count);

                    ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, packetByteBuf);
                }
            }

            TextRenderer textRenderer = getTextRenderer();

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            textRenderer.drawWithShadow(matrixStack, ""+count, 10, 10, 0xFFFFFF);
            textRenderer.drawWithShadow(matrixStack, ""+MyComponents.PLAYER_STATS.get(client.player).getLevel(), 10, 20, 0x25c42b);

            //itemRenderer.renderInGui(item, 10, 50);

            // points to a sprite texture or atlas

            // renders texture on screen

            int x = 10;
            int y = 58;

            RenderSystem.setShaderTexture(0, new Identifier("textures/gui/widgets.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1F);

            for (int i = 0; i<=maxCount; i++) {
                this.drawTexture(matrixStack, x, y+=22, 24, 23, 22, 22);
            }

            y = 83;

            //selected item
            RenderSystem.setShaderColor(1.0F, 1.0F, 2.0F, 1F);
            this.drawTexture(matrixStack, x, 80+(count*22), 24, 23, 22, 22);

            itemRenderer.renderGuiItemIcon(new ItemStack(Items.DIAMOND), x+3, y);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.DIAMOND_SWORD), x+3, y+=22);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.TNT), x+3, y+=22);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.GHAST_TEAR), x+3, y+=22);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.CACTUS), x+3, y+=22);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.GOLD_BLOCK), x+3, y+=22);
            itemRenderer.renderGuiItemIcon(new ItemStack(Items.RAIL), x+3, y+=22);

            // enum these stuff
            y = 85;
            textRenderer.drawWithShadow(matrixStack,"Hello there!", x+25, y,0xf51142);
            textRenderer.drawWithShadow(matrixStack,"What!", x+25, y+=22, 0xf51262);
            textRenderer.drawWithShadow(matrixStack,"LOL!", x+25, y+=22, 0xf41141);
            textRenderer.drawWithShadow(matrixStack,"COOL!", x+25, y+=22, 0xf21842);

            //my own sprite!
            RenderSystem.setShaderTexture(0, new Identifier(Testingmod.MOD_ID, "textures/gui/hud/sprite_atlas.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexture(matrixStack, 30, 58, 0,0, 22, 22);
            this.drawTexture(matrixStack, 60, 58, 23,0, 22, 22);
            this.drawTexture(matrixStack, 90, 58, 0,24, 32, 32);
            this.drawTexture(matrixStack, 120, 58, 33,24, 32, 32);
            this.drawTexture(matrixStack, 150, 58, 66,24, 32, 32);

            //drawSprite()
            // have keybind open complete new inventory with custom textures no more cotton :(
            // also have textures in bottom left and figure out a good gui design

            drawHorizontalLine(matrixStack, 10, 100, 100, 0xFFFFFF);
            RenderSystem.disableBlend();
        }
    }
}
