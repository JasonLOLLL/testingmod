package com.jasonjat.testingmod.abilities;

import net.minecraft.entity.player.PlayerEntity;

public class Ability {
    private String name;
    private int cooldown;
    private PlayerEntity owner;

    public Ability(String name, int cooldown, PlayerEntity owner) {
        this.name = name;
        this.cooldown = cooldown;
        this.owner = owner;
    }

    public void useAbility() {
        // blah blah blah
    }
}
