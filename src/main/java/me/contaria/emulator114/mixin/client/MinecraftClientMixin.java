package me.contaria.emulator114.mixin.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    // Reverts: "Game window:
    //          Now displays the session type in the window title, such as singleplayer or multiplayer.
    //
    //          If applicable, the window title also specifies the type of multiplayer server the player is on, such as on a LAN or third-party server.
    //          If there is a * after Minecraft then it's modded. Example: "Minecraft* 1.15.2 - Singleplayer"
    //          Example: "Minecraft 1.15.2 - Multiplayer (LAN)".
    //          Available types: "Singleplayer", "Multiplayer (LAN)", "Multiplayer (Realms)", and "Multiplayer (3rd-party)". (Any of these can be with the * mentioned above)"

    /**
     * @author contaria
     * @reason change window title
     */
    @Overwrite
    private String getWindowTitle() {
        return "Minecraft 1.14.4... or is it?";
    }
}