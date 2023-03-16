package me.contaria.emulator114.mixin.animation.block;

import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;CONSUME:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}