package com.jasonjat.testingmod.entities;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.items.ExplosiveArrowItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends PersistentProjectileEntity {
    private int duration = 200;

    public ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity owner) {
        super(Testingmod.EXPLOSIVE_ARROW_ENTITY, owner, world);
    }

    public ExplosiveArrowEntity(World world, double x, double y, double z) {
        super(Testingmod.EXPLOSIVE_ARROW_ENTITY, x, y, z, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Testingmod.EXPLOSIVE_ARROW);
    }

    public void tick() {
        super.tick();
        if (this.world.isClient && !this.inGround) {
            this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    public void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.GLOWING, this.duration, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Duration")) {
            this.duration = nbt.getInt("Duration");
        }

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Duration", this.duration);
    }
}
