package me.contaria.emulator114.mixin.client.gui;

import com.mojang.brigadier.ImmutableStringReader;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.CommandSuggestor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandSuggestor.class)
public abstract class CommandSuggestorMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-150079
    @MCBug("MC-150079")
    @Redirect(method = "show", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/ImmutableStringReader;canRead()Z", remap = false))
    private boolean emulator114$doNotShowCommandExceptions(ImmutableStringReader reader) {
        return false;
    }
}
