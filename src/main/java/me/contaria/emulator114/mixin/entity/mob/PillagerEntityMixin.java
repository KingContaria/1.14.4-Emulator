package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PillagerEntity.class)
public abstract class PillagerEntityMixin extends IllagerEntity {

    @Shadow @Final private BasicInventory inventory;

    protected PillagerEntityMixin(EntityType<? extends IllagerEntity> type, World world) {
        super(type, world);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-160367
    @MCBug("MC-160367")
    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() && this.inventory.isInvEmpty();
    }

    // see above
    @MCBug("MC-160367")
    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return super.canImmediatelyDespawn(distanceSquared) && this.inventory.isInvEmpty();
    }
}