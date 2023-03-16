package me.contaria.emulator114.mixin.animation.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.CakeBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CakeBlock.class)
public abstract class CakeBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Trying to eat cake when hunger bar is full"
    // Bugreport: https://bugs.mojang.com/browse/MC-106826
    @MCBug("MC-106826")
    @Redirect(method = "tryEat", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;PASS:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}