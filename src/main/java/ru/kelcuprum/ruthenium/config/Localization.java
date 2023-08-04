package ru.kelcuprum.ruthenium.config;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class Localization {
    /**
     * Хуета которая может быть спасёт от Mojang которые сука постоянно меняют либо название класса либо еще что-то
     * @return MutableText
     * @param key
     */
    public static MutableText getText(String key){
        return Text.translatable(key);
    }
    public static MutableText toText(String text){
        return Text.literal(text);
    }
}
