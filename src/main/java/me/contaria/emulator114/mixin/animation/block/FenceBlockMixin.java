package me.contaria.emulator114.mixin.animation.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin {

    @ModifyExpressionValue(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item emulator114$addRedundantHandAnimation(Item item) {
        return item == Items.AIR ? Items.LEAD : item;
    }
}