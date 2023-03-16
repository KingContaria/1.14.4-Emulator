package me.contaria.emulator114.mixin.animation.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
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

    @ModifyReturnValue(method = "interactMob", at = @At(value = "RETURN", ordinal = 0))
    private boolean emulator114$removeHandAnimation(boolean bl, PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }
}