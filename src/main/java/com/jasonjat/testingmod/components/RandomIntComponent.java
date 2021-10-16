package com.jasonjat.testingmod.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class RandomIntComponent implements AutoSyncedComponent, ServerTickingComponent {

    private final PlayerEntity provider;
    private int value = (int) (Math.random() * 20);
    private final List<String> stringList = new ArrayList<>();

    public RandomIntComponent(PlayerEntity provider) {
        this.provider = provider;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        MyComponents.MAGIK.sync(this.provider);
    }

    public void addString() {
        stringList.add("" + Math.random()*200);
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.value = tag.getInt("value");

        stringList.clear();
        NbtList nbtList = tag.getList("listz", NbtType.STRING);
        nbtList.forEach(x -> stringList.add(x.asString()));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("value", this.value);

        NbtList nbtList = new NbtList();
        stringList.forEach(x -> nbtList.add(NbtString.of(x)));
        tag.put("listz", nbtList);
    }

    @Override
    public void serverTick() {
//        if (provider.age % 100 == 0) { //every five seconds or 100 ticks
//            System.out.println("Five seconds have passed.");
//            stringList.forEach(System.out::println);
//        }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider;
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        AutoSyncedComponent.super.writeSyncPacket(buf, recipient);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        AutoSyncedComponent.super.applySyncPacket(buf);
    }
}
