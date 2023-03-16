package me.contaria.emulator114.mixin.entity.passive;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PassiveEntity.class)
public abstract class PassiveEntityMixin extends MobEntityWithAi {

    protected PassiveEntityMixin(EntityType<? extends MobEntityWithAi> type, World world) {
        super(type, world);
    }

    // Reverts: "When breedable mobs in groups spawn naturally they will now sometimes spawn babies in the groups."
    @Override
    public EntityData initialize(IWorld world, LocalDifficulty difficulty, SpawnType spawnType, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
        return super.initialize(world, difficulty, spawnType, entityData, entityTag);
    }
}