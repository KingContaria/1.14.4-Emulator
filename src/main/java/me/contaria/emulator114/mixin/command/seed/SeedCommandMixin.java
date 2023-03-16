package me.contaria.emulator114.mixin.command.seed;

import net.minecraft.server.command.SeedCommand;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SeedCommand.class)
public abstract class SeedCommandMixin {

    @Redirect(method = "method_13619", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Style;setHoverEvent(Lnet/minecraft/text/HoverEvent;)Lnet/minecraft/text/Style;", remap = true), remap = false)
    private static Style emulator114$removeHoverEvent(Style style, HoverEvent event) {
        return style;
    }

    @Redirect(method = "method_13619", at = @At(value = "FIELD", target = "Lnet/minecraft/text/ClickEvent$Action;COPY_TO_CLIPBOARD:Lnet/minecraft/text/ClickEvent$Action;", remap = true), remap = false)
    private static ClickEvent.Action emulator114$replaceCopyingToClipboard() {
        return ClickEvent.Action.SUGGEST_COMMAND;
    }
}