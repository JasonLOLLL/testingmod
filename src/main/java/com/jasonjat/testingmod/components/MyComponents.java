package com.jasonjat.testingmod.components;

import com.jasonjat.testingmod.Testingmod;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public final class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<RandomIntComponent> MAGIK = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Testingmod.MOD_ID, "magik"), RandomIntComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MAGIK, RandomIntComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }

}
