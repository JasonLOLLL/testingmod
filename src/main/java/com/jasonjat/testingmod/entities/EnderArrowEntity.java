package com.jasonjat.testingmod.entities;

import com.jasonjat.testingmod.Testingmod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class EnderArrowEntity extends PersistentProjectileEntity {
    private int duration = 200;

    public EnderArrowEntity(EntityType<? extends EnderArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public EnderArrowEntity(World world, double x, double y, double z) {
        super(Testingmod.ENDER_ARROW_ENTITY, x, y, z, world);
    }

    public EnderArrowEntity(World world, LivingEntity shooter) {
        super(Testingmod.ENDER_ARROW_ENTITY, shooter, world);
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Testingmod.ENDER_ARROW);
    }

    public void tick() { //updates
        super.tick();
        if (this.world.isClient && !this.inGround) {
            this.world.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    public void onHit(LivingEntity target) {
        super.onHit(target);
        PlayerEntity player = (PlayerEntity) this.getOwner();

        target.teleport(player.getX(), player.getY(), player.getZ());

        // logic to teleport entity
    }

    @Override
    protected void onBlockHit(BlockHitResult bhr) {
        super.onBlockHit(bhr);
        BlockPos b = bhr.getBlockPos();

        getOwner().teleport(b.getX(), b.getY()+1, b.getZ());

        if (this.world.isClient) {
            this.world.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        this.world.playSound((PlayerEntity) this.getOwner(), b, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.AMBIENT,1f, 1f);


        this.discard();
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
