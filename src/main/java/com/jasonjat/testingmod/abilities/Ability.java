package com.jasonjat.testingmod.abilities;

import net.minecraft.entity.player.PlayerEntity;

public abstract class Ability {
    protected String name;
    protected int cooldown;
    protected PlayerEntity owner;

    public Ability(String name, int cooldown, PlayerEntity owner) {
        this.name = name;
        this.cooldown = cooldown;
        this.owner = owner;
    }

    public abstract void useAbility();
}
