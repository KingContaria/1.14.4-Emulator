package me.contaria.emulator114.mixin.resources.sound;

import net.minecraft.client.sound.SoundEntry;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {

    @ModifyVariable(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/client/sound/SoundManager$SoundList;", at = @At("STORE"))
    private Map<String, SoundEntry> emulator114$addParrotImitatingSounds(Map<String, SoundEntry> map) {
        addImitatingSound(map, "enderman");
        addImitatingSound(map, "polar_bear");
        addImitatingSound(map, "wolf");
        addImitatingSound(map, "zombie_pigman");

        return map;
    }

    @Unique
    private static void addImitatingSound(Map<String, SoundEntry> map, String entity) {
        SoundEntry entry = map.get(String.format("entity.%s.ambient", entity));
        map.putIfAbsent("entity.parrot.imitate." + entity, new SoundEntry(entry.getSounds(), entry.canReplace(), "subtitles.entity.parrot.imitate." + entity));
    }
}