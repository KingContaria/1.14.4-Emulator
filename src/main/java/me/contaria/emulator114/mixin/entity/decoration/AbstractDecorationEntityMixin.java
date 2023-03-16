package me.contaria.emulator114.mixin.entity.decoration;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractDecorationEntity.class)
public abstract class AbstractDecorationEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-862
    @MCBug("MC-862")
    @Redirect(method = "handleAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;canPlayerModifyAt(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean emulator114$breakInSpawnProtection(World world, PlayerEntity player, BlockPos pos) {
        return true;
    }
}