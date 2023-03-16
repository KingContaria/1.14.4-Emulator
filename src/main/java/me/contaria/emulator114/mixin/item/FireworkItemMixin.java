package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.FireworkItem;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkItem.class)
public abstract class FireworkItemMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-4240
    @MCBug("MC-4240")
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Direction;getOffsetX()I"))
    private int emulator114$noDirectionAdjustment1(Direction direction) {
        return 0;
    }

    // see above
    @MCBug("MC-4240")
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Direction;getOffsetY()I"))
    private int emulator114$noDirectionAdjustment2(Direction direction) {
        return 0;
    }

    // see above
    @MCBug("MC-4240")
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Direction;getOffsetZ()I"))
    private int emulator114$noDirectionAdjustment3(Direction direction) {
        return 0;
    }
}