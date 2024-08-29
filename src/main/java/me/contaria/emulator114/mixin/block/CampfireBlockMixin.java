package me.contaria.emulator114.mixin.block;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {

    // Reverts: "Fire charges launched from a dispenser now light campfires."
    // Bugreport: https://bugs.mojang.com/browse/MC-159947
    @MCBug("MC-159947")
    @Definition(id = "AbstractFireballEntity", type = AbstractFireballEntity.class)
    @Expression("? instanceof AbstractFireballEntity")
    @Redirect(method = "onProjectileHit", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean emulator114$noFireballsLightingCampfires(Object entity, Class<?> abstractFireballEntity) {
        return false;
    }
}