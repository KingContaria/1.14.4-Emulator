package me.contaria.emulator114.mixin.animation.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RedstoneOreBlock.class)
public abstract class RedstoneOreBlockMixin {

    // Reverts: "Added the hand use animation [...]: Activating redstone ore"
    // Bugreport: https://bugs.mojang.com/browse/MC-161151
    @MCBug("MC-161151")
    @Redirect(method = "onUse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;SUCCESS:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$removeHandAnimation() {
        return ActionResult.PASS;
    }
}