package com.jasonjat.testingmod.modpackets;

import com.jasonjat.testingmod.Testingmod;
import com.jasonjat.testingmod.abilities.Ability;
import com.jasonjat.testingmod.abilities.AbilityRegistry;
import com.jasonjat.testingmod.abilities.ExplodeAbility;
import com.jasonjat.testingmod.abilities.TeleportAbility;
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

import static com.jasonjat.testingmod.Testingmod.LOGGER;

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
            List<Identifier> idListPlayer = MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).getUnlockedAbilities();

            if (type < 3) {
                useAbility(MyComponents.UNLOCKED_ABILITIES.get(serverPlayerEntity).getUnlockedAbilities().get(type), serverPlayerEntity);
            }

            switch (type) {
                case 3:
                    serverPlayerEntity.playSound(SoundEvents.ENTITY_FOX_HURT, SoundCategory.AMBIENT, 1f, 1f);
                    break;
                case 4:
                    MyComponents.PLAYER_STATS.get(serverPlayerEntity).incrementLevel(-1);
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

    private static void useAbility(Identifier identifier, ServerPlayerEntity player) {
        if (MyComponents.UNLOCKED_ABILITIES.get(player).getCooldown(identifier)<=0 && Ability.checkContains.test(MyComponents.UNLOCKED_ABILITIES.get(player).getUnlockedAbilities(), identifier)) {
            AbilityRegistry.get(identifier).use(player, identifier);
        } else LOGGER.warn("Suspicious behavior on " + player.getDisplayName().asString() + " detected sending early packets and bypassing the cooldown.");
    }

    private static void guiThing(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        PlayerEntity player = (PlayerEntity) serverPlayerEntity;
        System.out.println(packetByteBuf.readString());
    }
}
