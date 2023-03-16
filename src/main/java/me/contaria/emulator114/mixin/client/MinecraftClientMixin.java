package me.contaria.emulator114.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    /**
     * @author contaria
     * @reason change window title
     */
    @Overwrite
    private String getWindowTitle() {
        return "Minecraft 1.14.4... or is it?";
    }
}