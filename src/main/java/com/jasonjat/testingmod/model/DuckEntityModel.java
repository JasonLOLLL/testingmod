package com.jasonjat.testingmod.model;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.DuckEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DuckEntityModel extends AnimatedGeoModel<DuckEntity> {
    @Override
    public Identifier getModelLocation(DuckEntity object) {
        return new Identifier(Testingmod.MOD_ID, "geo/duck.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DuckEntity object) {
        return new Identifier(Testingmod.MOD_ID, "textures/entity/mobs/duck_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DuckEntity animatable) {
        return new Identifier(Testingmod.MOD_ID, "animations/duck.animation.json");
    }
}
