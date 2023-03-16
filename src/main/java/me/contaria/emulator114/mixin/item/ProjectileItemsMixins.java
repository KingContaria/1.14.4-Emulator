package me.contaria.emulator114.mixin.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        EggItem.class,
        EnderPearlItem.class,
        ExperienceBottleItem.class,
        SnowballItem.class
})
public abstract class ProjectileItemsMixins {

    @ModifyVariable(method = "use", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private ItemStack emulator114$decrementEarly(ItemStack stack, World world, PlayerEntity user) {
        if (!user.abilities.creativeMode) {
            stack.decrement(1);
        }
        return stack;
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void emulator114$decrementEarly(ItemStack stack, int amount) {
        if (amount != 1) throw new IllegalStateException("Conflict regarding ProjectileItemsMixin of 1.14 Emulator, 'amount' was not 1!");
    }
}