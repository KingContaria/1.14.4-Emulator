package me.contaria.emulator114.mixin.block;

import net.minecraft.block.WetSpongeBlock;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WetSpongeBlock.class)
public abstract class WetSpongeBlockMixin {

    // Reverts: "Wet Sponges: Now dry out when placed in the Nether."
    @Redirect(method = "onBlockAdded", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;doesWaterVaporize()Z"))
    private boolean emulator114$dontDrySpongeInNether(Dimension dimension) {
        return false;
    }
}