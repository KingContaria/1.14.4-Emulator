package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.DirectConnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DirectConnectScreen.class)
public abstract class DirectConnectScreenMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-2140
    @MCBug("MC-2140")
    @Redirect(method = "onAddressFieldChanged", at = @At(value = "INVOKE", target = "Ljava/lang/String;indexOf(I)I"))
    private int emulator114$noBlockingAddressWithSpaces(String string, int ch) {
        return -1;
    }
}