package me.contaria.emulator114.mixin.command.clone;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.server.command.CloneCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CloneCommand.class)
public abstract class CloneCommandMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-159785
    @MCBug("MC-159785")
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private static boolean emulator114$keepClonedBlockAsSameInMemory(ServerWorld world, BlockPos blockPos, BlockState blockState, int i) {
        return false;
    }
}