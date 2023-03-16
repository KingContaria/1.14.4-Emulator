package me.contaria.emulator114.mixin.world;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlockRenderView.class)
public interface BlockRenderViewMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-68565
    @MCBug("MC-68565")
    @ModifyVariable(method = "getBaseLightLevel", at = @At("HEAD"), argsOnly = true)
    default BlockPos emulator114$grabWrongLightLevelAbove255(BlockPos blockPos) {
        return blockPos.getY() >= 256 ? new BlockPos(blockPos.getX(), 255, blockPos.getZ()) : blockPos;
    }
}