package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.components.MyComponents;
import com.jasonjat.testingmod.modpackets.ModPacketsC2S;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class TeleportAbility extends Ability {

    {
        cooldown = 5;
        levelUnlocked = 5;
    }

    public void use(ServerPlayerEntity player, Identifier id) {
        Vec3d pos = player.getPos();

        player.teleport(pos.getX(), pos.getY()+10, pos.getZ());
        player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
        doCooldown(player, id);
    }
}
