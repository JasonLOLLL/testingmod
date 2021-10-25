package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.modpackets.ModPackets;
import com.jasonjat.testingmod.screen.SliderChallengeScreen;
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

    public static KeyBinding keybindSelect = KeyBindingHelper.registerKeyBinding(new KeyBinding(
       "key.testingmod.cbind",
       InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "category.testingmod.custombindings"
    ));

    public static KeyBinding keybindUse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.testingmod.usebind",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.testingmod.custombindings"
    ));

    public static KeyBinding keybindOpen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.testingmod.openbind",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category.testingmod.custombindings"
    ));

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keybindOpen.wasPressed()) {
                client.setScreen(new SliderChallengeScreen());
            }
        });
    }
}
