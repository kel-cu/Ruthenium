package ru.kelcuprum.ruthenium;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import ru.kelcuprum.ruthenium.screen.ConfigScreen;

@Mod("ruthenium")
public class RutheniumForge {
    public RutheniumForge(){
        Ruthenium.onInitializeClient();
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> ConfigScreen.build(screen)));
    }
}
