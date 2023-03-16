package me.contaria.emulator114.mixin.client.options;

import net.minecraft.client.options.StickyKeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.BooleanSupplier;

@Mixin(StickyKeyBinding.class)
public abstract class StickyKeyBindingMixin {

    @Redirect(method = "setPressed", at = @At(value = "INVOKE", target = "Ljava/util/function/BooleanSupplier;getAsBoolean()Z"))
    private boolean emulator114$noToggleKeys(BooleanSupplier toggleGetter) {
        return false;
    }
}