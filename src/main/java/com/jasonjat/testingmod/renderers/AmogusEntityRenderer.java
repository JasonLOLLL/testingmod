package com.jasonjat.testingmod.renderers;

import com.jasonjat.testingmod.entities.AmogusEntity;
import com.jasonjat.testingmod.model.AmogusEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AmogusEntityRenderer extends GeoEntityRenderer<AmogusEntity> {
    public AmogusEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AmogusEntityModel());
        this.shadowRadius = 0.2F;
    }
}
