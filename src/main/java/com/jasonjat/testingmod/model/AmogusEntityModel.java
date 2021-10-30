package com.jasonjat.testingmod.model;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.AmogusEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AmogusEntityModel extends AnimatedGeoModel<AmogusEntity> {
    @Override
    public Identifier getModelLocation(AmogusEntity object) {
        return new Identifier(Testingmod.MOD_ID, "geo/amogus.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AmogusEntity object) {
        return new Identifier(Testingmod.MOD_ID, "textures/entity/mobs/amogus.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AmogusEntity animatable) {
        return new Identifier(Testingmod.MOD_ID, "animations/amogus.animation.json");
    }
}
