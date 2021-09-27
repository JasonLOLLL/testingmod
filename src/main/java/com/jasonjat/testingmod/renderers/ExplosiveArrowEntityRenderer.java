package com.jasonjat.testingmod.renderers;

import com.jasonjat.testingmod.entities.ExplosiveArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ExplosiveArrowEntityRenderer extends ProjectileEntityRenderer<ExplosiveArrowEntity> {
    public static final Identifier TEXTURE = new Identifier("textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(ExplosiveArrowEntity entity) {
        System.out.println("texture is being called");
        return TEXTURE;
    }
}
