package me.contaria.emulator114.mixin.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.ClickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Redirect(method = "handleComponentClicked", at = @At(value = "FIELD", target = "Lnet/minecraft/text/ClickEvent$Action;COPY_TO_CLIPBOARD:Lnet/minecraft/text/ClickEvent$Action;"))
    private ClickEvent.Action emulator114$noCopyingToClipboardClickEvent() {
        return ClickEvent.Action.RUN_COMMAND;
    }
}