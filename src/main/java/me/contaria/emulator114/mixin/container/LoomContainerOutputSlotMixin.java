package me.contaria.emulator114.mixin.container;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.container.LoomContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/container/LoomContainer$6")
public abstract class LoomContainerOutputSlotMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-152173
    @MCBug("MC-152173")
    @Redirect(method = "method_17433", at = @At(value = "INVOKE", target = "Lnet/minecraft/container/LoomContainer;method_21829(Lnet/minecraft/container/LoomContainer;)J", ordinal = 0))
    private long emulator114$stackedLoomSounds(LoomContainer container) {
        return -1;
    }
}