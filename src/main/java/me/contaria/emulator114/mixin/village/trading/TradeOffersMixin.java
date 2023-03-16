package me.contaria.emulator114.mixin.village.trading;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(TradeOffers.class)
public abstract class TradeOffersMixin {

    // Reverts: "Librarians now sell one bookshelf for nine emeralds, instead of three bookshelves for six emeralds."
    // Bugreport: https://bugs.mojang.com/browse/MC-153334
    @MCBug("MC-153334")
    @ModifyConstant(method = "method_16929", constant = @Constant(intValue = 9), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;BOOKSHELF:Lnet/minecraft/block/Block;", remap = true),
            to = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;BOOK:Lnet/minecraft/item/Item;", remap = true)
    ), remap = false)
    private static int emulator114$modifyBookShelfPrice(int price) {
        return 6;
    }

    // see above
    @MCBug("MC-153334")
    @ModifyConstant(method = "method_16929", constant = @Constant(intValue = 1, ordinal = 0), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;BOOKSHELF:Lnet/minecraft/block/Block;", remap = true),
            to = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;BOOK:Lnet/minecraft/item/Item;", remap = true)
    ), remap = false)
    private static int emulator114$modifyBookShelfAmount(int amount) {
        return 3;
    }
}