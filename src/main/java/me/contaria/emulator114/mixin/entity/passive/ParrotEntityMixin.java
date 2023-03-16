package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.MoreSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin {

    @Inject(method = "method_6579", at = @At("TAIL"), remap = false)
    private static void emulator114$extraParrotImitateSounds(HashMap<EntityType<?>, SoundEvent> map, CallbackInfo ci) {
        map.put(EntityType.ENDERMAN, MoreSoundEvents.ENTITY_PARROT_IMITATE_ENDERMAN);
        map.put(EntityType.POLAR_BEAR, MoreSoundEvents.ENTITY_PARROT_IMITATE_POLAR_BEAR);
        map.put(EntityType.WOLF, MoreSoundEvents.ENTITY_PARROT_IMITATE_WOLF);
        map.put(EntityType.ZOMBIE_PIGMAN, MoreSoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN);
    }
}