package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.FallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FallingBlock.class)
public abstract class FallingBlockMixin {

    // Reverts: "The floating-point precision used for the location of where certain particles and entities appear has increased to 64-bit [...]: Particles for suspended blocks affected by gravity, such as sand and gravel."
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    // see above
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 2)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}