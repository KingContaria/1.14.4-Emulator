package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.FallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings("InvalidInjectorMethodSignature")
@Mixin(FallingBlock.class)
public abstract class FallingBlockMixin {

    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 2)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}