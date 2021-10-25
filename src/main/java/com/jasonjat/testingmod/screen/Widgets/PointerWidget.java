package com.jasonjat.testingmod.screen.Widgets;

import com.jasonjat.testingmod.screen.SliderChallengeScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;

public class PointerWidget extends DrawableHelper implements Element, Drawable {
    public int x;
    public int y;
    public boolean visible = true;

    public PointerWidget(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (visible) {
            RenderSystem.setShaderTexture(0, SliderChallengeScreen.TEXTURE);
            drawTexture(matrices, this.x, this.y, 0, 48, 32, 22);
        }
    }
}
