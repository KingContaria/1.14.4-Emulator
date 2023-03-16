package me.contaria.emulator114.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=bee_spawn_egg"),
            to = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;BEE_SPAWN_EGG:Lnet/minecraft/item/Item;")
    ))
    private static Item emulator114$removeBeeSpawnEgg(String id, Item item) {
        return new AirBlockItem(Blocks.AIR, new Item.Settings());
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=honeycomb")
    ))
    private static Item emulator114$removeItems1(String id, Item item) {
        return new AirBlockItem(Blocks.AIR, new Item.Settings());
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Lnet/minecraft/block/Block;Lnet/minecraft/item/ItemGroup;)Lnet/minecraft/item/Item;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=honeycomb")
    ))
    private static Item emulator114$removeItems2(Block block, ItemGroup group) {
        return new AirBlockItem(Blocks.AIR, new Item.Settings());
    }
}