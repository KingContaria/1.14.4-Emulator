package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ConduitBlockEntity.class)
public abstract class ConduitBlockEntityMixin {
/*
    @ModifyArg(method = "spawnNautilusParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;<init>(DDD)V"), index = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @ModifyArg(method = "spawnNautilusParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;<init>(DDD)V"), index = 1)
    private double emulator114$precisionLossY(double y) {
        return (float) y;
    }

    @ModifyArg(method = "spawnNautilusParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;<init>(DDD)V"), index = 2)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }

 */
}