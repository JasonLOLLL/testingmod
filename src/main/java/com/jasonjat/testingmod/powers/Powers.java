package com.jasonjat.testingmod.powers;

import com.jasonjat.testingmod.abilities.Ability;

import java.util.ArrayList;

public abstract class Powers {
    protected ArrayList<String> abilities = new ArrayList<>();

    public void register() {
        abilities.add("Explode");
        abilities.add("Kill");
        abilities.add("Diamond");
    }
}
