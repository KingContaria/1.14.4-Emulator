package me.contaria.emulator114.mixin.animation.block;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.FenceBlock;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin {

    // Reverts: "Redundant hand use animations have been removed from the following actions:
    //          Right-clicking on fences; this also prevented blocks from being placed on fences from the offhand"
    // Bugreports: https://bugs.mojang.com/browse/MC-83998, https://bugs.mojang.com/browse/MC-119621
    @MCBug({"MC-83998", "MC-119621"})
    @Definition(id = "getItem", method = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;")
    @Definition(id = "LEAD", field = "Lnet/minecraft/item/Items;LEAD:Lnet/minecraft/item/Item;")
    @Expression("?.getItem() == LEAD")
    @WrapOperation(method = "onUse", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean emulator114$addRedundantHandAnimation(Object item, Object lead, Operation<Boolean> original) {
        return original.call(item, lead) || item == Items.AIR;
    }
}