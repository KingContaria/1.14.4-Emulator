package me.contaria.emulator114.mixin.client.options;

import com.google.common.base.Splitter;
import me.contaria.emulator114.plugin.annotations.MCBug;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.BufferedReader;
import java.nio.charset.Charset;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-104818
    @MCBug("MC-104818")
    @ModifyExpressionValue(method = "method_24230", at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;COLON_SPLITTER:Lcom/google/common/base/Splitter;", remap = true), remap = false)
    private static Splitter emulator114$skippingBadSettingLastServer(Splitter colonSplitter) {
        return colonSplitter.omitEmptyStrings().limit(2);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-117449
    @MCBug("MC-117449")
    @Redirect(method = "load", at = @At(value = "FIELD", target = "Lcom/google/common/base/Charsets;UTF_8:Ljava/nio/charset/Charset;", remap = false))
    private Charset emulator114$useDefaultCharsetOnLoad() {
        return Charset.defaultCharset();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-151173
    @MCBug("MC-151173")
    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Ljava/io/BufferedReader;close()V"))
    private void emulator114$doNotCloseInputStream(BufferedReader reader) {
    }
}