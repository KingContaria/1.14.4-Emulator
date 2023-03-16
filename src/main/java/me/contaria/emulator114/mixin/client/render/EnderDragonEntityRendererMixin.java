package me.contaria.emulator114.mixin.client.render;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnderDragonEntityRenderer.class)
public abstract class EnderDragonEntityRendererMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-106468
    @MCBug("MC-106468")
    @ModifyConstant(method = "renderCrystalBeam", constant = @Constant(intValue = 1))
    private static int emulator114$buggedEndCrystalBeamRendering(int value) {
        return 0;
    }

    // see above
    @MCBug("MC-106468")
    @ModifyVariable(method = "renderCrystalBeam", at = @At("STORE"), ordinal = 13)
    private static float emulator114$buggedEndCrystalBeamRendering(float value) {
        return value % 1;
    }
}