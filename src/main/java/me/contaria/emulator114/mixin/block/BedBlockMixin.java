package me.contaria.emulator114.mixin.block;

import net.minecraft.block.BedBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BedBlock;method_22357(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean emulator114$noWakingUpVillagers(BedBlock bed, World world, BlockPos pos) {
        return false;
    }
}