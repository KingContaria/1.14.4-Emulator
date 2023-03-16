package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin extends Screen {

    @Shadow @Final private boolean isHardcore;

    protected DeathScreenMixin(Text title) {
        super(title);
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-30646
    @MCBug("MC-30646")
    @Redirect(method = "method_19808", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DeathScreen;quitLevel()V", remap = true), remap = false)
    private void emulator114$openTitleScreen(DeathScreen screen) {
        Objects.requireNonNull(this.minecraft).openScreen(new TitleScreen());
    }

    // see above
    @MCBug("MC-30646")
    @ModifyConstant(method = "init", constant = @Constant(stringValue = "deathScreen.titleScreen"))
    private String emulator114$deleteWorldString(String constant) {
        if (this.isHardcore) {
            return "deathScreen." + (Objects.requireNonNull(this.minecraft).isInSingleplayer() ? "deleteWorld" : "leaveServer");
        }
        return constant;
    }
}