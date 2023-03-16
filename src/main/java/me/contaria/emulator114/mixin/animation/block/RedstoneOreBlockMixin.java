package me.contaria.emulator114.mixin.animation.block;

import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RedstoneOreBlock.class)
public abstract class RedstoneOreBlockMixin {

    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;SUCCESS:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$removeHandAnimation() {
        return ActionResult.PASS;
    }
}