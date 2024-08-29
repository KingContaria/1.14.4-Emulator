package me.contaria.emulator114.mixin.client.debug;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
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
    @Definition(id = "getX", method = "Lnet/minecraft/entity/Entity;getX()D")
    @Definition(id = "lerp", method = "Lnet/minecraft/util/math/MathHelper;lerp(DDD)D")
    @Definition(id = "entity", local = @Local(type = Entity.class, argsOnly = true))
    @Definition(id = "lastRenderX", field = "Lnet/minecraft/entity/Entity;lastRenderX:D")
    @Expression("@(entity.getX()) - lerp(?, entity.lastRenderX, entity.getX())")
    @Redirect(method = "renderHitbox", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private double emulator114$fixDragonHitboxX(Entity dragon) {
        return 0.0;
    }

    // see above
    @Definition(id = "entity", local = @Local(type = Entity.class, argsOnly = true))
    @Definition(id = "getY", method = "Lnet/minecraft/entity/Entity;getY()D")
    @Definition(id = "lerp", method = "Lnet/minecraft/util/math/MathHelper;lerp(DDD)D")
    @Definition(id = "lastRenderY", field = "Lnet/minecraft/entity/Entity;lastRenderY:D")
    @Expression("@(entity.getY()) - lerp(?, entity.lastRenderY, entity.getY())")
    @MCBug("MC-164129")
    @Redirect(method = "renderHitbox", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private double emulator114$fixDragonHitboxY(Entity dragon) {
        return 0.0;
    }
/*
    // see above
    @Expression("")
    @MCBug("MC-164129")
    @Redirect(method = "renderHitbox", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private double emulator114$fixDragonHitboxZ(Entity dragon) {
        return 0.0;
    }

 */
}