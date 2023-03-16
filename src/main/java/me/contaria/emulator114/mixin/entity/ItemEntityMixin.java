package me.contaria.emulator114.mixin.entity;

import me.contaria.emulator114.interfaces.IItemEntity;
import me.contaria.emulator114.plugin.annotations.CannotDisable;
import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin implements IItemEntity {

    @Shadow private int age;

    // Bugreport: https://bugs.mojang.com/browse/MC-125495
    @MCBug("MC-125495")
    @ModifyExpressionValue(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Ljava/util/UUID;equals(Ljava/lang/Object;)Z"))
    private boolean emulator114$canPickUpOldItemsWithOwner(boolean original) {
        return original || 6000 - this.age <= 200;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-125511
    @MCBug("MC-125511")
    @Redirect(method = "tryMerge(Lnet/minecraft/entity/ItemEntity;)V", at = @At(value = "INVOKE", target = "Ljava/util/Objects;equals(Ljava/lang/Object;Ljava/lang/Object;)Z"))
    private boolean emulator114$canMergeWithDifferentOwners(Object a, Object b) {
        return true;
    }

    @CannotDisable
    @Override
    public void emulator114$setCreativeDespawnTime() {
        this.age = 4800;
    }
}