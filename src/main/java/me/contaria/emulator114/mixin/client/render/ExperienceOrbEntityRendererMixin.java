package me.contaria.emulator114.mixin.client.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExperienceOrbEntityRenderer.class)
public abstract class ExperienceOrbEntityRendererMixin {

    // Reverts: "Experience orbs: Now render as translucent again."
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getEntityTranslucent(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"))
    private static RenderLayer emulator114$noTranslucentXPOrb(Identifier identifier) {
        return RenderLayer.getEntityCutout(identifier);
    }
}