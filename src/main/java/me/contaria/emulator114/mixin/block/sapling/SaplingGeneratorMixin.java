package me.contaria.emulator114.mixin.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SaplingGenerator.class)
public abstract class SaplingGeneratorMixin {

    // Reverts: "Oak and birch saplings grown with a flower 2 blocks away within a 5×3×5 volume centered on the sapling have a 5% chance to generate a bee nest on the side of the tree."
    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/sapling/SaplingGenerator;method_24282(Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean emulator114$noBeehivesOnSaplingsWithFlowers(SaplingGenerator generator, IWorld iWorld, BlockPos blockPos) {
        return false;
    }
}
