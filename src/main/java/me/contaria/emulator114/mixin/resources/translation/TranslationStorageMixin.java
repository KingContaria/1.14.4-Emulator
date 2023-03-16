package me.contaria.emulator114.mixin.resources.translation;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(TranslationStorage.class)
public abstract class TranslationStorageMixin {

    @Shadow @Final protected Map<String, String> translations;

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)V", at = @At("TAIL"))
    private void emulator114$addImitatingSounds(CallbackInfo ci) {
        this.addImitatingSound("enderman");
        this.addImitatingSound("polar_bear");
        this.addImitatingSound("wolf");
        this.addImitatingSound("zombie_pigman");

        // Bugreport: https://bugs.mojang.com/browse/MC-164747
        this.translations.replace("stat.minecraft.damage_blocked_by_shield", "Damage Blocked by Shield", "Damage Blocked By Shield");

        // Bugreport: https://bugs.mojang.com/browse/MC-166319
        this.translations.replace("chat.link.open", "Open in Browser", "Open in browser");
    }

    @Unique
    private void addImitatingSound(String entity) {
        try {
            this.translations.putIfAbsent("subtitles.entity.parrot.imitate." + entity, this.translations.get("subtitles.entity." + entity + ".ambient").replaceFirst(this.translations.get("entity.minecraft." + entity), this.translations.get("entity.minecraft.parrot")));
        } catch (NullPointerException ignored) {
            // catches in case any of the needed translations aren't present and return null
        }
    }
}