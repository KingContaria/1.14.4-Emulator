package me.contaria.emulator114.mixin.animation.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.DecorationItem;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DecorationItem.class)
public abstract class DecorationItemMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Trying to place paintings in invalid positions"
    // Bugreport: https://bugs.mojang.com/browse/MC-129273
    @MCBug("MC-129273")
    @Redirect(method = "useOnBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;CONSUME:Lnet/minecraft/util/ActionResult;"))
    private ActionResult emulator114$addRedundantHandAnimation() {
        return ActionResult.SUCCESS;
    }
}