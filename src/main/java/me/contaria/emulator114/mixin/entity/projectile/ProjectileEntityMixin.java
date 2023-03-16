package me.contaria.emulator114.mixin.entity.projectile;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-94421
    @MCBug("MC-94421")
    @ModifyVariable(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean emulator114$playHitSoundOnEndermanHit(boolean isEnderman) {
        return false;
    }

    // see above
    @MCBug("MC-94421")
    @WrapWithCondition(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ProjectileEntity;remove()V", ordinal = 1))
    private boolean emulator114$playHitSoundOnEndermanHit(ProjectileEntity projectile, EntityHitResult entityHitResult) {
        return entityHitResult.getEntity().getType() != EntityType.ENDERMAN;
    }
}