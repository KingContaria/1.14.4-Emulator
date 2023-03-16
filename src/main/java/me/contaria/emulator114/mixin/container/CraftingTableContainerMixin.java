package me.contaria.emulator114.mixin.container;

import net.minecraft.container.CraftingTableContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftingTableContainer.class)
public abstract class CraftingTableContainerMixin {

    // Reverts: "Crafting Table: When the GUI is opened, Shift + clicking an item or a stack in the inventory now transfers it directly into the crafting grid."
    @Redirect(method = "transferSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/container/CraftingTableContainer;insertItem(Lnet/minecraft/item/ItemStack;IIZ)Z", ordinal = 1))
    private boolean emulator114$noShiftingItemsIntoCraftingTable(CraftingTableContainer instance, ItemStack itemStack, int i, int j, boolean b) {
        return false;
    }
}