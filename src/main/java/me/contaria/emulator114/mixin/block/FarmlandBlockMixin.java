package me.contaria.emulator114.mixin.block;

import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.PistonExtensionBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    // Reverts: "Farmland: Blocks that can be manually placed on farmland without turning it into dirt can now also be pushed into
    //          (the space above) it by a piston without turning the farmland under the block the piston has pushed into dirt."
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "canPlaceAt", constant = @Constant(classValue = PistonExtensionBlock.class))
    private boolean emulator114$destroyingByPiston(Object o, Class<?> c) {
        return false;
    }
}