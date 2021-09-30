package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.modpackets.ModPackets;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Keybinds {

    public static KeyBinding keybindC = KeyBindingHelper.registerKeyBinding(new KeyBinding(
       "key.testingmod.cbind",
       InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "category.testingmod.custombindings"
    ));

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keybindC.wasPressed()) {
                PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
                packetByteBuf.writeString("C");
                ClientPlayNetworking.send(ModPackets.KEYBIND_PACKET, packetByteBuf);

                MinecraftClient.getInstance().player.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f, 1f);
            }
        });
    }
}
