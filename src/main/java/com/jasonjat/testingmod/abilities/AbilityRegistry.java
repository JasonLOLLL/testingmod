package com.jasonjat.testingmod.abilities;

import com.jasonjat.testingmod.Testingmod;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AbilityRegistry {
    private static final Map<Identifier, Ability> ABILITIES = new LinkedHashMap<>();

    public static void init() {
        register("teleport", new TeleportAbility());
        register("explode", new ExplodeAbility());
        register("tunnel", new TunnelAbility());
    }

    private static void register(String abilityId, Ability ability) {
        ABILITIES.put(new Identifier(Testingmod.MOD_ID, abilityId), ability);
    }

    public static Ability get(Identifier id) {
        return ABILITIES.get(id);
    }

    public static Map<Identifier, Ability> getAbilities() {
        return ABILITIES;
    }
}
