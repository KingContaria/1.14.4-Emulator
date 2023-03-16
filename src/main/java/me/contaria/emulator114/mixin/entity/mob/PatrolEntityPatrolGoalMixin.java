package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.mob.PatrolEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PatrolEntity.PatrolGoal.class)
public abstract class PatrolEntityPatrolGoalMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-156856
    @MCBug("MC-156856")
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PatrolEntity;isRaidCenterSet()Z"))
    private boolean emulator114$breakRavagerAI(PatrolEntity entity) {
        return false;
    }
}