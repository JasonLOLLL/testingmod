package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.Testingmod;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TNT_Bow extends BowItem {

    public TNT_Bow() {
        super(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(10));
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//
//        boolean bl = false;
//        ItemStack itemStack = user.getStackInHand(hand);
//
//        for(int i = 0; i < user.getInventory().size(); ++i) {
//            ItemStack itemStack2 = user.getInventory().getStack(i);
//            if (itemStack2.getItem() == Testingmod.EXPLOSIVE_ARROW) {
//                bl = true;
//                break;
//            }
//        }
//
//        if (!user.getAbilities().creativeMode && !bl) {
//            System.out.println("Has been failed");
//            return TypedActionResult.fail(itemStack);
//        } else {
//            user.setCurrentHand(hand);
//            System.out.println("Has been consumed");
//            return TypedActionResult.consume(itemStack); //replaced hand
//        }
//    }
//
//    @Override
//    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
//        super.onStoppedUsing(new ItemStack(Testingmod.EXPLOSIVE_ARROW), world, user, remainingUseTicks);
//        System.out.println("PLEASE CALLE!!!!!!");
//    }
}
