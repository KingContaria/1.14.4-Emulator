package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ConcretePowderBlock.class)
public abstract class ConcretePowderBlockMixin {

    @Shadow
    private static boolean hardensIn(BlockState state) {
        return false;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-150575
    @MCBug("MC-150575")
    @Redirect(method = "onLanding", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/ConcretePowderBlock;method_24279(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"))
    private boolean emulator114$noHardenedConcreteWhenLanding(BlockView view, BlockPos pos, BlockState state) {
        return hardensIn(state);
    }
}