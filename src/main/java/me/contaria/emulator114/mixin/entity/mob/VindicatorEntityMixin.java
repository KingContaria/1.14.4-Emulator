package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.VindicatorEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VindicatorEntity.class)
public abstract class VindicatorEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-158484
    @MCBug("MC-158484")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "mobTick", constant = @Constant(classValue = MobNavigation.class, ordinal = 0))
    private boolean emulator114$vindicatorsCrashWhenRidingEntities(Object o, Class<?> c) {
        return true;
    }
}