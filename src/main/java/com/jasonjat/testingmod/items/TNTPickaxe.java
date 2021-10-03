package com.jasonjat.testingmod.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class TNTPickaxe extends PickaxeItem {
    private float power = 20;
    public TNTPickaxe() {
        super(ToolMaterials.NETHERITE, 10, 3, new Item.Settings().group(ItemGroup.TOOLS).maxDamage(1200));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Explosion.DestructionType d = Explosion.DestructionType.DESTROY;

        world.createExplosion(miner, pos.getX(), pos.getY(), pos.getZ(), power, d);
        world.createExplosion(miner, pos.getX(), pos.getY()-5, pos.getZ(), power, d);
        world.createExplosion(miner, pos.getX(), pos.getY()-10, pos.getZ(), power, d);
        world.createExplosion(miner, pos.getX(), pos.getY()-15, pos.getZ(), power, d);
        world.createExplosion(miner, pos.getX(), pos.getY()-20, pos.getZ(), power, d);
        world.createExplosion(miner, pos.getX(), pos.getY()-25, pos.getZ(), power, d);

        return super.postMine(stack, world, state, pos, miner);
    }
}
