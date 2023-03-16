package me.contaria.emulator114.mixin.entity.vehicle;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartEntityMixin extends AbstractMinecartEntity {

    protected FurnaceMinecartEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public double pushX;

    @Shadow public double pushZ;

    @Shadow protected abstract double getMaxOffRailSpeed();

    @Inject(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sqrt(D)F", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void emulator114$slowDownOnCorners(BlockPos pos, BlockState state, CallbackInfo ci, double f, double g, Vec3d vec3d) {
        g = MathHelper.sqrt(g);
        this.pushX /= g;
        this.pushZ /= g;
        if (this.pushX * vec3d.x + this.pushZ * vec3d.z < 0.0D) {
            this.pushX = 0.0D;
            this.pushZ = 0.0D;
        } else {
            double e = g / this.getMaxOffRailSpeed();
            this.pushX *= e;
            this.pushZ *= e;
        }
        ci.cancel();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-112990
    @MCBug("MC-112990")
    @Redirect(method = "interact", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/vehicle/FurnaceMinecartEntity;fuel:I", opcode = Opcodes.GETFIELD, ordinal = 2))
    private int emulator114$pushWithoutFuel(FurnaceMinecartEntity entity) {
        return Integer.MAX_VALUE;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isClient()Z"))
    private boolean emulator114$setFuelClientSide(World world) {
        return false;
    }
}