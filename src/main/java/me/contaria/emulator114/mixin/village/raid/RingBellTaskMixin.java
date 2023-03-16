package me.contaria.emulator114.mixin.village.raid;

import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BellBlock;
import net.minecraft.entity.ai.brain.task.RingBellTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RingBellTask.class)
public abstract class RingBellTaskMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-153661
    @MCBug("MC-153661")
    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BellBlock;ring(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"))
    private boolean emulator114$ringBellFromImpossibleLocation(BellBlock bell, World world, BlockPos pos, Direction ignored, Operation<Boolean> original) {
        boolean result = false;
        for (Direction direction : Direction.Type.HORIZONTAL) {
            result = original.call(bell, world, pos, direction);
            if (result) {
                break;
            }
        }
        return result;
    }
}