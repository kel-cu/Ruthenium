package ru.kelcuprum.ruthenium.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.kelcuprum.ruthenium.Ruthenium;

@Mixin(value = Options.class, priority = -1)
public abstract class OptionsMixin {
    @Inject(method = "getEffectiveRenderDistance", at = @At("HEAD"), cancellable = true)
    protected void getEffectiveRenderDistance(CallbackInfoReturnable<Integer> cir) {
        if(!Ruthenium.config.getBoolean("ENABLE.AFK_DISTANCE", true)) return;
        if(Minecraft.getInstance().isWindowActive()) return;
        cir.setReturnValue(Ruthenium.config.getNumber("AFK_DISTANCE", 2).intValue());
        cir.cancel();
    }
}