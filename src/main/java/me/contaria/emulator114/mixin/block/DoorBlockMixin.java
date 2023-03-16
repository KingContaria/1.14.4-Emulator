package me.contaria.emulator114.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DoorBlock.class)
public abstract class DoorBlockMixin {

    // Reverts: "Iron Doors: Must now be mined with a pickaxe for it to be dropped as an item."
    @Redirect(method = "onBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isUsingEffectiveTool(Lnet/minecraft/block/BlockState;)Z"))
    private boolean emulator114$canMineIronDoorsWithHand(PlayerEntity player, BlockState state) {
        return true;
    }
}