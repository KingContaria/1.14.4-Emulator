package me.contaria.emulator114.mixin.entity.vehicle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "method_7555", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;updateTrackedPosition(DDD)V"))
    private void emulator114$doNotUpdateTrackedPosition(BoatEntity boat, double x, double y, double z) {
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V"))
    private void emulator114$updateTrackedPositionIncorrectly(CallbackInfo ci) {
        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();
    }
}