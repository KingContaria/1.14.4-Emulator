package me.contaria.emulator114.mixin.client.gui;

import net.minecraft.client.gui.widget.AbstractButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractButtonWidget.class)
public abstract class AbstractButtonWidgetMixin {

    @Shadow public abstract boolean isHovered();

    // Reverts: "Highlighted buttons now have white text on them instead of yellow."
    @ModifyConstant(method = "renderButton", constant = @Constant(intValue = 16777215))
    private int emulator114$yellowButtonText(int constant) {
        return this.isHovered() ? 16777120 : 14737632;
    }
}