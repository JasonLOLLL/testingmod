package com.jasonjat.testingmod.modpackets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.mixin.client.particle.ParticleManagerAccessor;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ModPacketsC2S {

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ModPackets.GUI_PACKET, ModPacketsC2S::guiThing);
        ServerPlayNetworking.registerGlobalReceiver(ModPackets.KEYBIND_PACKET, ModPacketsC2S::keybind);
    }

    private static void keybind(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        String action = packetByteBuf.readString();
        int type = packetByteBuf.readInt();
        if (action.equals("Use")) {
            Vec3d pos = serverPlayerEntity.getPos();
            switch (type) {
                case 0:
                    serverPlayerEntity.teleport(pos.getX(), pos.getY()+10, pos.getZ());
                    serverPlayerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 1:
                    serverPlayerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 3));
                    serverPlayerEntity.getServerWorld().spawnParticles(ParticleTypes.DOLPHIN, pos.getX(), pos.getY(), pos.getZ(), 10,1,1,1,1);
                    serverPlayerEntity.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 1f, 1f);
            }

        }
    }

    private static void guiThing(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        PlayerEntity player = (PlayerEntity) serverPlayerEntity;
        String red = packetByteBuf.readString();
        if (red.equals("Drop")) {
            player.giveItemStack(new ItemStack(Items.DIAMOND, 64));
        } else {
            player.kill();
        }
    }
}
