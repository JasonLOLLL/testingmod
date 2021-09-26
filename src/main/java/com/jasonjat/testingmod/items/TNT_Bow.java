package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.Testingmod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class TNT_Bow extends BowItem {

    public TNT_Bow() {
        super(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(800));
    }



    @Override //override this arrow jawn and return the predicate of if there is an explosive arrow in the inventory
    public Predicate<ItemStack> getProjectiles() {
        return (stack) -> {
            return stack.getItem() == Testingmod.EXPLOSIVE_ARROW;
        };
    }
}
