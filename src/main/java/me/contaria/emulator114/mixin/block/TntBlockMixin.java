package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-124280
    @MCBug("MC-124280")
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreative()Z"))
    private boolean emulator114$consumeFireChargeInCreative(PlayerEntity player) {
        return false;
    }
}