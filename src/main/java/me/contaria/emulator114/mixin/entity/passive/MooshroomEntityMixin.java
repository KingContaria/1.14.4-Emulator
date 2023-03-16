package me.contaria.emulator114.mixin.entity.passive;

import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MooshroomEntity.class)
public abstract class MooshroomEntityMixin {

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CowEntity;setCustomNameVisible(Z)V"))
    private void emulator114$noCopyingIsCustomNameVisibleData(CowEntity cow, boolean customNameVisible) {
    }

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CowEntity;setPersistent()V"))
    private void emulator114$noCopyingIsPersistentData(CowEntity cow) {
    }

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/CowEntity;setInvulnerable(Z)V"))
    private void emulator114$noCopyingIsInvulnerableData(CowEntity cow, boolean invulnerable) {
    }
}
