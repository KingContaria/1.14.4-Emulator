package me.contaria.emulator114.mixin.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.ShovelItem;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {

    // Reverts: "Campfires: Can now be extinguished with a shovel."
    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"))
    private Comparable<Boolean> emulator114$noExtinguishingCampfires(BlockState state, Property<Boolean> property) {
        return false;
    }
}