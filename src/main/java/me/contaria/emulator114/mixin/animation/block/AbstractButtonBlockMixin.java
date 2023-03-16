package me.contaria.emulator114.mixin.animation.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractButtonBlock.class)
public abstract class AbstractButtonBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Trying to use active buttons"
    // Bugreport: https://bugs.mojang.com/browse/MC-161220
    @MCBug("MC-161220")
    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;CONSUME:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}