package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.EndPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin {

    // Reverts: "The floating-point precision used for the location of where certain particles and entities appear has increased to 64-bit [...]: End portal particles"
    @ModifyVariable(method = "randomDisplayTick", at = @At("STORE"))
    private double emulator114$precisionLoss(double value) {
        return (float) value;
    }
}