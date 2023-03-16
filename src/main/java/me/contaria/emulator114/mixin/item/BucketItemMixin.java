package me.contaria.emulator114.mixin.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Redirect(method = "placeFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;canBucketPlace(Lnet/minecraft/fluid/Fluid;)Z"))
    private boolean emulator114$canPlaceFluidsInEndPortal(BlockState blockState, Fluid fluid) {
        Material material = blockState.getMaterial();
        return !material.isSolid() || material.isReplaceable();
    }
}