package me.contaria.emulator114.mixin.client.debug;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-164129
    // This is kind of a special case because the bug was introduced, not fixed in 1.15
    @MCBug("MC-164129")
    @Redirect(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getX()D", ordinal = 0))
    private double emulator114$fixDragonHitboxX(Entity dragon) {
        return 0.0;
    }

    @MCBug("MC-164129")
    @Redirect(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getY()D", ordinal = 0))
    private double emulator114$fixDragonHitboxY(Entity dragon) {
        return 0.0;
    }

    @MCBug("MC-164129")
    @Redirect(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getZ()D", ordinal = 0))
    private double emulator114$fixDragonHitboxZ(Entity dragon) {
        return 0.0;
    }
}