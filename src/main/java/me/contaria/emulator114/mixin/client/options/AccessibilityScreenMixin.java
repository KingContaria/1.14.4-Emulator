package me.contaria.emulator114.mixin.client.options;

import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(AccessibilityScreen.class)
public abstract class AccessibilityScreenMixin {

    @Mutable
    @Shadow @Final private static Option[] OPTIONS;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/options/AccessibilityScreen;OPTIONS:[Lnet/minecraft/client/options/Option;", shift = At.Shift.AFTER))
    private static void emulator114$removeToggleOptions(CallbackInfo ci) {
        List<Option> options = Arrays.stream(OPTIONS).collect(Collectors.toList());
        options.remove(Option.SNEAK_TOGGLED);
        options.remove(Option.SPRINT_TOGGLED);
        OPTIONS = options.toArray(new Option[0]);
    }
}