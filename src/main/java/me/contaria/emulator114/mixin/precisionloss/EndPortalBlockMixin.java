package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.EndPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin {

    @ModifyVariable(method = "randomDisplayTick", at = @At("STORE"))
    private double emulator114$precisionLoss(double value) {
        return (float) value;
    }
}