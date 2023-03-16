package me.contaria.emulator114.mixin.block.dispenser;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DispenserBehavior.class)
public interface DispenserBehaviourMixin {

    // Reverts: "Dispensers: Can now place down armor stands."
    // Bugreport: https://bugs.mojang.com/browse/MC-131061
    @MCBug("MC-131061")
    @SuppressWarnings("PublicStaticMixinMember")
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V", ordinal = 9))
    static void emulator114$removeArmorStandDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }

    // Reverts: "Dispensers: Can now collect water bottles when dispensing glass bottles on water."
    @SuppressWarnings("PublicStaticMixinMember")
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V", ordinal = 37))
    static void emulator114$removeBottleDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }
}