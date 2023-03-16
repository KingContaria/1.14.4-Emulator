package me.contaria.emulator114.mixin.network;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.attribute.AbstractEntityAttributeContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    // Bugreports: https://bugs.mojang.com/browse/MC-28447, https://bugs.mojang.com/browse/MC-29386, https://bugs.mojang.com/browse/MC-88179
    @MCBug({"MC-28447", "MC-29386", "MC-88179"})
    @Redirect(method = "onPlayerRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/AbstractEntityAttributeContainer;copyFrom(Lnet/minecraft/entity/attribute/AbstractEntityAttributeContainer;)V"))
    private void emulator114$noCopyingAttributes(AbstractEntityAttributeContainer instance, AbstractEntityAttributeContainer attributeContainer) {
    }
}