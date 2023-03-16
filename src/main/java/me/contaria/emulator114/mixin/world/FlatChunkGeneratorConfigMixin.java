package me.contaria.emulator114.mixin.world;

import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(FlatChunkGeneratorConfig.class)
public abstract class FlatChunkGeneratorConfigMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-164499
    @MCBug("MC-164499")
    @ModifyVariable(method = "fromString", at = @At("STORE"))
    private static Exception emulator114$crashOnFaultyBiome(Exception e) throws Exception {
        throw e;
    }

    // see above
    @MCBug("MC-164499")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Redirect(method = "fromString", at = @At(value = "INVOKE", target = "Ljava/util/Optional;orElseThrow(Ljava/util/function/Supplier;)Ljava/lang/Object;"))
    private static Object emulator114$crashOnFaultyBiome(Optional<Biome> optional, Supplier<?> exceptionSupplier, @Local Biome biome) {
        return optional.orElse(biome);
    }
}