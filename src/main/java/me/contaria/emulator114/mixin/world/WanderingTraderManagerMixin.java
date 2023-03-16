package me.contaria.emulator114.mixin.world;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WanderingTraderManager.class)
public abstract class WanderingTraderManagerMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-153298
    @MCBug("MC-153298")
    @Redirect(method = "method_18018", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WanderingTraderManager;method_23279(Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean emulator114$canSpawnInSmallSpaces(WanderingTraderManager manager, BlockPos blockPos) {
        return true;
    }
}