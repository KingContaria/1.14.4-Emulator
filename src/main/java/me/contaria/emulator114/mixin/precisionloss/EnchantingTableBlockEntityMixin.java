package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.entity.EnchantingTableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings("InvalidInjectorMethodSignature")
@Mixin(EnchantingTableBlockEntity.class)
public abstract class EnchantingTableBlockEntityMixin {

    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @ModifyVariable(method = "tick", at = @At(value = "STORE", ordinal = 0), ordinal = 1)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}