package ru.kelcuprum.ruthenium;

import com.mojang.authlib.yggdrasil.response.User;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.ruthenium.config.UserConfig;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("Ruthenium");
    public static Boolean yetAnotherConfigLibV3 = FabricLoader.getInstance().getModContainer("yet_another_config_lib_v3").isPresent();
    public static int currentFPS = 10;
    public static int currentDistance = 2;
    public static boolean closing = false;
    public static boolean background = false;
    public static boolean lastStatus = false;
    @Override
    public void onInitializeClient() {
        log("I'm started work UwU");
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            closing = false;
            GameOptions gameSettings = MinecraftClient.getInstance().options;
            log("Client started!");
            init();
            currentFPS = gameSettings.getMaxFps().getValue();
            currentDistance = gameSettings.getViewDistance().getValue();
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            closing = true;
            log("Client stopped!");
        });
    }
    public static void init(){
        UserConfig.load();
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (closing) return;

            if (MinecraftClient.getInstance().getWindow().shouldClose() || MinecraftClient.getInstance().world == null)
                return;
            GameOptions gameSettings = MinecraftClient.getInstance().options;
            if (!MinecraftClient.getInstance().isWindowFocused()) {
                if(!lastStatus){
//                    log("update back");
                    lastStatus = true;
                    if (UserConfig.AFK_FPS_ENABLE) {
                        currentFPS = gameSettings.getMaxFps().getValue();
                        setFpsLimit(UserConfig.AFK_FPS);
                    }
                    if (UserConfig.AFK_DISTANCE_ENABLE) {
                        currentDistance = gameSettings.getViewDistance().getValue();
                        gameSettings.getViewDistance().setValue(UserConfig.AFK_DISTANCE);
                    }
                }
            } else {
                if(lastStatus){
//                    log("update active");
                    lastStatus = false;
                    if (UserConfig.AFK_FPS_ENABLE) {
                        setFpsLimit(currentFPS);
                    }
                    if (UserConfig.AFK_DISTANCE_ENABLE) {
                        gameSettings.getViewDistance().setValue(currentDistance);
                    }
                }
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null) {
                if (!MinecraftClient.getInstance().isWindowFocused()) return;
                if(lastStatus){
                    GameOptions gameSettings = MinecraftClient.getInstance().options;
                    if (UserConfig.AFK_FPS_ENABLE) {
                        setFpsLimit(currentFPS);
                    }
                    if (UserConfig.AFK_DISTANCE_ENABLE) {
                        gameSettings.getViewDistance().setValue(currentDistance);
                    }
                    gameSettings.write();
                }
            }
        });
    }
    public static void setFpsLimit(int fps) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        minecraft.getWindow().setFramerateLimit(fps);
    }
    public static void log(String message){ log(message, Level.INFO); }
    public static void log(String message, Level level){
        LOG.log(level, "[Ruthenium] "+message);
    }
}