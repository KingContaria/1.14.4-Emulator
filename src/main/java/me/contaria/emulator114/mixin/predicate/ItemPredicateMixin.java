package me.contaria.emulator114.mixin.predicate;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(ItemPredicate.class)
public abstract class ItemPredicateMixin {

    @Shadow @Final private EnchantmentPredicate[] storedEnchantments;

    // Reverts: "Item predicate in advancements now makes a distinction between actual enchantments and stored enchantments, like ones stored in enchanted books."
    // Bugreport: https://bugs.mojang.com/browse/MC-154873
    @MCBug("MC-154873")
    @ModifyExpressionValue(method = "*", at = @At(value = "FIELD", target = "Lnet/minecraft/predicate/item/ItemPredicate;enchantments:[Lnet/minecraft/predicate/item/EnchantmentPredicate;", opcode = Opcodes.GETFIELD))
    private EnchantmentPredicate[] emulator114$mergeEnchantmentPredicates(EnchantmentPredicate[] enchantments) {
        EnchantmentPredicate[] mergedPredicates = new EnchantmentPredicate[enchantments.length + this.storedEnchantments.length];
        System.arraycopy(enchantments, 0, mergedPredicates, 0, enchantments.length);
        System.arraycopy(this.storedEnchantments, 0, mergedPredicates, enchantments.length, this.storedEnchantments.length);
        return mergedPredicates;
    }

    // see above
    @MCBug("MC-154873")
    @Redirect(method = "test", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantments(Lnet/minecraft/nbt/ListTag;)Ljava/util/Map;"))
    private Map<Enchantment, Integer> emulator114$mergeEnchantmentCheck(ListTag tag, ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack);
    }
}
