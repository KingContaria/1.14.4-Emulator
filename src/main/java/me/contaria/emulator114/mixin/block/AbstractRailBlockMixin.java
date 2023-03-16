package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractRailBlock.class)
public abstract class AbstractRailBlockMixin {

    // Reverts: "Now places facing the player, rather than always north-south."
    // Bugreport: https://bugs.mojang.com/browse/MC-791
    @MCBug("MC-791")
    @Redirect(method = "getPlacementState", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemPlacementContext;getPlayerFacing()Lnet/minecraft/util/math/Direction;"))
    private Direction emulator114$noPlacingRailsRelativeToPlayer(ItemPlacementContext context) {
        return Direction.NORTH;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-162261
    @MCBug("MC-162261")
    @ModifyExpressionValue(method = "onBlockAdded", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;", ordinal = 0))
    private Block emulator114$noImmediateClientSideUpdate(Block block, BlockState state, World world, BlockPos pos, BlockState oldState, boolean moved) {
        return !world.isClient ? block : null;
    }
}