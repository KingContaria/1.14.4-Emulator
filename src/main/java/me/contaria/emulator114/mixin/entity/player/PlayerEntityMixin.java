package me.contaria.emulator114.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class  PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Shadow @Final public PlayerAbilities abilities;

    // Reverts: "Parrots: Can now sit on a player's shoulder even when the player is riding an entity."
    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isTouchingWater()Z"))
    private boolean emulator114$dropParrotsWhenOnVehicle(boolean isTouchingWater) {
        return isTouchingWater || this.hasVehicle();
    }

    // Reverts: "All foods are now edible in creative mode, including cake."
    @ModifyReturnValue(method = "canConsume", at = @At("RETURN"))
    private boolean emulator114$noEatingInCreative(boolean bl) {
        return bl && !this.abilities.invulnerable;
    }

    // Reverts: "Trying to sleep during the daytime will now set the player's spawn location to that bed."
    @Redirect(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setPlayerSpawn(Lnet/minecraft/util/math/BlockPos;ZZ)V"))
    private void emulator114$noSettingSpawnAtDayTime(PlayerEntity player, BlockPos blockPos, boolean bl, boolean bl2) {
    }

    // Reverts: "Setting the respawn point by using a bed now shows the message 'Respawn point set' in chat."
    @Redirect(method = "setPlayerSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;sendMessage(Lnet/minecraft/text/Text;)V"))
    private void emulator114$noSpawnSetMessage(PlayerEntity player, Text text) {
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-167235
    @MCBug("MC-167235")
    @ModifyConstant(method = "method_24278", constant = @Constant(doubleValue = 0.5))
    private double emulator114$offCenterSleepingDistance(double constant) {
        return 0.0;
    }
}