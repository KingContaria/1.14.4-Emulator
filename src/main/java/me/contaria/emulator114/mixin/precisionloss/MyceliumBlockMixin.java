package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.block.MyceliumBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MyceliumBlock.class)
public abstract class MyceliumBlockMixin {

    @ModifyArg(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), index = 1)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @ModifyArg(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), index = 2)
    private double emulator114$precisionLossY(double y) {
        return (float) y;
    }

    @ModifyArg(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), index = 3)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}