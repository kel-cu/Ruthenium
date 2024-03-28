package ru.kelcuprum.ruthenium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RutheniumFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Ruthenium.onInitializeClient();
    }
}