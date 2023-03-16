package me.contaria.emulator114.mixin.client.render;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WolfEntityModel.class)
public abstract class WolfEntityModelMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-117635
    @MCBug("MC-117635")
    @ModifyConstant(method = "animateModel(Lnet/minecraft/entity/passive/WolfEntity;FFF)V", constant = @Constant(floatValue = 22.7f))
    private float emulator114$liftSittingDogsLegs(float constant) {
        return constant - 0.7f;
    }
}