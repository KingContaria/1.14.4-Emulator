package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HorseBaseEntity.class)
public abstract class HorseBaseEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-110907
    @MCBug("MC-110907")
    @Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;hasVanishingCurse(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean emulator114$noCurseOfVanishingEffects(ItemStack stack) {
        return false;
    }
}