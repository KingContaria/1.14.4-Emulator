package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.block.enums.ComparatorMode;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ComparatorBlock.class)
public abstract class ComparatorBlockMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-12211
    @MCBug("MC-12211")
    @Redirect(method = "hasPower", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"))
    private Comparable<?> emulator114$bugInSubtractionMode(BlockState state, Property<?> property) {
        return ComparatorMode.COMPARE;
    }
}