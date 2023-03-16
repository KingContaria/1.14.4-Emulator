package me.contaria.emulator114.mixin.animation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Redirect(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V"))
    private void emulator114$removeHandAnimationWhenDroppingItems(ClientPlayerEntity player, Hand hand) {
    }

    @Redirect(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;shouldSwingHand()Z", ordinal = 0))
    private boolean emulator114$removeHandAnimationWhenInteractingWithAnimals(ActionResult actionResult) {
        return false;
    }

    @Redirect(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;shouldSwingHand()Z", ordinal = 2))
    private boolean emulator114$removeHandAnimationWhenInteractingWithItems(ActionResult actionResult) {
        return false;
    }
}