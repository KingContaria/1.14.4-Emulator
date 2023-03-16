package me.contaria.emulator114.mixin.precisionloss;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntityRenderer.class)
public abstract class FallingBlockEntityRendererMixin {

    // TODO: this seems to be bullshit
    /*
    @ModifyArg(method = "render(Lnet/minecraft/entity/FallingBlockEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;<init>(DDD)V"), index = 0)
    private double emulator114$precisionLossX(double x) {
        return (float) x;
    }

    @ModifyArg(method = "render(Lnet/minecraft/entity/FallingBlockEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;<init>(DDD)V"), index = 1)
    private double emulator114$precisionLossY(double y) {
        return (float) y;
    }

    @ModifyArg(method = "render(Lnet/minecraft/entity/FallingBlockEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;<init>(DDD)V"), index = 2)
    private double emulator114$precisionLossZ(double z) {
        return (float) z;
    }

     */

    @Inject(method = "render(Lnet/minecraft/entity/FallingBlockEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    private void test(FallingBlockEntity fallingBlockEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        BlockPos blockPos = new BlockPos(fallingBlockEntity.getX(), fallingBlockEntity.getBoundingBox().y2, fallingBlockEntity.getZ());
        System.out.println("x: " + blockPos.getX() + ", " + fallingBlockEntity.lastRenderX);
        System.out.println("y: " + blockPos.getY() + ", " + fallingBlockEntity.lastRenderY);
        System.out.println("z: " + blockPos.getZ() + ", " + fallingBlockEntity.lastRenderZ);
    }
}