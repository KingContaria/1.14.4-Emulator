package me.contaria.emulator114.mixin.world;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-24336
    @MCBug("MC-24336")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "getCausingEntity", constant = @Constant(classValue = ExplosiveProjectileEntity.class, ordinal = 0))
    private boolean emulator114$withersDamagingThemselves(Object o, Class<?> c) {
        return false;
    }
}