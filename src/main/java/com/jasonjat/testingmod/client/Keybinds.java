package com.jasonjat.testingmod.client;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.AmogusEntity;
import com.jasonjat.testingmod.screen.SliderChallengeScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
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
                AmogusEntity amogus = new AmogusEntity(Testingmod.AMOGUS_ENTITY, client.player.world);
                amogus.setPosition(client.player.getPos());
            }
        });
    }
}
