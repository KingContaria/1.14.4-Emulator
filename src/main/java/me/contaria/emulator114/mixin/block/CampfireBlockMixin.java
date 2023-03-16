package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {

    // Reverts: "Fire charges launched from a dispenser now light campfires."
    // Bugreport: https://bugs.mojang.com/browse/MC-159947
    @MCBug("MC-159947")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "onProjectileHit", constant = @Constant(classValue = AbstractFireballEntity.class))
    private boolean emulator114$noFireballsLightingCampfires(Object o, Class<?> c) {
        return false;
    }
}