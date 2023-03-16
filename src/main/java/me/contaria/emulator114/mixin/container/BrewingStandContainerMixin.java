package me.contaria.emulator114.mixin.container;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.container.BrewingStandContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BrewingStandContainer.class)
public abstract class BrewingStandContainerMixin {

    @WrapOperation(method = "transferSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/container/BrewingStandContainer$SlotFuel;matches(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean emulator114$cannotShiftClickBlazePowder(ItemStack itemStack, Operation<Boolean> original) {
        return !BrewingStandContainer.SlotPotion.matches(itemStack) && original.call(itemStack);
    }
}
