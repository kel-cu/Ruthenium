package ru.kelcuprum.ruthenium.screen;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.SliderIntegerBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.ruthenium.Ruthenium;

public class ConfigScreen {
    public static Screen build(Screen parent){
        return new ConfigScreenBuilder(parent, Component.translatable("ruthenium.name"), InterfaceUtils.DesignType.FLAT)
                .addPanelWidget(new ButtonBuilder(Component.translatable("ruthenium.config"), (s)-> Minecraft.getInstance().setScreen(ConfigScreen.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("modmenu.modrinth"), (s)-> Util.getPlatform().openUri("https://modrinth.com/mod/ruthenium")).build())

                .addWidget(new TextBox(Component.translatable("ruthenium.config"), true))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("ruthenium.config.enable.afk_fps"), true).setConfig(Ruthenium.config, "ENABLE.AFK_FPS").build())
                .addWidget(new SliderIntegerBuilder(Component.translatable("ruthenium.config.afk_fps")).setDefaultValue(10).setMin(5).setMax(60).setConfig(Ruthenium.config, "AFK_FPS").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("ruthenium.config.enable.afk_distance"), false).setConfig(Ruthenium.config, "ENABLE.AFK_DISTANCE").build())
                .addWidget(new SliderIntegerBuilder(Component.translatable("ruthenium.config.afk_distance")).setDefaultValue(2).setMin(2).setMax(12).setConfig(Ruthenium.config, "AFK_DISTANCE").build())
                .build();
    }
}
