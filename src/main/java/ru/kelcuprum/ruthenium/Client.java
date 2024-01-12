package ru.kelcuprum.ruthenium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.alinlib.config.Config;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("Ruthenium");
    public static int currentFPS = 10;
    public static int currentDistance = 2;
    public static boolean closing = false;
    public static Config config = new Config("config/ruthenium.json");
    public static boolean lastStatus = false;
    @Override
    public void onInitializeClient() {
        log("I'm started work UwU");
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            closing = false;
            Options gameSettings = Minecraft.getInstance().options;
            log("Client started!");
            init();
            currentFPS = gameSettings.framerateLimit().get();
            currentDistance = gameSettings.renderDistance().get();
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            closing = true;
            setFpsLimit(currentFPS);
            Minecraft.getInstance().options.renderDistance().set(currentDistance);
            log("Client stopped!");
        });
    }
    public static void init(){
        config.load();
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (closing) return;

            Options gameSettings = Minecraft.getInstance().options;
            if (Minecraft.getInstance().getWindow().shouldClose() || Minecraft.getInstance().level == null) {
                lastStatus = true;
                gameSettings.renderDistance().set(currentDistance);
                setFpsLimit(currentFPS);
                return;
            }
            if (!Minecraft.getInstance().isWindowActive()) {
                if(!lastStatus){
                    lastStatus = true;
                    if (config.getBoolean("AFK_FPS_ENABLE", true)) {
                        currentFPS = gameSettings.framerateLimit().get();
                        setFpsLimit(config.getNumber("AFK_FPS", 10).intValue());
                    }
                    if (config.getBoolean("AFK_DISTANCE_ENABLE", true)) {
                        currentDistance = gameSettings.renderDistance().get();
                        gameSettings.renderDistance().set(config.getNumber("AFK_DISTANCE", 2).intValue());
                    }
                }
            } else {
                if(lastStatus){
                    lastStatus = false;
                    if (config.getBoolean("AFK_FPS_ENABLE", true)) {
                        setFpsLimit(currentFPS);
                    }
                    if (config.getBoolean("AFK_DISTANCE_ENABLE", true)) {
                        gameSettings.renderDistance().set(currentDistance);
                    }
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
                if (!Minecraft.getInstance().isWindowActive()) return;
                if(lastStatus){
                    Options gameSettings = Minecraft.getInstance().options;
                    if (config.getBoolean("AFK_FPS_ENABLE", true)) {
                        setFpsLimit(currentFPS);
                    }
                    if (config.getBoolean("AFK_DISTANCE_ENABLE", true)) {
                        gameSettings.renderDistance().set(currentDistance);
                    }
                    gameSettings.save();
                }
        });
    }
    public static void setFpsLimit(int fps) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getWindow().setFramerateLimit(fps);
    }
    public static void log(String message){ log(message, Level.INFO); }
    public static void log(String message, Level level){
        LOG.log(level, "[Ruthenium] "+message);
    }
}