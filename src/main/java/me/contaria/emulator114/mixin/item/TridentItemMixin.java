package me.contaria.emulator114.mixin.item;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends Item {

    public TridentItemMixin(Settings settings) {
        super(settings);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-125360
    @MCBug("MC-125360")
    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return false;
    }
}