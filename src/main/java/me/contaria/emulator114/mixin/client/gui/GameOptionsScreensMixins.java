package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.screen.options.*;
import net.minecraft.client.gui.screen.resourcepack.ResourcePackOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin({
        AccessibilityScreen.class,
        ChatOptionsScreen.class,
        ControlsOptionsScreen.class,
        LanguageOptionsScreen.class,
        MouseOptionsScreen.class,
        ResourcePackOptionsScreen.class,
        SkinOptionsScreen.class,
        SoundOptionsScreen.class,
        VideoOptionsScreen.class
})
public abstract class GameOptionsScreensMixins extends GameOptionsScreen {

    public GameOptionsScreensMixins(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-125104
    @MCBug("MC-125104")
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;<init>(IIIILjava/lang/String;Lnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V", ordinal = 0), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=gui.done")
    ), index = 5)
    private ButtonWidget.PressAction emulator114$onlyOpenParentScreenOnDoneButtonNotEsc(ButtonWidget.PressAction onPress) {
        return button -> this.minecraft.openScreen(this.parent);
    }
}