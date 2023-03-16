package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SoulSandBlock.class)
public abstract class SoulSandBlockMixin extends Block {

    public SoulSandBlockMixin(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.setVelocity(entity.getVelocity().multiply(0.4, 1.0, 0.4));
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-149928
    // specifically, the second part of the "Players also don't suffocate in soul sand, and it makes the entire world see-through" comment
    @MCBug("MC-149928")
    @SuppressWarnings("deprecation")
    @Override
    public boolean hasInWallOverlay(BlockState state, BlockView view, BlockPos pos) {
        return super.hasInWallOverlay(state, view, pos);
    }
}
