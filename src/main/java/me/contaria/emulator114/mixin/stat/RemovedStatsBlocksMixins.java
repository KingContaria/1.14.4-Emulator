package me.contaria.emulator114.mixin.stat;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.GrindstoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        AnvilBlock.class,
        GrindstoneBlock.class
})
public abstract class RemovedStatsBlocksMixins {

    // Reverts: "Added statistics for anvil and grindstone interaction counts."
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;incrementStat(Lnet/minecraft/util/Identifier;)V"))
    private void emulator114$doNotIncrementStat(PlayerEntity player, Identifier stat) {
    }
}