package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-3591
    @MCBug("MC-3591")
    @Redirect(method = "finishConversion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EquipmentSlot;values()[Lnet/minecraft/entity/EquipmentSlot;"))
    private EquipmentSlot[] emulator114$noDroppingItemsOnConversion() {
        return new EquipmentSlot[0];
    }
}