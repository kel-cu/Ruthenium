package ru.kelcuprum.ruthenium.screen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.FatalErrorScreen;
import net.minecraft.client.gui.screen.Screen;
import ru.kelcuprum.ruthenium.Client;
import ru.kelcuprum.ruthenium.config.Localization;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (Client.yetAnotherConfigLibV3) {
            return ConfigScreen::buildScreen;
        } else {
            return this::buildNotSupportScreen;
        }
    }

    public Screen buildNotSupportScreen(Screen currentScreen){
        return new FatalErrorScreen(Localization.getText("ruthenium.name"), Localization.getText("ruthenium.not.yaclv3"));
    }
}
