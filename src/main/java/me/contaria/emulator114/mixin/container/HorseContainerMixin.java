package me.contaria.emulator114.mixin.container;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.container.HorseContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(HorseContainer.class)
public abstract class HorseContainerMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-156276
    @MCBug("MC-156276")
    @Redirect(method = "transferSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/container/HorseContainer;insertItem(Lnet/minecraft/item/ItemStack;IIZ)Z"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "intValue=9")
    ))
    private boolean emulator114$noShiftingItems(HorseContainer container, ItemStack itemStack, int i, int j, boolean b) {
        return false;
    }
}