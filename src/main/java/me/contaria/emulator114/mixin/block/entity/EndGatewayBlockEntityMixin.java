package me.contaria.emulator114.mixin.block.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndGatewayBlockEntity.class)
public abstract class EndGatewayBlockEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-155616
    @MCBug("MC-155616")
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getRootVehicle()Lnet/minecraft/entity/Entity;"))
    private Entity emulator114$passengersGoMissingWhenTeleporting(Entity entity) {
        return entity;
    }
}
