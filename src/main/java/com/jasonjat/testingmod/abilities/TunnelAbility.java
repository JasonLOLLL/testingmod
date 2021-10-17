package com.jasonjat.testingmod.abilities;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class TunnelAbility extends Ability {

    {
        cooldown = 1;
        levelUnlocked = 15;
    }

    @Override
    public void use(ServerPlayerEntity player, Identifier id) {
        Vec3d pos = player.getPos();

        player.getEntityWorld().breakBlock(player.getBlockPos().add(0,-1,0), true, player);
        player.getServerWorld().spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 15,1,1,1,1);

        doCooldown(player, id);
    }
}
