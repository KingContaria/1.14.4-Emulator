package me.contaria.emulator114.mixin.animation.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {

    protected SheepEntityMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    // Reverts: "Added the hand use animation [...]: Shearing animals"
    // Bugreport: https://bugs.mojang.com/browse/MC-160896
    @MCBug("MC-160896")
    @ModifyReturnValue(method = "interactMob", at = @At(value = "RETURN", ordinal = 0))
    private boolean emulator114$removeHandAnimation(boolean bl, PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }
}