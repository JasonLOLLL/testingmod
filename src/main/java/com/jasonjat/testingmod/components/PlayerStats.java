package com.jasonjat.testingmod.components;

import com.jasonjat.testingmod.abilities.Ability;
import com.jasonjat.testingmod.abilities.AbilityRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.visitor.NbtTextFormatter;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Identifier;

import java.util.Map;

public class PlayerStats implements AutoSyncedComponent, ServerTickingComponent {
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

    @Override
    public void serverTick() {
        double level = getLevel();
        for (Map.Entry<Identifier, Ability> entry : AbilityRegistry.getAbilities().entrySet()) {
            if (level >= entry.getValue().getLevelUnlocked()) {
                MyComponents.UNLOCKED_ABILITIES.get(player).unlockAbility(entry.getKey());
            } else {
                MyComponents.UNLOCKED_ABILITIES.get(player).revokeAbility(entry.getKey());
            }
        }
    }
}
