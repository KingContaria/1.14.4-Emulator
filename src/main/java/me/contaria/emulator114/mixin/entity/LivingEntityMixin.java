package me.contaria.emulator114.mixin.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract void dropXp();

    // Reverts: "Experience orbs now appear at the same spatial and temporal location as loot when an entity is killed."
    @Redirect(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropXp()V"))
    private void emulator114$noDroppingXPearly(LivingEntity entity) {
    }

    // see above
    @Inject(method = "updatePostDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;remove()V"))
    private void emulator114$dropXPLate(CallbackInfo ci) {
        this.dropXp();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-159574
    @MCBug("MC-159574")
    @Redirect(method = "consumeItem", at = @At(value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"))
    private boolean emulator114$consumeWrongItem(Object item1, Object item2) {
        return true;
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateTrackedPosition(DDD)V"))
    private void emulator114$noUpdatingTrackedPosition(LivingEntity entity, double x, double y, double z) {
    }
}