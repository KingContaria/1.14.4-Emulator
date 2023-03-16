package me.contaria.emulator114.mixin.container;

import com.google.common.collect.ImmutableList;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.container.StonecutterContainer;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(StonecutterContainer.class)
public abstract class StonecutterContainerMixin {

    @Unique
    private static final ImmutableList<Item> INGREDIENTS = ImmutableList.of(Items.STONE, Items.SANDSTONE, Items.RED_SANDSTONE, Items.QUARTZ_BLOCK, Items.COBBLESTONE, Items.STONE_BRICKS, Items.BRICKS, Items.NETHER_BRICKS, Items.RED_NETHER_BRICKS, Items.PURPUR_BLOCK, Items.PRISMARINE, Items.PRISMARINE_BRICKS, Items.DARK_PRISMARINE, Items.ANDESITE, Items.POLISHED_ANDESITE, Items.GRANITE, Items.POLISHED_GRANITE, Items.DIORITE, Items.POLISHED_DIORITE, Items.MOSSY_STONE_BRICKS, Items.MOSSY_COBBLESTONE, Items.SMOOTH_SANDSTONE, Items.SMOOTH_RED_SANDSTONE, Items.SMOOTH_QUARTZ, Items.END_STONE, Items.END_STONE_BRICKS, Items.SMOOTH_STONE, Items.CUT_SANDSTONE, Items.CUT_RED_SANDSTONE);

    // Bugreport: https://bugs.mojang.com/browse/MC-142918
    @MCBug("MC-142918")
    @Redirect(method = "transferSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/RecipeManager;getFirstMatch(Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/world/World;)Ljava/util/Optional;"))
    private Optional<?> emulator114$noShiftingDataPackItems(RecipeManager manager, RecipeType<?> type, Inventory inventory, World world) {
        return INGREDIENTS.contains(inventory.getInvStack(0).getItem()) ? Optional.of(0) : Optional.empty();
    }
}