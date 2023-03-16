package me.contaria.emulator114.mixin.entity.thrown;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.thrown.ThrownEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThrownEntity.class)
public abstract class ThrownEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-70111
    @MCBug("MC-70111")
    @Redirect(method = "getOwner", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/LivingEntity;removed:Z"))
    private boolean emulator114$killEnderPearlOnLogOut(LivingEntity entity) {
        return false;
    }
}