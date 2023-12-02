package ru.kelcuprum.ruthenium.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatColoredButton;
import ru.kelcuprum.alinlib.gui.components.sliders.flat.FlatSliderInteger;
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
    private static final Component exitText = Component.translatable("ruthenium.config.exit");

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
        int x = this.width/2-155;
        addRenderableWidget(new FlatButtonBoolean(x, 40, 150, 20, Client.config, "AFK_FPS_ENABLE", true, afkFPSEnableText));
        addRenderableWidget(new FlatSliderInteger(x+160, 40, 150, 20, Client.config, "AFK_FPS", 10,5, 60, afkFPSText));

        addRenderableWidget(new FlatButtonBoolean(x, 65, 150, 20, Client.config, "AFK_DISTANCE_ENABLE", true, afkDistanceEnableText));
        addRenderableWidget(new FlatSliderInteger(x+160, 65, 150, 20, Client.config, "AFK_DISTANCE", 2,2, 12, afkDistanceText));

        addRenderableWidget(new FlatColoredButton(x+80, 115, 150, 20, 0xFFff006e,  exitText, (s) -> {
            this.minecraft.setScreen(this.parent);
        }));
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
}

