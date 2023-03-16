package me.contaria.emulator114.mixin.effects;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StatusEffectInstance.class)
public abstract class StatusEffectInstanceMixin {

    // Reverts: "Effects are now stored when overwritten by an effect of a higher amplifier."
    // Bugreport: https://bugs.mojang.com/browse/MC-1541
    @MCBug("MC-1541")
    @Redirect(method = "upgrade", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;duration:I", opcode = Opcodes.GETFIELD, ordinal = 0))
    private int emulator114$noHiddenStatusEffects1(StatusEffectInstance instance) {
        return Integer.MAX_VALUE;
    }

    // see above
    @MCBug("MC-1541")
    @SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference", "InvalidInjectorMethodSignature", "InvalidMemberReference"})
    @Redirect(method = "upgrade", at = @At(value = "NEW", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;"))
    private StatusEffectInstance emulator114$noHiddenStatusEffects2(StatusEffectInstance instance) {
        return null;
    }
}