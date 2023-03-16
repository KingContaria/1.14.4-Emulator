package me.contaria.emulator114.mixin.world.gen.biome;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GiantTreeTaigaHillsBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GiantTreeTaigaHillsBiome.class)
public abstract class GiantTreeTaigaHillsBiomeMixin {

    // Reverts: "Foxes now spawn in all taiga biome variants."
    // Bugreport: https://bugs.mojang.com/browse/MC-155591
    @MCBug("MC-155591")
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/GiantTreeTaigaHillsBiome;addSpawn(Lnet/minecraft/entity/EntityCategory;Lnet/minecraft/world/biome/Biome$SpawnEntry;)V", ordinal = 0), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityType;FOX:Lnet/minecraft/entity/EntityType;")
    ))
    private void emulator114$noFoxes(GiantTreeTaigaHillsBiome biome, EntityCategory entityCategory, Biome.SpawnEntry spawnEntry) {
    }
}