package me.contaria.emulator114.mixin.client.options;

import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Option.class)
public abstract class OptionMixin {

    // Reverts: "The biome blend setting has moved and now has descriptions per setting value."
    @ModifyArg(method = "method_18590", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resource/language/I18n;translate(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", remap = true), index = 0, remap = false)
    private static String emulator114$noBiomeBlendDescription(String key, Object[] args) {
        if (key.equals("1")) {
            return "options.off";
        }
        return key + "x" + key;
    }

    // see above
    @ModifyConstant(method = "method_18590", constant = @Constant(stringValue = "options.biomeBlendRadius."), remap = false)
    private static String emulator114$noBiomeBlendDescription(String constant) {
        return "";
    }
}