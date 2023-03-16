package me.contaria.emulator114.mixin.animation.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BellBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BellBlock.class)
public abstract class BellBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Trying to ring bells from angles they can't be used from"
    // Bugreport: https://bugs.mojang.com/browse/MC-147549
    @MCBug("MC-147549")
    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;PASS:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}