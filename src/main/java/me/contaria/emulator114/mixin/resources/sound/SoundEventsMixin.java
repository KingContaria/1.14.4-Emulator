package me.contaria.emulator114.mixin.resources.sound;

import me.contaria.emulator114.statics.MoreSoundEvents;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEvents.class)
public abstract class SoundEventsMixin {

    @Shadow
    private static SoundEvent register(String id) {
        return null;
    }

    // Reverts: "Parrots: Can no longer imitate endermen, polar bears, wolves, and zombie pigmen."
    // Bugreport: https://bugs.mojang.com/browse/MC-163400
    @MCBug("MC-163400")
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void emulator114$registerMoreParrotSounds(CallbackInfo ci) {
        String imitate = "entity.parrot.imitate.";

        MoreSoundEvents.ENTITY_PARROT_IMITATE_ENDERMAN = register(imitate + "enderman");
        MoreSoundEvents.ENTITY_PARROT_IMITATE_POLAR_BEAR = register(imitate + "polar_bear");
        MoreSoundEvents.ENTITY_PARROT_IMITATE_WOLF = register(imitate + "wolf");
        MoreSoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN = register(imitate + "zombie_pigman");
    }
}