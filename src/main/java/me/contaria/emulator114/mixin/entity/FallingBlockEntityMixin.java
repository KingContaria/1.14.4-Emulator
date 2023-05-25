package me.contaria.emulator114.mixin.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {

    // Bugreports: https://bugs.mojang.com/browse/MC-144263, https://bugs.mojang.com/browse/MC-165023, https://bugs.mojang.com/browse/MC-165034
    @MCBug({"MC-144263", "MC-165023", "MC-165034"})
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FallingBlock;canFallThrough(Lnet/minecraft/block/BlockState;)Z"))
    private boolean emulator114$sandLandsOnBoats(BlockState state) {
        return false;
    }
}