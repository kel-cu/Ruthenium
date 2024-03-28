package ru.kelcuprum.ruthenium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.alinlib.config.Config;

@Environment(EnvType.CLIENT)
public class Ruthenium implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("Ruthenium");
    public static Config config = new Config("config/ruthenium.json");
    @Override
    public void onInitializeClient() {
        log("I'm started work UwU");
    }
    public static void log(String message){ log(message, Level.INFO); }
    public static void log(String message, Level level){
        LOG.log(level, "[Ruthenium] "+message);
    }
}