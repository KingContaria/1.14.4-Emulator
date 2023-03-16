package me.contaria.emulator114.mixin.entity.ai;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.MobEntityWithAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WanderAroundGoal.class)
public abstract class WanderAroundGoalMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-19413
    @MCBug("MC-19413")
    @Redirect(method = "shouldContinue", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntityWithAi;hasPassengers()Z"))
    private boolean emulator114$entitiesWithPassengersKeepTarget(MobEntityWithAi entity) {
        return false;
    }
}