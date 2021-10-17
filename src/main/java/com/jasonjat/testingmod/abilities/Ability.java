package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.components.MyComponents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.BiPredicate;

public abstract class Ability {
    protected String name;
    protected int cooldown;
    protected double levelUnlocked;

    public static final BiPredicate<List<Identifier>, Identifier> checkContains = List::contains;


    public abstract void use(ServerPlayerEntity player, Identifier id);

    public int getCooldown() {
        return cooldown;
    }
    public double getLevelUnlocked() {return levelUnlocked; }

    public void doCooldown(ServerPlayerEntity player, Identifier id) {
        MyComponents.UNLOCKED_ABILITIES.get(player).setCooldown(id, cooldown);
        MyComponents.UNLOCKED_ABILITIES.sync(player);
    }
}
