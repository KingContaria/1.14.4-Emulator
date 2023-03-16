package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.Tag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-101700
    @MCBug("MC-101700")
    @Redirect(method = {"damage", "teleportRandomly"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isClient()Z"), require = 2)
    private boolean emulator114$desyncEnderman(World world) {
        return false;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-15862
    @MCBug("MC-15862")
    @Redirect(method = "teleportRandomly", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/EndermanEntity;isAlive()Z"))
    private boolean emulator114$teleportWhileDead(EndermanEntity enderman) {
        return true;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-136074
    @MCBug("MC-136074")
    @Redirect(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;matches(Lnet/minecraft/tag/Tag;)Z"))
    private boolean emulator114$canTeleportOntoWaterlogged(FluidState state, Tag<Fluid> tag) {
        return false;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-115567
    @MCBug("MC-115567")
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = -2147483648))
    private int emulator114$noAngrySoundInFirst400Ticks(int minValue) {
        return 0;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-88209
    @MCBug("MC-88209")
    @Redirect(method = "onTrackedDataSet", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/EndermanEntity;method_22330()Z"))
    private boolean emulator114$playStareSoundOnAttack(EndermanEntity enderman) {
        return enderman.isAngry();
    }
}