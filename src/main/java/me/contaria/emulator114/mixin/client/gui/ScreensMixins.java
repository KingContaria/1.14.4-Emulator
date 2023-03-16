package me.contaria.emulator114.mixin.client.gui;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.gui.screen.PresetsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({
        CreateWorldScreen.class,
        GameOptionsScreen.class,
        PresetsScreen.class
})
public abstract class ScreensMixins extends Screen {

    protected ScreensMixins(Text title) {
        super(title);
    }

    // Bugreports: https://bugs.mojang.com/browse/MC-125104, https://bugs.mojang.com/browse/MC-94491
    @MCBug({"MC-125104", "MC-94491"})
    @Override
    public void onClose() {
        super.onClose();
    }
}