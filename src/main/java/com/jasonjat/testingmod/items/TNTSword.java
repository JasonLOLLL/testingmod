package com.jasonjat.testingmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class TNTSword extends SwordItem {

    public TNTSword() {
        super(ToolMaterials.NETHERITE, 100, 0.2f, new Item.Settings().maxDamage(800).group(ItemGroup.COMBAT));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        System.out.println("Hello");
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.getEntityWorld().createExplosion(attacker, target.getX(), target.getY(), target.getZ(), 10f, Explosion.DestructionType.DESTROY);

        return super.postHit(stack, target, attacker);
    }
}
