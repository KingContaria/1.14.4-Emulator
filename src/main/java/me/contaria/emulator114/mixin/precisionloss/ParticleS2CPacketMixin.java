package me.contaria.emulator114.mixin.precisionloss;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ParticleS2CPacket.class)
public abstract class ParticleS2CPacketMixin {

    // Reverts: "The floating-point precision used for the location of where certain particles and entities appear has increased to 64-bit (double-precision), from 32-bit (single-precision)"
    @ModifyArg(method = "write", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/PacketByteBuf;writeDouble(D)Lio/netty/buffer/ByteBuf;"))
    private double emulator114$precisionLossOnBufWrite(double value) {
        return (float) value;
    }

    // see above
    @ModifyReturnValue(method = {"getX", "getY", "getZ"}, at = @At("RETURN"), require = 3)
    private double emulator114$precisionLossOnGetter(double value) {
        return (float) value;
    }
}