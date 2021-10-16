package com.jasonjat.testingmod.abilities;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;

public class ExplodeAbility extends Ability {

    static {
        cooldown = 2;
    }

    public static void use(ServerPlayerEntity player) {
        Vec3d pos = player.getPos();
        player.getEntityWorld().createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 5F, Explosion.DestructionType.DESTROY);
    }
}
