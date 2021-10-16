package com.jasonjat.testingmod.abilities;

public abstract class Ability {
    static protected String name;
    static protected int cooldown;

    public static int getCooldown() {
        return cooldown;
    }
}
