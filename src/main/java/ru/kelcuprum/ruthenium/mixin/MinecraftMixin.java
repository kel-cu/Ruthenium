package ru.kelcuprum.ruthenium.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.kelcuprum.ruthenium.Ruthenium;

@Mixin(value = Minecraft.class, priority = -1)
public abstract class MinecraftMixin {
    @Inject(method = "getFramerateLimit", at = @At("HEAD"), cancellable = true)
    protected void renderSelection(CallbackInfoReturnable<Integer> cir) {
        if(!Ruthenium.config.getBoolean("ENABLE.AFK_FPS", true)) return;
        if(Minecraft.getInstance().isWindowActive()) return;
        cir.setReturnValue(Ruthenium.config.getNumber("AFK_FPS", 10).intValue());
        cir.cancel();
    }
}