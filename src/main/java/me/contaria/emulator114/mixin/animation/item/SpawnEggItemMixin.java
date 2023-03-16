package me.contaria.emulator114.mixin.animation.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.TypedActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItemMixin {

    // Reverts: "Added the hand use animation [...]: Using spawn eggs on water"
    // Bugreport: https://bugs.mojang.com/browse/MC-132445
    @MCBug("MC-132445")
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/TypedActionResult;success(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;", ordinal = 0))
    private TypedActionResult<?> emulator114$removeHandAnimation(Object data) {
        return TypedActionResult.pass(data);
    }
}