package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-125880, https://bugs.mojang.com/browse/MC-136352
    @MCBug({"MC-136352", "MC-125880"})
    @Redirect(method = {"<init>(Lnet/minecraft/item/ItemConvertible;I)V", "setTag"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;isDamageable()Z"))
    private boolean emulator114$noSettingInitialDamage(Item item) {
        return false;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-86619
    @MCBug("MC-86619")
    @Redirect(method = "copy", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private boolean emulator114$doNotReturnItemStackEMPTY(ItemStack itemStack) {
        return false;
    }
}