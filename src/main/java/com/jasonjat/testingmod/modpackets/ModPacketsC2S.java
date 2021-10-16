package com.jasonjat.testingmod.modpackets;

import com.jasonjat.testingmod.components.MyComponents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BiPredicate;

public class ModPacketsC2S {

    private static final BiPredicate<List<Identifier>, Identifier> checkContains = List::contains;

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
            List<Identifier> idListPlayer = MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).getUnlockedAbilities();

            switch (type) {
                case 0:
                    if (checkContains.test(idListPlayer, new Identifier("teleport"))) {
                        serverPlayerEntity.teleport(pos.getX(), pos.getY()+10, pos.getZ());
                        serverPlayerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1f, 1f);
                    } else {
                        System.out.println("player does not have teleport ability unlocked");
                    }
                    break;
                case 1:
                    if (checkContains.test(idListPlayer, new Identifier("explode"))) {
                        serverPlayerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 3));
                        serverPlayerEntity.getServerWorld().spawnParticles(ParticleTypes.DOLPHIN, pos.getX(), pos.getY(), pos.getZ(), 10, 1, 1, 1, 1);
                        serverPlayerEntity.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 1f, 1f);
                    } else {
                        System.out.println("player does not have explode ability");
                    }
                    break;
                case 2:
                    world.breakBlock(serverPlayerEntity.getBlockPos().add(0,-1,0), true, serverPlayerEntity);
                    serverPlayerEntity.getServerWorld().spawnParticles(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), 15,1,1,1,1);
                    break;
                case 3:
                    serverPlayerEntity.playSound(SoundEvents.ENTITY_FOX_HURT, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 4:
                    // update unlocked abilities
                    double level = MyComponents.PLAYER_STATS.get(serverPlayerEntity).getLevel();
                    if (level > 5) {
                        MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).unlockAbility("teleport");
                    } else MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).revokeAbility("teleport");
                    if (level > 10) {
                        MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).unlockAbility("explode");
                    } else MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).revokeAbility("explode");
                    break;
                case 5:
                    // change level
                    MyComponents.PLAYER_STATS.get(serverPlayerEntity).incrementLevel(1);
                    break;
                case 6:
                    MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).getUnlockedAbilities().forEach(System.out::println);
                    break;
            }
        }
    }

    private static void guiThing(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        PlayerEntity player = (PlayerEntity) serverPlayerEntity;
        System.out.println(packetByteBuf.readString());
    }
}
