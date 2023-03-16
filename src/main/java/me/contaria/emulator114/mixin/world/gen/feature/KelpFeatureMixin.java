package me.contaria.emulator114.mixin.world.gen.feature;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.world.gen.feature.KelpFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(KelpFeature.class)
public abstract class KelpFeatureMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-162905
    @MCBug("MC-162905")
    @ModifyArg(method = "generate(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)Z", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/block/KelpBlock;AGE:Lnet/minecraft/state/property/IntProperty;", ordinal = 0)
    ))
    private int emulator114$kelpGrowsYoung1(int bound) {
        return bound + 20;
    }

    // see above
    @MCBug("MC-162905")
    @ModifyConstant(method = "generate(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)Z", constant = @Constant(intValue = 20))
    private int emulator114$kelpGrowsYoung2(int bound) {
        return bound - 20;
    }
}