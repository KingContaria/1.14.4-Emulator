package me.contaria.emulator114.mixin.client.render;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntityRenderer.class)
public abstract class CreeperEntityRendererMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-47941
    @MCBug("MC-47941")
    @Redirect(method = "getWhiteOverlayProgress(Lnet/minecraft/entity/mob/CreeperEntity;F)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"))
    private float emulator114$creeperOverlayIsCompletelyWhite(float value, float min, float max) {
        return max;
    }
}