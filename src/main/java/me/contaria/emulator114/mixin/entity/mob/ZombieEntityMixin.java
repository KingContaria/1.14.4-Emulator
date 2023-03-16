package me.contaria.emulator114.mixin.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin {

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/SpawnEggItem;isOfSameEntityType(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/entity/EntityType;)Z"))
    private boolean emulator114$noSpawningChildren(SpawnEggItem instance, CompoundTag tag, EntityType<?> type) {
        return false;
    }
}