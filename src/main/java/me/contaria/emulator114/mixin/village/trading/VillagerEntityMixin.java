package me.contaria.emulator114.mixin.village.trading;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-157136
    @MCBug("MC-157136")
    @Redirect(method = "shouldRestock", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/passive/VillagerEntity;lastRestockTime:J", opcode = Opcodes.PUTFIELD))
    private void emulator114$infiniteRestocks(VillagerEntity instance, long value) {
    }

    // this is the shit that makes discount extending possible
    @Redirect(method = "needRestock", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOffer;method_21834()Z"))
    private boolean emulator114$onlyRestockWhenOutOfStock(TradeOffer tradeOffer) {
        return tradeOffer.isDisabled();
    }
}