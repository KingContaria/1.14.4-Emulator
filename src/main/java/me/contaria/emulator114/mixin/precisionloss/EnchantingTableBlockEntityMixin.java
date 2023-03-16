package me.contaria.emulator114.mixin.precisionloss;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantingTableBlockEntity.class)
public abstract class EnchantingTableBlockEntityMixin {

    // Reverts: "The floating book above placed enchanting tables is now correctly rotated towards the direction of where a nearby player is at high coordinates."
    // Bugreport: https://bugs.mojang.com/browse/MC-161888
    @MCBug("MC-161888")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    // see above
    @MCBug("MC-161888")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}