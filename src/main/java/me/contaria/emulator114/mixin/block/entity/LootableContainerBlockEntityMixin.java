package me.contaria.emulator114.mixin.block.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootableContainerBlockEntity.class)
public abstract class LootableContainerBlockEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-164342
    @MCBug("MC-164342")
    @SuppressWarnings("ConstantValue")
    @WrapWithCondition(method = {"isInvEmpty", "getInvStack", "takeInvStack", "removeInvStack", "setInvStack", "clear"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/LootableContainerBlockEntity;checkLootInteraction(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    private boolean doNotCheckLootInteractionOnBarrels(LootableContainerBlockEntity container, PlayerEntity player) {
        return !((Object) this instanceof BarrelBlockEntity);
    }
}
