package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.components.MyComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class ExplodeAbility extends Ability {

    {
        cooldown = 2;
    }

    public void use(ServerPlayerEntity player, Identifier id) {
        List<Identifier> idListPlayer = MyComponents.UNLOCKED_ABILITIES.get(player).getUnlockedAbilities();
        Vec3d pos = player.getPos();

        if (checkContains.test(idListPlayer, id)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 3));
            player.getServerWorld().spawnParticles(ParticleTypes.DOLPHIN, pos.getX(), pos.getY(), pos.getZ(), 10, 1, 1, 1, 1);
            player.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 1f, 1f);

            doCooldown(player, id);
        } else {
            System.out.println("player does not have explode ability");
        }
    }
}
