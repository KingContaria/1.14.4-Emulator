package me.contaria.emulator114.mixin.command.execute;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ExecuteCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ExecuteCommand.class)
public abstract class ExecuteCommandMixin {

    // Reverts: "Added subcommand /execute if predicate <predicate>"
    @Redirect(method = "addConditionArguments", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;then(Lcom/mojang/brigadier/builder/ArgumentBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", remap = false), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=predicate", ordinal = 1),
            to = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")
    ))
    private static ArgumentBuilder<ServerCommandSource, ?> emulator114$removeExecuteCommandArguments(LiteralArgumentBuilder<ServerCommandSource> builder, ArgumentBuilder<ServerCommandSource, ?> predicateArgumentBuilder) {
        return builder;
    }
}