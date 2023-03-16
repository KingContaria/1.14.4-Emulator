package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-27287
    @MCBug("MC-27287")
    @Redirect(method = "setTamed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/WolfEntity;setHealth(F)V"))
    private void emulator114$noHealingOnTamed(WolfEntity instance, float v) {
    }

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "canAttackWithOwner", constant = @Constant(classValue = TameableEntity.class, ordinal = 0))
    private boolean emulator114$canAttackTamedParrots(Object reference, Class<?> constant) {
        return reference instanceof CatEntity;
    }
}