package me.contaria.emulator114.mixin.block;

import net.minecraft.block.BellBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BellBlock.class)
public abstract class BellBlockMixin {

    // Reverts: "Bells: Will now ring if powered with a redstone signal."
    @Redirect(method = "neighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isReceivingRedstonePower(Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean emulator114$noRedstoneActivatingBells(World world, BlockPos pos) {
        return false;
    }
}