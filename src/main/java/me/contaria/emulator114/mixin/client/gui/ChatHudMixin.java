package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    // Bugreports: https://bugs.mojang.com/browse/MC-134495, https://bugs.mojang.com/browse/MC-127573
    @MCBug({"MC-134495", "MC-127573"})
    @Redirect(method = "getText", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;hudHidden:Z"))
    private boolean emulator114$noCheckingHudVisibility(GameOptions options) {
        return false;
    }

    // see above
    @Redirect(method = "getText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;method_23677()Z"))
    private boolean emulator114$noCheckingChatVisibility(ChatHud hud) {
        return true;
    }
}