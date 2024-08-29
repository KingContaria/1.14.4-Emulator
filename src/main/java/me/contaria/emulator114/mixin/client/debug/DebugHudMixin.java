package me.contaria.emulator114.mixin.client.debug;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-148704
    @MCBug("MC-148704")
    @Definition(id = "add", method = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    @Expression("?.add('Server Light: (?? sky, ?? block)')")
    @Redirect(method = "getLeftText", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean emulator114$noServerLightWhenCrossingChunkBorders(List<?> list, Object o) {
        return false;
    }
}