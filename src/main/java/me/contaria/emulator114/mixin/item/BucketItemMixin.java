package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-11944
    @MCBug("MC-11944")
    @Redirect(method = "placeFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;canBucketPlace(Lnet/minecraft/fluid/Fluid;)Z"))
    private boolean emulator114$canPlaceFluidsInEndPortal(BlockState blockState, Fluid fluid) {
        Material material = blockState.getMaterial();
        return !material.isSolid() || material.isReplaceable();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-136470
    @MCBug("MC-136470")
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;offset(Lnet/minecraft/util/math/Direction;)Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos emulator114$checkWrongBlockForPlacingWater(BlockPos pos, Direction direction) {
        return pos;
    }
}