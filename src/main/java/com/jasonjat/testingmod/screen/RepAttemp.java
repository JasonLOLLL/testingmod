package com.jasonjat.testingmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class RepAttemp extends Screen {

    private ButtonWidget myButton;

    public RepAttemp(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        // adds but does not render
        myButton = addDrawableChild(new ButtonWidget(120, 120, 30, 15, Text.of("Hi!"), button -> System.out.println("YAY!")));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BookScreen.BOOK_TEXTURE);
        RenderSystem.setShaderColor(1F,1F,1F,1F);

        this.drawTexture(matrices, 100, 100, 0,0, 192, 192);

        // PUT THIS AT THE END BECAUSE THIS RENDERS THE BUTTONS!
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
