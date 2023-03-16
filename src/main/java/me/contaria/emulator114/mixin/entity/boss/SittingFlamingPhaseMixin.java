package me.contaria.emulator114.mixin.entity.boss;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.boss.dragon.phase.SittingFlamingPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SittingFlamingPhase.class)
public abstract class SittingFlamingPhaseMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-158677
    @MCBug("MC-158677")
    @ModifyConstant(method = "serverTick", constant = @Constant(doubleValue = 0.0, ordinal = 1))
    private double emulator114$freezeWhenNoEndStone(double constant) {
        return Double.MIN_VALUE;
    }
}