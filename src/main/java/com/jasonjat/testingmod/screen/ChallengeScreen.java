package com.jasonjat.testingmod.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public abstract class ChallengeScreen extends Screen {

    protected ChallengeScreen(Text title) {
        super(title);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
