package me.contaria.emulator114.mixin.entity.boss;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.Phase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-152159
    @MCBug("MC-152159")
    @Redirect(method = "damagePart", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/phase/Phase;getType()Lnet/minecraft/entity/boss/dragon/phase/PhaseType;"))
    private PhaseType<? extends Phase> emulator114$canHitDeadDragon(Phase phase) {
        return null;
    }
}