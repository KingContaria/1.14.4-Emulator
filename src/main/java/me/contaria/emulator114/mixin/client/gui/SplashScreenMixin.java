package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.SplashScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(SplashScreen.class)
public abstract class SplashScreenMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-146213
    @MCBug("MC-146213")
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/SplashScreen;progress:F", opcode = Opcodes.GETFIELD),
            to = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/SplashScreen;progress:F", opcode = Opcodes.PUTFIELD)
    ))
    private float emulator114$tooFarProgressBar(float value, float min, float max) {
        return value;
    }
}