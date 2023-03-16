package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-136352
    @MCBug("MC-136352")
    @Redirect(method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;isDamageable()Z"))
    private boolean emulator114$noSettingInitialDamage(Item item) {
        return false;
    }
}