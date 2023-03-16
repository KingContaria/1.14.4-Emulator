package me.contaria.emulator114.mixin.animation.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Right-clicking on flower pots with incompatible items"
    // Bugreport: https://bugs.mojang.com/browse/MC-160993
    @MCBug("MC-160993")
    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;CONSUME:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}