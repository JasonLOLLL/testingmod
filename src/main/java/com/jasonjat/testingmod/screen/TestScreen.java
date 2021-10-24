package com.jasonjat.testingmod.screen;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.screen.Widgets.WireWidget;
import com.jasonjat.testingmod.util.Vec2;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.http.impl.conn.Wire;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class TestScreen extends Screen {

    private static final Identifier WINDOW = new Identifier(Testingmod.MOD_ID, "textures/gui/main/basic_frame.png");
    private final int backgroundWidth = 160;
    private final int backgroundHeight = 112;

    public static final List<Vec2 > connectSprites = new ArrayList<>();
    public final List<WireWidget > wireWidgets = new ArrayList<>();

    static {
        connectSprites.add(new Vec2(450,150));
        connectSprites.add(new Vec2(450,200));
        connectSprites.add(new Vec2(450,250));
        connectSprites.add(new Vec2(450,100));
    }

    public TestScreen() {
        super(Text.of("Thy Mother"));
    }

    @Override
    protected void init() {
        super.init();
        // 1st
        wireWidgets.add(addDrawableChild(new WireWidget(200, connectSprites.get(0).y,32,32, Text.of(""), 3, b -> {
            System.out.println("HELLO! BUTTON!");
        })));

        //2nd
        wireWidgets.add(addDrawableChild(new WireWidget(200,connectSprites.get(1).y,32,32, Text.of(""), 1, b -> {
            System.out.println("HELLO! BUTTON!");
        })));

        wireWidgets.add(addDrawableChild(new WireWidget(200,connectSprites.get(2).y,32,32, Text.of(""), 2, b -> {
            System.out.println("HELLO! BUTTON!");
        })));

        wireWidgets.add(addDrawableChild(new WireWidget(200,connectSprites.get(3).y,32,32, Text.of(""), 0, b -> {
            System.out.println("HELLO! BUTTON!");
        })));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        renderTestScreenBackground(matrices);
        renderTextureSprites(matrices);


        // at the end pls
        super.render(matrices, mouseX, mouseY, delta);
    }

    private void renderTestScreenBackground(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, WINDOW);
        

        this.drawTexture(matrices, (this.width-this.backgroundWidth)/2,(this.height-this.backgroundHeight)/2,0,0, this.backgroundWidth, this.backgroundHeight);
    }

    private void renderTextureSprites(MatrixStack matrices) {
        RenderSystem.setShaderTexture(0, new Identifier(Testingmod.MOD_ID, "textures/gui/hud/sprite_atlas.png"));
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        for (Vec2 v : connectSprites) {
            this.drawTexture(matrices, v.x, v.y, 0,0, 22, 22);
        }

        for (WireWidget w : wireWidgets) {
            drawLine(matrices, w.originPosition.x, w.originPosition.y, w.x + w.getWidth()/2, w.y + w.getHeight()/2);
        }
    }

    public void drawLine(MatrixStack matrices, int x1, int y1, int x2, int y2) {
        double x = x1;
        double y = y1;
        int dx = x2-x1;
        int dy = y2-y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        double Xincrement = (double) dx / steps;
        double Yincrement = (double) dy / steps;

        for (int i = 0; i<steps; i++) {
            x = x + Xincrement;
            y = y + Yincrement;
            drawPixel(matrices, (int) Math.round(x), (int) Math.round(y));
        }
    }

    private void drawPixel(MatrixStack matrices, int x, int y) {
        int thickness = 10;
        fill(matrices, x, y, x+thickness, y+thickness, -2123232);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for (WireWidget w : wireWidgets) {
            if (w.isClicking()) {
                w.setPosition(mouseX, mouseY);
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
