package me.contaria.emulator114.mixin.client.debug;

import me.contaria.emulator114.statics.ChunkRendererInfo;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-158870
    @MCBug("MC-158870")
    @SuppressWarnings("MixinAnnotationTarget")
    @ModifyConstant(method = "handleProfilerKeyPress", constant = @Constant(intValue = '\u001e'))
    private int emulator114$breakPieChart(int value) {
        return '.';
    }

    // Reverts: "The biome blend setting is now shown in the debug overlay."
    // Reverts: "Debug screen: The 'chunk updates' counter on the second line no longer appears."
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"), index = 0)
    private String emulator114$modifyDebugScreenString(String format) {
        return "%d fps (%d chunk update%s) T: %s%s%s%s%s";
    }

    // see above
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"), index = 1)
    private Object[] emulator114$modifyDebugScreenStringArgs(Object[] args) {
        args[args.length - 1] = "";

        List<Object> list = new ArrayList<>(Arrays.asList(args));
        list.addAll(1, Arrays.asList(ChunkRendererInfo.chunkUpdateCount, ChunkRendererInfo.chunkUpdateCount == 1 ? "" : "s"));

        ChunkRendererInfo.chunkUpdateCount = 0;

        return list.toArray();
    }
}