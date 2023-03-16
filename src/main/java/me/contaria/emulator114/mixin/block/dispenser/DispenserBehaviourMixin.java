package me.contaria.emulator114.mixin.block.dispenser;

import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("PublicStaticMixinMember")
@Mixin(DispenserBehavior.class)
public interface DispenserBehaviourMixin {

    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V", ordinal = 9))
    static void emulator114$removeArmorStandDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }

    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/DispenserBlock;registerBehavior(Lnet/minecraft/item/ItemConvertible;Lnet/minecraft/block/dispenser/DispenserBehavior;)V", ordinal = 37))
    static void emulator114$removeBottleDispenserBehaviour(ItemConvertible provider, DispenserBehavior behavior) {
    }
}