package me.contaria.emulator114.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow protected abstract boolean canClimb();

    // Bugreport: https://bugs.mojang.com/browse/MC-102267
    @MCBug("MC-102267")
    @ModifyExpressionValue(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;bypassesSteppingEffects()Z"))
    private boolean emulator114$doNotStepOnBlocksIfEntityCantClimb(boolean bypassesSteppingEffects) {
        return bypassesSteppingEffects || !this.canClimb();
    }
}