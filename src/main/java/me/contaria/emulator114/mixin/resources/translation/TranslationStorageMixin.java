package me.contaria.emulator114.mixin.resources.translation;

import me.contaria.emulator114.plugin.annotations.CannotDisable;
import me.contaria.emulator114.plugin.annotations.MCBug;
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

    // Bugreport: https://bugs.mojang.com/browse/MC-164747
    @MCBug("MC-164747")
    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)V", at = @At("TAIL"))
    private void emulator114$uppercaseBy(CallbackInfo ci) {
        this.translations.replace("stat.minecraft.damage_blocked_by_shield", "Damage Blocked by Shield", "Damage Blocked By Shield");
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-166319
    @MCBug("MC-166319")
    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)V", at = @At("TAIL"))
    private void emulator114$lowercaseBrowser(CallbackInfo ci) {
        this.translations.replace("chat.link.open", "Open in Browser", "Open in browser");
    }

    // Reverts: "Parrots: Can no longer imitate endermen, polar bears, wolves, and zombie pigmen."
    // Bugreport: https://bugs.mojang.com/browse/MC-163400
    @MCBug("MC-163400")
    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)V", at = @At("TAIL"))
    private void emulator114$addImitatingSounds(CallbackInfo ci) {
        this.addImitatingSound("enderman");
        this.addImitatingSound("polar_bear");
        this.addImitatingSound("wolf");
        this.addImitatingSound("zombie_pigman");
    }

    @CannotDisable
    @Unique
    private void addImitatingSound(String entity) {
        try {
            this.translations.putIfAbsent("subtitles.entity.parrot.imitate." + entity, this.translations.get("subtitles.entity." + entity + ".ambient").replaceFirst(this.translations.get("entity.minecraft." + entity), this.translations.get("entity.minecraft.parrot")));
        } catch (NullPointerException ignored) {
            // catches in case any of the needed translations aren't present and return null
        }
    }
}