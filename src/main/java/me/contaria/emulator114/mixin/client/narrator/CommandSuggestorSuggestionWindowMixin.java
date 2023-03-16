package me.contaria.emulator114.mixin.client.narrator;

import net.minecraft.client.gui.screen.CommandSuggestor;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandSuggestor.SuggestionWindow.class)
public abstract class CommandSuggestorSuggestionWindowMixin {

    @Redirect(method = "select", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/NarratorManager;narrate(Ljava/lang/String;)V"))
    private void emulator114$noNarration(NarratorManager manager, String text) {
    }
}