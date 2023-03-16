package me.contaria.emulator114.mixin.animation.block;

import net.minecraft.block.CakeBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CakeBlock.class)
public abstract class CakeBlockMixin {

    @Redirect(method = "tryEat", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;PASS:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}