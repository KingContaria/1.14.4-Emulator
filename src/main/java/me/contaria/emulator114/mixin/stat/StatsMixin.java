package me.contaria.emulator114.mixin.stat;

import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Stats.class)
public abstract class StatsMixin {

    // Reverts: "Added statistics for anvil and grindstone interaction counts."
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/stat/Stats;register(Ljava/lang/String;Lnet/minecraft/stat/StatFormatter;)Lnet/minecraft/util/Identifier;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=interact_with_anvil")
    ))
    private static Identifier emulator114$removeStats(String id, StatFormatter formatter) {
        return new Identifier(id);
    }
}