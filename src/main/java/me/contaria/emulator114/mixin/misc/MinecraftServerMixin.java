package me.contaria.emulator114.mixin.misc;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    // Bugreports: https://bugs.mojang.com/browse/MC-161132, https://bugs.mojang.com/browse/MC-158911
    @MCBug({"MC-161132", "MC-158911"})
    @Redirect(method = {"loadWorldDataPacks", "reload"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;method_24154()V"))
    private void emulator114$noInitializingBlockStateCache(MinecraftServer server) {
    }
}