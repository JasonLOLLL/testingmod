package com.jasonjat.testingmod.renderers;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.EnderArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EnderArrowEntityRenderer extends ProjectileEntityRenderer<EnderArrowEntity> {
    public static final Identifier TEXTURE = new Identifier(Testingmod.MOD_ID, "textures/entity/projectiles/explosive_arrow.png");

    public EnderArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(EnderArrowEntity entity) {
        return TEXTURE;
    }
}
