package com.jasonjat.testingmod.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class UnlockedAbilities implements AutoSyncedComponent {
    private final PlayerEntity player;
    private final List<Identifier> unlockedAbilities = new ArrayList<>();

    enum AllAbilities {
        TELEPORT, EXPLODE
    }

    public UnlockedAbilities(PlayerEntity player) {
        this.player = player;
    }

    public boolean unlockAbility(String ability) {
        Identifier a = new Identifier(ability);
        if (!unlockedAbilities.contains(a)) {
            return unlockedAbilities.add(a);
        }
        return false;
    }

    public boolean revokeAbility(String ability) {
        Identifier a = new Identifier(ability);
        return unlockedAbilities.remove(a);
    }

    public List<Identifier> getUnlockedAbilities() {
        return unlockedAbilities;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        unlockedAbilities.clear();

        NbtList nbtList = tag.getList("abilities", NbtType.STRING);
        nbtList.forEach(id -> unlockedAbilities.add(new Identifier(id.asString())));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtList nbtList = new NbtList();

        unlockedAbilities.forEach(id -> nbtList.add(NbtString.of(id.toString())));
        tag.put("abilities", nbtList);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return this.player == player;
    }
}
