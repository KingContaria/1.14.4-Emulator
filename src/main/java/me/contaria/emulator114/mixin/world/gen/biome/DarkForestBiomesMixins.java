package me.contaria.emulator114.mixin.world.gen.biome;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.world.biome.DarkForestBiome;
import net.minecraft.world.biome.DarkForestHillsBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin({
        DarkForestBiome.class,
        DarkForestHillsBiome.class
})
public abstract class DarkForestBiomesMixins {

    // Reverts: "Birch trees generate in the dark forest biome once again."
    // Bugreport: https://bugs.mojang.com/browse/MC-164397
    @MCBug("MC-164397")
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/ConfiguredFeature;withChance(F)Lnet/minecraft/world/gen/feature/RandomFeatureEntry;", ordinal = 0), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/world/biome/DefaultBiomeFeatures;BIRCH_TREE_CONFIG:Lnet/minecraft/world/gen/feature/BranchedTreeFeatureConfig;")
    ), index = 0)
    private float emulator114$noBirchTrees(float chance) {
        return 0.0f;
    }
}