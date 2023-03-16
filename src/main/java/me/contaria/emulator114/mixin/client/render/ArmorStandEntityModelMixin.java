package me.contaria.emulator114.mixin.client.render;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityModel.class)
public abstract class ArmorStandEntityModelMixin {

    @Shadow @Final private ModelPart plate;

    // Bugreport: https://bugs.mojang.com/browse/MC-81970
    @MCBug("MC-81970")
    @Inject(method = "setAngles(Lnet/minecraft/entity/decoration/ArmorStandEntity;FFFFF)V", at = @At("TAIL"))
    private void emulator114$shakeBasePlate(ArmorStandEntity armorStandEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        this.plate.pitch = 0.0F;
        this.plate.yaw = 0.017453292F * -armorStandEntity.yaw;
        this.plate.roll = 0.0F;
    }
}