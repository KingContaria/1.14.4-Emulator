package me.contaria.emulator114.mixin.container;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.container.CartographyTableContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/container/CartographyTableContainer$5")
public abstract class CartographyTableContainerOutputSlotMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-152172
    @MCBug("MC-152172")
    @Redirect(method = "method_17387", at = @At(value = "INVOKE", target = "Lnet/minecraft/container/CartographyTableContainer;method_21826(Lnet/minecraft/container/CartographyTableContainer;)J"))
    private long emulator114$stackedCartographyTableSounds(CartographyTableContainer container) {
        return -1;
    }
}