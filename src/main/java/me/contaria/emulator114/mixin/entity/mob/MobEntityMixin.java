package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-2958
    @MCBug("MC-2958")
    @Redirect(method = "dropEquipment", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float emulator114$dropUndroppableEquipmentWithHighLooting(float chance, float zero) {
        return chance;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-114715
    @MCBug("MC-114715")
    @Redirect(method = "loot", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float emulator114$dropUndroppableEquipmentWhenPickingUpItems(float chance, float zero) {
        return chance;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-14826
    @MCBug("MC-14826")
    @Redirect(method = "writeCustomDataToTag", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/MobEntity;leashTag:Lnet/minecraft/nbt/CompoundTag;", ordinal = 0))
    private CompoundTag emulator114$unloadLeadsIncorrectly(MobEntity entity) {
        return null;
    }
}