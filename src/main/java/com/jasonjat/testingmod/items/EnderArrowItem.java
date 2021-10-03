package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.entities.EnderArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderArrowItem extends ArrowItem {
    public EnderArrowItem() {
        super(new Item.Settings().maxCount(64).group(ItemGroup.COMBAT));
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        EnderArrowEntity arrowEntity = new EnderArrowEntity(world, shooter);
        arrowEntity.setDamage(100);
        return arrowEntity;
    }
}
