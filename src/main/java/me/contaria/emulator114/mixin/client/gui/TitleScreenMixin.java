package me.contaria.emulator114.mixin.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.options.GameOptions;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;isModded()Z"))
    private boolean emulator114$doNotShowModded(MinecraftClient client) {
        return false;
    }

    @Redirect(method = "method_19860", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;skipMultiplayerWarning:Z", opcode = Opcodes.GETFIELD, remap = true), remap = false)
    private boolean emulator114$doNotShowDisclaimerScreen(GameOptions options) {
        return true;
    }
}