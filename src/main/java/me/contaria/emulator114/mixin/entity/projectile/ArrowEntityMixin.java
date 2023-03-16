package me.contaria.emulator114.mixin.entity.projectile;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-107941
    @MCBug("MC-107941")
    @Redirect(method = "initColor", at = @At(value = "INVOKE", target = "Ljava/util/Set;isEmpty()Z"))
    private boolean emulator114$overwriteColorTag(Set<?> instance) {
        return false;
    }
}