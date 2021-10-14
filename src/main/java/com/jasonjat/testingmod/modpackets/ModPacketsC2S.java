package com.jasonjat.testingmod.modpackets;

import com.jasonjat.testingmod.abilities.Ability;
import com.jasonjat.testingmod.abilities.ExplodeAbility;
import com.jasonjat.testingmod.components.IntComponent;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import static com.jasonjat.testingmod.components.MyComponents.MAGIK;

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
            World world = serverPlayerEntity.getEntityWorld();
            switch (type) {
                case 0:
                    serverPlayerEntity.teleport(pos.getX(), pos.getY()+10, pos.getZ());
                    serverPlayerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 1:
                    serverPlayerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 3));
                    serverPlayerEntity.getServerWorld().spawnParticles(ParticleTypes.DOLPHIN, pos.getX(), pos.getY(), pos.getZ(), 10,1,1,1,1);
                    serverPlayerEntity.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 2:
                    world.breakBlock(serverPlayerEntity.getBlockPos().add(0,-1,0), true, serverPlayerEntity);
                    serverPlayerEntity.getServerWorld().spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 15,1,1,1,1);
                    break;
                case 3:
                    serverPlayerEntity.playSound(SoundEvents.ENTITY_FOX_HURT, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 4:
                    Ability myNewAbility = new ExplodeAbility(serverPlayerEntity);
                    myNewAbility.useAbility();
                case 5:
                    int magik = MAGIK.maybeGet(serverPlayerEntity).map(IntComponent::getValue).orElse(0);
                    System.out.println(magik);
                    break;
                case 6:
                    MAGIK.get(serverPlayerEntity).setValue(10);
                    break;
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
