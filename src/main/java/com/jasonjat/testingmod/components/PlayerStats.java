package com.jasonjat.testingmod.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerStats implements AutoSyncedComponent {
    private final PlayerEntity player;
    //stats
    private double level = 0;

    public PlayerStats(PlayerEntity player) {
        this.player = player;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public void incrementLevel(double level) {
        this.level+=level;
        System.out.println(player.getName().asString() + "'s level has increased by " + level);
        System.out.println("Level is now: " + this.level);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        level = tag.getDouble("level");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putDouble("level", level);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return this.player == player;
    }
}
