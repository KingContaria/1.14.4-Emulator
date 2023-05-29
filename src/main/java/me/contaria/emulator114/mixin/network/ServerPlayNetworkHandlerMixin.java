package me.contaria.emulator114.mixin.network;

import me.contaria.emulator114.interfaces.IItemEntity;
import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-86846
    @MCBug("MC-86846")
    @Redirect(method = "onUpdateCommandBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/CommandBlockBlockEntity;method_23359()V"))
    private void emulator114$noUpdatingToRepeating(CommandBlockBlockEntity entity) {
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-106494
    @MCBug("MC-106494")
    @ModifyExpressionValue(method = "onCreativeInventoryAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;dropItem(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/ItemEntity;"))
    private ItemEntity emulator114$setCreativeDespawnTime(ItemEntity item) {
        ((IItemEntity) item).emulator114$setCreativeDespawnTime();
        return item;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-153698
    @MCBug("MC-153698")
    @ModifyConstant(method = "onPlayerMove", constant = @Constant(doubleValue = 0.0, ordinal = 0), slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;method_20630(Lnet/minecraft/world/WorldView;)Z")
    ))
    private double emulator114$accumulateFallDamageWhenJumpingOnBoat(double zero) {
        return Double.MAX_VALUE;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-132211
    @MCBug("MC-132211")
    @Redirect(method = "onBookUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkThreadUtils;forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/server/world/ServerWorld;)V"))
    private void emulator114$offThreadBookUpdates(Packet<?> packet, PacketListener listener, ServerWorld world) {
    }
}