package me.contaria.emulator114.mixin.misc;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-83003
    @MCBug("MC-83003")
    @Redirect(method = "doRandomBlockDisplayTicks", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getItemsHand()Ljava/lang/Iterable;"))
    private Iterable<ItemStack> emulator114$noShowingBarriersWhenInOffhand(ClientPlayerEntity player) {
        return Collections.singleton(player.getMainHandStack());
    }
}