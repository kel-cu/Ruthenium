package ru.kelcuprum.ruthenium.config;

import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONObject;

public class UserConfig {
    public static int AFK_DISTANCE = 2;
    public static boolean AFK_DISTANCE_ENABLE = true;
    public static int AFK_FPS = 10;
    public static boolean AFK_FPS_ENABLE = true;
    public static void save(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("Ruthenium/config.json");
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("AFK_DISTANCE", AFK_DISTANCE)
                .put("AFK_DISTANCE_ENABLE", AFK_DISTANCE_ENABLE)
                .put("AFK_FPS", AFK_FPS)
                .put("AFK_FPS_ENABLE", AFK_FPS_ENABLE);
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void load(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("Ruthenium/config.json");
        try{
            JSONObject jsonConfig = new JSONObject(Files.readString(configFile));
            if(!jsonConfig.isNull("AFK_DISTANCE")) AFK_DISTANCE = jsonConfig.getInt("AFK_DISTANCE");
            else AFK_DISTANCE = 2;
            if(!jsonConfig.isNull("AFK_DISTANCE_ENABLE")) AFK_DISTANCE_ENABLE = jsonConfig.getBoolean("AFK_DISTANCE_ENABLE");
            else AFK_DISTANCE_ENABLE = true;
            if(!jsonConfig.isNull("AFK_FPS")) AFK_FPS = jsonConfig.getInt("AFK_FPS");
            else AFK_FPS = 2;
            if(!jsonConfig.isNull("AFK_FPS_ENABLE")) AFK_FPS_ENABLE = jsonConfig.getBoolean("AFK_FPS_ENABLE");
            else AFK_FPS_ENABLE = true;
        } catch (Exception e){
            e.printStackTrace();
            save();
        }
    }
}
