package com.jasonjat.testingmod.modpackets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModPacketsC2S {

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ModPackets.GUI_PACKET, ModPacketsC2S::guiThing);
        ServerPlayNetworking.registerGlobalReceiver(ModPackets.KEYBIND_PACKET, ModPacketsC2S::keybind);
    }

    private static void keybind(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        String message = packetByteBuf.readString();
        if (message.equals("C")) {
            serverPlayerEntity.setOnFireFor(1);
        }
    }

    private static void spawnArrow(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        Entity entity = null;


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
