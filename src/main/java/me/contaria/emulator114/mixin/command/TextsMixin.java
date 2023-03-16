package me.contaria.emulator114.mixin.command;

import com.llamalad7.mixinextras.sugar.Local;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.text.Texts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Collection;

@Mixin(Texts.class)
public abstract class TextsMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-157426
    @MCBug("MC-157426")
    @ModifyArg(method = "joinOrdered(Ljava/util/Collection;Ljava/util/function/Function;)Lnet/minecraft/text/Text;", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Texts;join(Ljava/util/Collection;Ljava/util/function/Function;)Lnet/minecraft/text/Text;"))
    private static Collection<?> emulator114$breakOrderingOfCommandOutputs(Collection<?> list, @Local(argsOnly = true) Collection<?> elements) {
        return elements;
    }
}