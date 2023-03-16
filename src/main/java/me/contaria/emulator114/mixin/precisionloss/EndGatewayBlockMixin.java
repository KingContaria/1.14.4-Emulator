package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.EndGatewayBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EndGatewayBlock.class)
public abstract class EndGatewayBlockMixin {

    @ModifyVariable(method = "randomDisplayTick", at = @At("STORE"), slice = @Slice(
            to = @At(value = "INVOKE", target = "Ljava/util/Random;nextFloat()F", ordinal = 3)
    ))
    private double emulator114$precisionLoss(double value) {
        return (float) value;
    }
}
