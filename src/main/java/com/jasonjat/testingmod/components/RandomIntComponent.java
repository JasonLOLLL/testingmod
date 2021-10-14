package com.jasonjat.testingmod.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class RandomIntComponent implements IntComponent, AutoSyncedComponent, ServerTickingComponent {

    private int value = (int) (Math.random() * 20);
    private final Entity provider;

    public RandomIntComponent(Entity provider) {
        this.provider = provider;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
        MyComponents.MAGIK.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.value = tag.getInt("value");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("value", this.value);
    }

    @Override
    public void serverTick() {

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
