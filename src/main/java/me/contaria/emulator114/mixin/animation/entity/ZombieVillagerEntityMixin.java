package me.contaria.emulator114.mixin.animation.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-161261
    @MCBug("MC-161261")
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;swingHand(Lnet/minecraft/util/Hand;Z)V"))
    private void emulator114$removeHandAnimation(PlayerEntity player, Hand hand, boolean b) {
    }
}