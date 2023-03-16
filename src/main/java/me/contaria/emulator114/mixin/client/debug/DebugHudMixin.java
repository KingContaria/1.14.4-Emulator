package me.contaria.emulator114.mixin.client.debug;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-148704
    @MCBug("MC-148704")
    @Redirect(method = "getLeftText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, remap = false), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=Server Light: (?? sky, ?? block)")
    ))
    private boolean emulator114$noServerLightWhenCrossingChunkBorders(List<?> list, Object o) {
        return false;
    }
}