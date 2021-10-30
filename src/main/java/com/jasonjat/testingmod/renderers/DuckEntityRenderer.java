package com.jasonjat.testingmod.renderers;

import com.jasonjat.testingmod.entities.DuckEntity;
import com.jasonjat.testingmod.model.DuckEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DuckEntityRenderer extends GeoEntityRenderer<DuckEntity> {
    public DuckEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DuckEntityModel());
        this.shadowRadius = 0.7F;
    }
}
