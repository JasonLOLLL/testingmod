package com.jasonjat.testingmod.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;

public class ExplodeAbility extends Ability {

    /* i am missing so much knowledge
    how to structure this whole thing
    design patterns and knowing how to manage data via data structures...
    idkkdkk im too dumb right now
     */

    public ExplodeAbility(PlayerEntity owner) {
        super("Test", 5, owner);
    }

    @Override
    public void useAbility() {
        Vec3d pos = owner.getPos();
        owner.getEntityWorld().createExplosion(owner, pos.getX(), pos.getY(), pos.getZ(), 5F, Explosion.DestructionType.DESTROY);
    }
}
