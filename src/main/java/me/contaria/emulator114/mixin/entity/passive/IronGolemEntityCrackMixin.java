package me.contaria.emulator114.mixin.entity.passive;

import net.minecraft.entity.passive.IronGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(IronGolemEntity.Crack.class)
public abstract class IronGolemEntityCrackMixin {

    @ModifyVariable(method = "from", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static float emulator114$noDamagedTexture(float value) {
        return 1.0f;
    }
}