package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
    private double emulator114$precisionLossY(double y) {
        return (float) y;
    }

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "randomDisplayTick", at = @At(value = "STORE", ordinal = 0), ordinal = 2)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}