package com.jasonjat.testingmod.screen;

import com.jasonjat.testingmod.Testingmod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TestScreen extends Screen {

    private static final Identifier WINDOW = new Identifier("textures/gui/container/hopper.png");

    public TestScreen() {
        super(Text.of("Thy Mother"));
    }

    @Override
    protected void init() {
        super.init();
        addDrawableChild(new ButtonWidget(250,100,50,20, Text.of("Button"), b -> {
            System.out.println("HELLO! BUTTON!");
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        super.renderBackground(matrices);
        this.renderWindowBackground(matrices);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }



    private void renderWindowBackground(MatrixStack matrices) {
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, WINDOW);

        this.drawTexture(matrices, this.width/2 - 50,this.height/2 - 40,0,0, 176,133);
        RenderSystem.disableBlend();
    }
}
