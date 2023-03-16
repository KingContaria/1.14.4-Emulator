package me.contaria.emulator114.mixin.precisionloss;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(BannerBlockEntityRenderer.class)
public abstract class BannerBlockEntityRendererMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-63720
    @MCBug("MC-63720")
    @Redirect(method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Ljava/lang/Math;floorMod(JJ)J"))
    private long emulator114$bannersStopWavingOnOldWorlds(long value, long one_hundred) {
        return value;
    }

    // see above
    @MCBug("MC-63720")
    @ModifyConstant(method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", constant = @Constant(floatValue = 100.0f))
    private float emulator114$bannersStopWavingOnOldWorlds(float constant) {
        return constant / 100.0f;
    }

    // see above
    @MCBug("MC-63720")
    @ModifyArg(method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;cos(F)F"))
    private float emulator114$bannersStopWavingOnOldWorlds2(float f) {
        return f / 100.0f;
    }
}