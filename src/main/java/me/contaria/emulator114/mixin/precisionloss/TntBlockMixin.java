package me.contaria.emulator114.mixin.precisionloss;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.TntBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin {

    // Reverts: "The floating-point precision used for the location of where certain particles and entities appear has increased to 64-bit [...]: Primed TNT entities"
    // Bugreport: https://bugs.mojang.com/browse/MC-125638
    @MCBug("MC-125638")
    @ModifyArg(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"), index = 1)
    private static double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    // see above
    @MCBug("MC-125638")
    @ModifyArg(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"), index = 3)
    private static double emulator114$precisionLossZ(double z) {
        return (float) z;
    }
}