package me.contaria.emulator114.mixin.network;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-115643
    @MCBug("MC-115643")
    @Redirect(method = "updateBlockBreakingProgress", at = @At(value = "FIELD", target = "Lnet/minecraft/sound/SoundCategory;BLOCKS:Lnet/minecraft/sound/SoundCategory;"))
    private SoundCategory emulator114$blockBreakingSoundIsNeutral() {
        return SoundCategory.NEUTRAL;
    }
}