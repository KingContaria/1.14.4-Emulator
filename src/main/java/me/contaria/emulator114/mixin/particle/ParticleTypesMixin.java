package me.contaria.emulator114.mixin.particle;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ParticleTypes.class)
public abstract class ParticleTypesMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/particle/ParticleTypes;register(Ljava/lang/String;Z)Lnet/minecraft/particle/DefaultParticleType;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=dripping_honey")
    ))
    private static DefaultParticleType emulator114$removeParticles(String name, boolean alwaysShow) {
        return new DefaultParticleType(false);
    }
}