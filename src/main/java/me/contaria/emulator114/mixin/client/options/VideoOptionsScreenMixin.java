package me.contaria.emulator114.mixin.client.options;

import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin {

    @Mutable
    @Shadow @Final private static Option[] OPTIONS;

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonListWidget;addSingleOptionEntry(Lnet/minecraft/client/options/Option;)I", ordinal = 1))
    private int emulator114$noWideBiomeBlendButton(ButtonListWidget button, Option option) {
        return 0;
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void emulator114$addNormalBiomeBlendButton(CallbackInfo ci) {
        List<Option> options = Arrays.stream(OPTIONS).collect(Collectors.toList());
        options.add(Option.BIOME_BLEND_RADIUS);
        OPTIONS = options.toArray(new Option[0]);
    }
}