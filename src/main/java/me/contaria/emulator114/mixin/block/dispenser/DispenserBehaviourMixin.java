package me.contaria.emulator114.mixin.block.dispenser;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Debug(export = true)
@Mixin(DispenserBehavior.class)
public interface DispenserBehaviourMixin {
/*
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

 */

    // Reverts: "Dispensers: Can now place down armor stands."
    // Bugreport: https://bugs.mojang.com/browse/MC-131061
    @MCBug("MC-131061")
    @Definition(id = "registerBehavior", method = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V")
    @Definition(id = "ARMOR_STAND", field = "Lnet/minecraft/item/Items;ARMOR_STAND:Lnet/minecraft/item/Item;")
    @Expression("registerBehavior(ARMOR_STAND, ?)")
    @SuppressWarnings("PublicStaticMixinMember")
    @Redirect(method = "registerDefaults", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    static void emulator114$removeArmorStandDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }

    // Reverts: "Dispensers: Can now collect water bottles when dispensing glass bottles on water."
    @Definition(id = "registerBehavior", method = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V")
    @Definition(id = "GLASS_BOTTLE", field = "Lnet/minecraft/item/Items;GLASS_BOTTLE:Lnet/minecraft/item/Item;")
    @Definition(id = "asItem", method = "Lnet/minecraft/item/Item;asItem()Lnet/minecraft/item/Item;")
    @Expression("registerBehavior(GLASS_BOTTLE.asItem(), ?)")
    @SuppressWarnings("PublicStaticMixinMember")
    @Redirect(method = "registerDefaults", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    static void emulator114$removeBottleDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }
}