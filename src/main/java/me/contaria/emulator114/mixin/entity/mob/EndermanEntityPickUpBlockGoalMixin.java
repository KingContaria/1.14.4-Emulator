package me.contaria.emulator114.mixin.entity.mob;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net/minecraft/entity/mob/EndermanEntity$PickUpBlockGoal")
public abstract class EndermanEntityPickUpBlockGoalMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-155758
    @MCBug("MC-155758")
    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;equals(Ljava/lang/Object;)Z"))
    private boolean emulator114$noHoldingHitTypeMiss(boolean original, @Local BlockHitResult blockHitResult) {
        return original && blockHitResult.getType() != HitResult.Type.MISS;
    }
}