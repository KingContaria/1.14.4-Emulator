package me.contaria.emulator114.mixin.block.dispenser;

import net.minecraft.entity.FireworkEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$3")
public abstract class DispenserBehaviour_FireworkRocketMixin {

    @Redirect(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/FireworkEntity;setVelocity(DDDFF)V"))
    private void emulator114$alwaysLaunchFireworksUpwards(FireworkEntity firework, double x, double y, double z, float speed, float divergence) {
    }

    @ModifyArg(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/FireworkEntity;<init>(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;DDDZ)V"), index = 5)
    private boolean emulator114$noShootingFireworksAtAngle(boolean shotAtAngle) {
        return false;
    }
}