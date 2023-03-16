package me.contaria.emulator114.mixin.command.kill;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.KillCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(KillCommand.class)
public abstract class KillCommandMixin {

    // Reverts: "/kill: The first parameter now defaults to @s if no target argument is given."
    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;executes(Lcom/mojang/brigadier/Command;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", remap = false))
    private static ArgumentBuilder<?, ?> emulator114$noDefault(LiteralArgumentBuilder<?> builder, Command<?> command) {
        return builder;
    }
}