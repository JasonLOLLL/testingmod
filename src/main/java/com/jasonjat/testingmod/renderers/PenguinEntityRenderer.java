package com.jasonjat.testingmod.renderers;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.PenguinEntity;
import com.jasonjat.testingmod.model.PenguinEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

// rendering the entity (not really its more like texturing the entity)
public class PenguinEntityRenderer extends MobEntityRenderer<PenguinEntity, PenguinEntityModel> {
    public static final Identifier TEXTURE = new Identifier(Testingmod.MOD_ID, "textures/entity/mobs/cube.png");

    public PenguinEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PenguinEntityModel(context.getPart(Testingmod.MODEL_PENGUIN_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(PenguinEntity entity) {
        return TEXTURE;
    }
}
