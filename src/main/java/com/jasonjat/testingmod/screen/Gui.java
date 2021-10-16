package com.jasonjat.testingmod.screen;

import com.jasonjat.testingmod.modpackets.ModPackets;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.LiteralText;

public class Gui extends LightweightGuiDescription {

    public Gui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(100, 100);

        WTextField wTextField = new WTextField();
        root.add(wTextField, 0,2,20,20);

        WButton button2 = new WButton(new LiteralText("Send"));
        root.add(button2, 0, 1, 20, 20);

        button2.setOnClick(() -> {
            // sends text to server
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeString(wTextField.getText());

            ClientPlayNetworking.send(ModPackets.GUI_PACKET, buf);
        });
    }
}
