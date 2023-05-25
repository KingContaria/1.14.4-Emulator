package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.AddServerScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AddServerScreen.class)
public abstract class AddServerScreenMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-122135
    @MCBug("MC-122135")
    @Redirect(method = "onClose()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    private void emulator114$noClosingOnEsc(MinecraftClient client, Screen screen) {
    }
}