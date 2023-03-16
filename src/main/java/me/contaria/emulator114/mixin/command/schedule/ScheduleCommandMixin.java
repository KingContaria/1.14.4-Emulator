package me.contaria.emulator114.mixin.command.schedule;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.command.ScheduleCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ScheduleCommand.class)
public abstract class ScheduleCommandMixin {

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/RequiredArgumentBuilder;then(Lcom/mojang/brigadier/builder/ArgumentBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", remap = false), slice = @Slice(
            to = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/RequiredArgumentBuilder;then(Lcom/mojang/brigadier/builder/ArgumentBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", ordinal = 2, remap = false)
    ))
    private static ArgumentBuilder<ServerCommandSource, ?> emulator114$removeScheduleCommandArguments(RequiredArgumentBuilder<ServerCommandSource, ?> builder, ArgumentBuilder<ServerCommandSource, ?> predicateArgumentBuilder) {
        return builder;
    }

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;then(Lcom/mojang/brigadier/builder/ArgumentBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", remap = false), slice = @Slice(
            from = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;then(Lcom/mojang/brigadier/builder/ArgumentBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;", ordinal = 2, remap = false)
    ))
    private static ArgumentBuilder<ServerCommandSource, ?> emulator114$removeScheduleCommandArguments(LiteralArgumentBuilder<ServerCommandSource> builder, ArgumentBuilder<ServerCommandSource, ?> predicateArgumentBuilder) {
        return builder;
    }
}