package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.Brittle;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({
        HorseBaseEntity.class,
        PandaEntity.class,
        PolarBearEntity.class,
        RabbitEntity.class
})
public abstract class NaturallySpawningAnimalEntitiesMixins extends AnimalEntity {

    protected NaturallySpawningAnimalEntitiesMixins(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    // This adds back baby variants spawning naturally for these specific entities
    // Bugreport: https://bugs.mojang.com/browse/MC-135098
    @MCBug("MC-135098")
    @Brittle
    @Inject(method = "initialize", at = @At("TAIL"))
    private void emulator114$spawnBabyVariants(CallbackInfoReturnable<PassiveEntity.EntityData> cir) {
        if (this.random.nextInt(Math.round(1 / cir.getReturnValue().getBabyChance())) == 0) {
            this.setBreedingAge(-24000);
        }
    }
}