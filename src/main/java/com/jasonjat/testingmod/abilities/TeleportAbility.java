package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.components.MyComponents;
import com.jasonjat.testingmod.modpackets.ModPacketsC2S;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class TeleportAbility extends Ability {

    static {
        cooldown = 5;
    }

    public static void use(ServerPlayerEntity player) {
        List<Identifier> idListPlayer = MyComponents.UNLOCKED_ABILITIES.get(player).getUnlockedAbilities();
        Vec3d pos = player.getPos();

        if (ModPacketsC2S.checkContains.test(idListPlayer, new Identifier("teleport"))) {
            player.teleport(pos.getX(), pos.getY()+10, pos.getZ());
            player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
        } else {
            System.out.println("player does not have teleport ability unlocked");
        }
    }
}
