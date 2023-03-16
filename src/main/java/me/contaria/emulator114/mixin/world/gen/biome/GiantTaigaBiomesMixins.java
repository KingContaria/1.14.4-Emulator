package me.contaria.emulator114.mixin.world.gen.biome;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.world.biome.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        GiantSpruceTaigaBiome.class,
        GiantSpruceTaigaHillsBiome.class,
        GiantTreeTaigaBiome.class,
        GiantTreeTaigaHillsBiome.class
})
public abstract class GiantTaigaBiomesMixins {

    // Reverts: "Sweet berry bushes: Now spawn in the giant tree taiga biome."
    // Bugreport: https://bugs.mojang.com/browse/MC-166590
    @MCBug("MC-166590")
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/DefaultBiomeFeatures;addSweetBerryBushes(Lnet/minecraft/world/biome/Biome;)V"))
    private void emulator114$noSweetBerryBushes(Biome biome) {
    }
}