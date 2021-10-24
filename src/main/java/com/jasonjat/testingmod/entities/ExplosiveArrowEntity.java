package com.jasonjat.testingmod.entities;

import com.jasonjat.testingmod.Testingmod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplosiveArrowEntity extends PersistentProjectileEntity {
    private int duration = 200;

    public ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(World world, double x, double y, double z) {
        super(Testingmod.EXPLOSIVE_ARROW_ENTITY, x, y, z, world);
    }

    public ExplosiveArrowEntity(LivingEntity owner, World world) {
        super(Testingmod.EXPLOSIVE_ARROW_ENTITY, owner, world);
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Testingmod.EXPLOSIVE_ARROW);
    }

    public void tick() { //updates
        super.tick();
        if (this.world.isClient && !this.inGround) {
            this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    public void onHit(LivingEntity target) {
        super.onHit(target);
//        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.GLOWING, this.duration, 0);
//        target.addStatusEffect(statusEffectInstance, this.getEffectCause());

        explode(target.getBlockPos());
    }

    @Override
    protected void onBlockHit(BlockHitResult bhr) {
        super.onBlockHit(bhr);

        explode(bhr.getBlockPos());
        this.discard();
    }

    private void explode(BlockPos pos) {
        world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 10f, Explosion.DestructionType.DESTROY);
        if (this.world.isClient) {
            this.world.addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.5D, 0.5D, 0.5D);
        }
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
