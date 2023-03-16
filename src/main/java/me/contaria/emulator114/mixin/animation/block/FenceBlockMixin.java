package me.contaria.emulator114.mixin.animation.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Right-clicking on fences; this also prevented blocks from being placed on fences from the offhand"
    // Bugreports: https://bugs.mojang.com/browse/MC-83998, https://bugs.mojang.com/browse/MC-119621
    @MCBug({"MC-83998", "MC-119621"})
    @ModifyExpressionValue(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item emulator114$addRedundantHandAnimation(Item item) {
        return item == Items.AIR ? Items.LEAD : item;
    }
}