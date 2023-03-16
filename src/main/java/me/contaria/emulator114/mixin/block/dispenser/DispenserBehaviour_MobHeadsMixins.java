package me.contaria.emulator114.mixin.block.dispenser;

import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = {
        "net/minecraft/block/dispenser/DispenserBehavior$10",
        "net/minecraft/block/dispenser/DispenserBehavior$11",
        "net/minecraft/block/dispenser/DispenserBehavior$13"
})
public abstract class DispenserBehaviour_MobHeadsMixins {

    // Bugreport: https://bugs.mojang.com/browse/MC-163879
    @MCBug("MC-163879")
    @ModifyExpressionValue(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ArmorItem;dispenseArmor(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Z"))
    private boolean emulator114$failSoundOnSkullEquip(boolean value, BlockPointer pointer, ItemStack stack) {
        return !stack.isEmpty();
    }
}