package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-120558
    @MCBug("MC-120558")
    @ModifyVariable(method = "finishUsing", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private PlayerEntity emulator114$decrementEarly(PlayerEntity user, ItemStack stack) {
        if (user == null || !user.abilities.creativeMode) {
            stack.decrement(1);
        }
        return user;
    }

    // see above
    @MCBug("MC-120558")
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void emulator114$decrementEarly(ItemStack stack, int amount) {
        if (amount != 1) throw new IllegalStateException("Conflict regarding PotionItemMixin of 1.14 Emulator, 'amount' was not 1!");
    }
}