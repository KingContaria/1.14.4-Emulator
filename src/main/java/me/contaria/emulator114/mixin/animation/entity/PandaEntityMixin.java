package me.contaria.emulator114.mixin.animation.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PandaEntity.class)
public abstract class PandaEntityMixin {

    // Reverts: "Added the hand use animation [...]: Feeding pandas"
    // Bugreport: https://bugs.mojang.com/browse/MC-161262
    @MCBug("MC-161262")
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;swingHand(Lnet/minecraft/util/Hand;Z)V"))
    private void emulator114$removeHandAnimation(PlayerEntity player, Hand hand, boolean b) {
    }
}