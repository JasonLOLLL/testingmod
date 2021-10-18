package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.components.MyComponents;
import com.jasonjat.testingmod.modpackets.ModPacketsC2S;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class TeleportAbility extends Ability {

    {
        cooldown = 1;
        levelUnlocked = 5;
    }

    public void use(ServerPlayerEntity player, Identifier id) {
        Vec3d rayPos = player.raycast(50, 1, false).getPos();
        BlockPos blockPos = new BlockPos(rayPos);

        if (!player.getEntityWorld().getBlockState(blockPos).isAir()) {
            player.teleport(rayPos.getX(), rayPos.getY(), rayPos.getZ());
            player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
            player.getServerWorld().spawnParticles(ParticleTypes.ENTITY_EFFECT, rayPos.getX(), rayPos.getY(), rayPos.getZ(), 15,1,1,1,1);
            doCooldown(player, id);
        }
    }
}
