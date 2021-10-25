package com.jasonjat.testingmod.abilities;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class HealAbility extends Ability {

    {
        cooldown = 10;
        levelUnlocked = 20;
    }

    @Override
    public void use(ServerPlayerEntity player, Identifier id) {
        player.heal(player.getMaxHealth());
        player.getHungerManager().setFoodLevel(20);
        doCooldown(player, id);
    }
}
