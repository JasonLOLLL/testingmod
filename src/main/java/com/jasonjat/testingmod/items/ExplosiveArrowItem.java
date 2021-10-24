package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.entities.EnderArrowEntity;
import com.jasonjat.testingmod.entities.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem() {
        super(new Item.Settings().maxCount(64).group(ItemGroup.COMBAT));
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        System.out.println("createArrow() called!");
        ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(shooter, world);
        arrowEntity.setDamage(100);
        return arrowEntity;
    }
}
