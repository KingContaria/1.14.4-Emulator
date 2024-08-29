package me.contaria.emulator114.mixin.block;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.PistonExtensionBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    // Reverts: "Farmland: Blocks that can be manually placed on farmland without turning it into dirt can now also be pushed into
    //          (the space above) it by a piston without turning the farmland under the block the piston has pushed into dirt."
    @Definition(id = "PistonExtensionBlock", type = PistonExtensionBlock.class)
    @Definition(id = "getBlock", method = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;")
    @Expression("?.getBlock() instanceof PistonExtensionBlock")
    @Redirect(method = "canPlaceAt", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean emulator114$destroyingByPiston(Object o, Class<?> c) {
        return false;
    }
}