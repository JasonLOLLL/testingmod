package com.jasonjat.testingmod.screen;

import com.jasonjat.testingmod.modpackets.ModPackets;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
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
        root.add(button2, 0,2,20,20);

        button2.setOnClick(() -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeString("Hi");
            System.out.println("Client only, but server if used packets.");
            ClientPlayNetworking.send(ModPackets.GUI_PACKET, buf);
        });


    }
}
