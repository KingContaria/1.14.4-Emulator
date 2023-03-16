package me.contaria.emulator114.mixin.entity.player;

import me.contaria.emulator114.plugin.annotations.MCBug;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    // Reverts: "Elytra can now start to glide immediately after jumping (while rising), not only during the descent of the jump."
    // Bugreport: https://bugs.mojang.com/browse/MC-111444
    @MCBug("MC-111444")
    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isClimbing()Z"))
    private boolean emulator114$changeElytraMovement(ClientPlayerEntity player) {
        return !(this.getVelocity().y < 0.0D);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-126856
    @MCBug("MC-126856")
    @Redirect(method = "method_22119", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;clipAtLedge()Z"))
    private boolean emulator_breakAutoJump(ClientPlayerEntity player) {
        return false;
    }
}