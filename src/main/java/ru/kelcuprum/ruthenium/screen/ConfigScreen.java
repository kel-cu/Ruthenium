package ru.kelcuprum.ruthenium.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.ruthenium.Client;

import java.util.Objects;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private static final Component TITLE = Component.translatable("ruthenium.name");
    private static final Component afkDistanceEnableText = Component.translatable("ruthenium.config.afk_distance_enable");
    private static final Component afkDistanceText = Component.translatable("ruthenium.config.afk_distance");
    private static final Component afkFPSEnableText = Component.translatable("ruthenium.config.afk_fps_enable");
    private static final Component afkFPSText = Component.translatable("ruthenium.config.afk_fps");

    public ConfigScreen(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    public void tick() {
        super.tick();
    }

    public void init() {
        this.initButtonsCategory();
    }

    private void initButtonsCategory() {
        Objects.requireNonNull(this.font);
        addRenderableWidget(new TextBox(0, 15, this.width, 9, this.title, true));
        int x = width/2;
        int size = 180;
        addRenderableWidget(new ButtonBoolean(x-90, 40, size, 20, Client.config, "AFK_FPS_ENABLE", true, afkFPSEnableText));
        addRenderableWidget(new SliderInteger(x-90, 65, size, 20, Client.config, "AFK_FPS", 10,5, 60, afkFPSText));

        addRenderableWidget(new ButtonBoolean(x-90, 90, size, 20, Client.config, "AFK_DISTANCE_ENABLE", true, afkDistanceEnableText));
        addRenderableWidget(new SliderInteger(x-90, 115, size, 20, Client.config, "AFK_DISTANCE", 2,2, 12, afkDistanceText));

        addRenderableWidget(new Button(x-90, height-30, size, 20, 0xFFff006e, CommonComponents.GUI_BACK, (s) -> {
            assert this.minecraft != null;
            this.minecraft.setScreen(this.parent);
        }));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int x = width/2;
        guiGraphics.fill(x-100, 0, x+100, height, 0x7F000000);
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
    }
}

