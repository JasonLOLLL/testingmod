package com.jasonjat.testingmod.screen.Widgets;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.screen.TestScreen;
import com.jasonjat.testingmod.util.Vec2;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WireWidget extends ButtonWidget {
    public static final Identifier TEXTURE = new Identifier(Testingmod.MOD_ID, "textures/gui/hud/sprite_atlas.png");
    private boolean clicking;
    private boolean draggable = true;
    public final int match;
    public final Vec2 originPosition;

    public WireWidget(int x, int y, int width, int height, Text message, int match, PressAction onPress) {
        super(x, y, width, height, message, onPress);
        originPosition = new Vec2(x, y);
        this.match = match;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        setPosition(mouseX, mouseY);
        clicking = true;
        super.onClick(mouseX, mouseY);
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        clicking = false;
        if (draggable) {
            this.x = originPosition.x;
            this.y = originPosition.y;
            super.onRelease(mouseX, mouseY);
        }
    }

    public boolean isClicking() {
        return clicking;
    }

    public boolean setPosition(double mouseX, double mouseY) {
        if (draggable) {
            Vec2 spriteV = findMatchingSpriteVec2();
            int spriteX = spriteV.x;
            int spriteY = spriteV.y;

            int midX = (int) mouseX - this.width / 2;
            int midY = (int) mouseY - this.height / 2;
            this.x = midX;
            this.y = midY;
            if ((this.x + width/2 > spriteX && this.x+ width/2 < spriteX+32) && (this.y +height/2 > spriteY && this.y+height/2 < spriteY + 32)) {
                this.x = spriteX;
                this.y = spriteY;
                setDraggable(false);
                System.out.println("reached destination, dragging is now disabled.");
            }
            return true;
        }
        return false;
    }

    private Vec2 findMatchingSpriteVec2() {
        return TestScreen.connectSprites.get(match);
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        this.drawTexture(matrices, this.x, this.y, 0, 24, 32, 32);
    }
}
