package ru.kelcuprum.ruthenium.screen;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.impl.controller.IntegerFieldControllerBuilderImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import ru.kelcuprum.ruthenium.Client;
import ru.kelcuprum.ruthenium.config.Localization;
import ru.kelcuprum.ruthenium.config.UserConfig;

public class ConfigScreen {
    public static Screen buildScreen(Screen currentScreen) {
        UserConfig.load();
        YetAnotherConfigLib.Builder screen = YetAnotherConfigLib.createBuilder()
                .title(Localization.getText("ruthenium.name"))
                .category(ConfigCategory.createBuilder()
                        .name(Localization.getText("ruthenium.config.client"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Localization.getText("ruthenium.config.afk_distance_enable"))
                                .binding(true, () -> UserConfig.AFK_DISTANCE_ENABLE, newVal -> UserConfig.AFK_DISTANCE_ENABLE = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build())
                        .option(Option.createBuilder(Integer.class)
                                .name(Localization.getText("ruthenium.config.afk_distance"))
                                .binding(2, () -> UserConfig.AFK_DISTANCE, newVal -> UserConfig.AFK_DISTANCE = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                        .range(2, 32)
                                        .step(1))
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Localization.getText("ruthenium.config.afk_fps_enable"))
                                .binding(true, () -> UserConfig.AFK_FPS_ENABLE, newVal -> UserConfig.AFK_FPS_ENABLE = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build())
                        .option(Option.createBuilder(Integer.class)
                                .name(Localization.getText("ruthenium.config.afk_fps"))
                                .binding(10, () -> UserConfig.AFK_FPS, newVal -> UserConfig.AFK_FPS = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                        .range(10, 260)
                                        .step(10))
                                .build())
                        .build())
                .save(ConfigScreen::save);
        return screen.build().generateScreen(currentScreen);
    }
    private static void save() {
        UserConfig.save();
        Client.log("Save user configs...");
    }
}
