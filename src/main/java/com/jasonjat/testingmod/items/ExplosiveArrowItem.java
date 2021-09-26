package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.entities.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class ExplosiveArrowItem extends TippedArrowItem {
    public ExplosiveArrowItem() {
        super(new Item.Settings().maxCount(64).group(ItemGroup.COMBAT));
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        System.out.println("createArrow() called!");
        ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(world, shooter);
        arrowEntity.setDamage(100);
        arrowEntity.initFromStack(stack);
        return arrowEntity;
    }
}
