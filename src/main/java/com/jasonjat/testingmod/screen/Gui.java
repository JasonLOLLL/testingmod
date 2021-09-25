package com.jasonjat.testingmod.screen;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.text.LiteralText;

public class Gui extends LightweightGuiDescription {
    public Gui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(100,100);

        WButton button = new WButton(new LiteralText("Whats up"));
        root.add(button, 0, 0, 20, 20);

        button.setOnClick(() -> {
            System.out.println("LOL");
        });

        WButton button2 = new WButton(new LiteralText("Hi guys"));
        root.add(button2, 20,20,20,20);

        button2.setOnClick(() -> {
            System.out.println("Client only, but server if used packets.");
        });
    }
}
