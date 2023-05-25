package me.contaria.emulator114.mixin.precisionloss;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MyceliumBlock.class)
public abstract class MyceliumBlockMixin {

    // Reverts: "The floating-point precision used for the location of where certain particles and entities appear has increased to 64-bit [...]: Mycelium particles"
    @WrapOperation(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private void emulator114$precisionLoss(World world, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original) {
        original.call(world, parameters, (float) x, (float) y, (float) z, velocityX, velocityY, velocityZ);
    }
}