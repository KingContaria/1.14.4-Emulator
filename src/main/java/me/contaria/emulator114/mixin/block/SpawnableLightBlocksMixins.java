package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({
        CarvedPumpkinBlock.class,
        RedstoneLampBlock.class
})
public abstract class SpawnableLightBlocksMixins extends Block {

    public SpawnableLightBlocksMixins(Settings settings) {
        super(settings);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-158843
    @MCBug("MC-158843")
    @Deprecated
    @Override
    public boolean allowsSpawning(BlockState state, BlockView view, BlockPos pos, EntityType<?> type) {
        return super.allowsSpawning(state, view, pos, type);
    }
}