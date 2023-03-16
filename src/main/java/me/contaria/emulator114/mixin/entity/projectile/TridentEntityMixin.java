package me.contaria.emulator114.mixin.entity.projectile;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin {

    // Bugreports: https://bugs.mojang.com/browse/MC-94421, https://bugs.mojang.com/browse/MC-129781
    @MCBug({"MC-94421", "MC-129781"})
    @Redirect(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getType()Lnet/minecraft/entity/EntityType;"))
    private EntityType<?> emulator114$collideWithEndermen(Entity entity) {
        return null;
    }
}