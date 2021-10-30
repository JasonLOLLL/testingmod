package com.jasonjat.testingmod.screen;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.modpackets.ModPackets;
import com.jasonjat.testingmod.screen.Widgets.PointerWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SliderChallengeScreen extends ChallengeScreen {

    public static final Identifier TEXTURE = new Identifier(Testingmod.MOD_ID, "textures/gui/challenges/slider_atlas.png");
    public static final int windowWidth = 256;
    public static final int windowHeight = 48;
    private ButtonWidget stop;
    private PointerWidget pointer;
    boolean slidingRight = true;
    boolean stopped = false;

    public SliderChallengeScreen() {
        super(Text.of("slider"));
    }

    @Override
    protected void init() {
        super.init();
        stop = addDrawableChild(new ButtonWidget(width/2 - 25, height/2, 50, 20, Text.of("stop!"), button -> {
            stopped = true;
            endGame();
        }));
        pointer = addDrawable(new PointerWidget((width-windowWidth)/2, (height-22)/2 - 15));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        renderWindow(matrices);
        renderSprites(matrices);

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void renderWindow(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);

        if (!stopped) {
            this.drawTexture(matrices, (width - windowWidth) / 2, (height - windowHeight) / 2 - 50, 0, 0, windowWidth, windowHeight);
        }
    }

    private void renderSprites(MatrixStack matrices) {
        if (!stopped) {
            if (slidingRight) {
                pointer.x++;
            } else {
                pointer.x--;
            }
            if (pointer.x + 32 - (width - windowWidth) / 2 > windowWidth) {
                slidingRight = false;
            } else if (pointer.x - (width - windowWidth) / 2 == 0) {
                slidingRight = true;
            }
        }
    }

    private void endGame() {
        int i;
        // red
        int pointerLocation = pointer.x + 16 - (width - windowWidth) / 2;
        if ((pointerLocation >= 0 && pointerLocation <= 64) || (pointerLocation >= 192 && pointerLocation <= 256)) {
            i = 1;
        } else if ((pointerLocation > 64 && pointerLocation < 112) || (pointerLocation > 144 && pointerLocation < 192)) {
            i = 2;
        } else {
            i = 3;
        }

        // send packet here


        stop.visible = false;
        pointer.visible = false;

        ClientPlayNetworking.send(ModPackets.GUI_PACKET, new PacketByteBuf(Unpooled.buffer()));
        client.setScreen(null);
    }
}
