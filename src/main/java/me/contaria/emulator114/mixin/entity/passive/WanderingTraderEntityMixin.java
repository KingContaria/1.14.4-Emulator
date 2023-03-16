package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.passive.WanderingTraderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WanderingTraderEntity.class)
public abstract class WanderingTraderEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-146305
    @MCBug("MC-146305")
    @ModifyArg(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoToWalkTargetGoal;<init>(Lnet/minecraft/entity/mob/MobEntityWithAi;D)V"), index = 1)
    private double emulator114$wanderingTradersRunReallyFast(double speed) {
        return 1.0;
    }
}
