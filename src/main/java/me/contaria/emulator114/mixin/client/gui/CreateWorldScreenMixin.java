package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-158978
    @MCBug("MC-158978")
    @Redirect(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/ButtonWidget;active:Z", opcode = Opcodes.PUTFIELD))
    private void emulator114$createWorldsWithNoName(ButtonWidget button, boolean bl) {
    }

    // Bugreports: https://bugs.mojang.com/browse/MC-3984, https://bugs.mojang.com/browse/MC-10209
    @MCBug({"MC-3984", "MC-10209"})
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;tweakDefaultsTo(Lnet/minecraft/client/gui/screen/world/CreateWorldScreen$Mode;)V"))
    private void emulator114$haveToCycleDifficultyOnRecreatedHardcoreWorlds(CreateWorldScreen screen, @Coerce Object mode) {
    }
}