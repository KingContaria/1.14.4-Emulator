package me.contaria.emulator114.mixin.world.gen.feature;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EndSpikeFeature.class)
public abstract class EndSpikeFeatureMixin {

    @ModifyConstant(method = "generateSpike", constant = @Constant(intValue = 1, ordinal = 0), slice = @Slice(
            from = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;", remap = false)
    ))
    private int emulator114$buggedEndTowers(int constant) {
        return 0;
    }

    @ModifyExpressionValue(method = "generateSpike", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getSquaredDistance(DDDZ)D"))
    private double emulator114$buggedEndTowers(double distance) {
        return distance + 0.001;
    }
}