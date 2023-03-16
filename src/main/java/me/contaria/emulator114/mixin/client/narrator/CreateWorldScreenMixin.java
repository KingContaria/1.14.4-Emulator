package me.contaria.emulator114.mixin.client.narrator;

import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {

    // Reverts: "Descriptions under buttons on the "Create World" screen are now narrated."
    @Redirect(method = {"method_23491", "method_23493", "method_19927", "method_23497", "method_19929"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;queueNarration(I)V", remap = true), remap = false, require = 5)
    private void emulator114$noNarration(ButtonWidget button, int i) {
    }
}