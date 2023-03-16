package me.contaria.emulator114.mixin.network;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {

    @Shadow public ServerPlayerEntity player;
    @Shadow private int failedStartMiningTime;

    @ModifyVariable(method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;postMine(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;)V", shift = At.Shift.AFTER), ordinal = 1)
    private ItemStack emulator114$noSilkTouchOrShearsOnLastUse(ItemStack itemStack) {
        return this.player.getMainHandStack().isEmpty() ? ItemStack.EMPTY : itemStack;
    }

    @Redirect(method = "processBlockBreakingAction", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;mining:Z", opcode = Opcodes.GETFIELD))
    private boolean emulator114$noSendingInstantMineFail(ServerPlayerInteractionManager manager) {
        return false;
    }

    @Redirect(method = "processBlockBreakingAction", at = @At(value = "INVOKE", target = "Ljava/util/Objects;equals(Ljava/lang/Object;Ljava/lang/Object;)Z"))
    private boolean emulator114$noDetectingMismatch(Object a, Object b) {
        return true;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-160177
    @MCBug("MC-160177")
    @ModifyVariable(method = "continueMining", at = @At("HEAD"), argsOnly = true)
    private int emulator114$noSendingBlockBreakingAnimationToOthers(int i) {
        return this.failedStartMiningTime;
    }
}