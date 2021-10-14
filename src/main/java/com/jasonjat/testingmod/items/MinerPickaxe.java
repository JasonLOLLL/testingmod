package com.jasonjat.testingmod.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MinerPickaxe extends PickaxeItem {
    public MinerPickaxe() {
        super(ToolMaterials.NETHERITE, 5, 1f, new Item.Settings().maxDamage(800).group(ItemGroup.TOOLS));
    }



    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        // if facing an opposite direction, x or z value is inverted
        int dirX = miner.getHorizontalFacing() == Direction.WEST ? -1 : 1;
        int dirZ = miner.getHorizontalFacing() == Direction.NORTH ? -1 : 1;
        boolean dropItems = false;

        for (int y = -1; y < 2; y++) { //y
            for (int v = 0; v < 3; v++) { // x or z
                for (int c = -1; c<2; c++) { // x or z
                    if (miner.getHorizontalFacing() == Direction.NORTH || miner.getHorizontalFacing() == Direction.SOUTH) { //checks bc x and y are inverted between N&S and E&W
                        world.breakBlock(pos.add(c, y, (v * dirZ)), dropItems);
                    } else {
                        world.breakBlock(pos.add((v*dirX), y, c), dropItems);
                    }
                }
            }
        }

        return super.postMine(stack, world, state, pos, miner);
    }
}
