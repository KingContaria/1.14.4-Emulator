package me.contaria.emulator114.mixin.resources;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.MinecraftVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftVersion.class)
public abstract class MinecraftVersionMixin {

    @ModifyReturnValue(method = {"getReleaseTarget", "getName"}, at = @At("RETURN"), require = 2)
    private String emulator114$modifyReleaseTarget(String releaseTarget) {
        return "1.14.4...?";
    }

    @ModifyReturnValue(method = "isStable", at = @At("RETURN"))
    private boolean emulator114$setUnstable(boolean stable) {
        return false;
    }
}