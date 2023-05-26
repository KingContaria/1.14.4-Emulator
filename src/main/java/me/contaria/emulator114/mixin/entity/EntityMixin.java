package me.contaria.emulator114.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow protected abstract boolean canClimb();

    @Shadow private boolean teleportRequested;

    @Shadow public World world;

    // Bugreport: https://bugs.mojang.com/browse/MC-102267
    @MCBug("MC-102267")
    @ModifyExpressionValue(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;bypassesSteppingEffects()Z"))
    private boolean emulator114$doNotStepOnBlocksIfEntityCantClimb(boolean bypassesSteppingEffects) {
        return bypassesSteppingEffects || !this.canClimb();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-155616
    @MCBug("MC-155616")
    @Redirect(method = "requestTeleport", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", remap = false))
    private void emulator114$passengersGoMissingWhenTeleporting(Stream<?> stream, Consumer<?> consumer) {
        this.teleportRequested = true;
        ((ServerWorld) this.world).checkChunk((Entity) (Object) this);
    }
}