package me.contaria.emulator114.mixin.animation.entity;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        AnimalEntity.class,
        PandaEntity.class,
        ZombieVillagerEntity.class
})
public abstract class InteractingEntitiesMixins {

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;swingHand(Lnet/minecraft/util/Hand;Z)V"))
    private void emulator114$removeHandAnimation(PlayerEntity player, Hand hand, boolean b) {
    }
}