package me.contaria.emulator114.mixin.client.debug;

import me.contaria.emulator114.statics.ChunkRendererInfo;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(targets = "net/minecraft/client/render/chunk/ChunkBuilder$BuiltChunk$RebuildTask")
public abstract class ChunkBuilderBuiltChunkRebuildTaskMixin {

    // Reverts: "Debug screen: The 'chunk updates' counter on the second line no longer appears."
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockModelRenderer;enableBrightnessCache()V"))
    private void emulator114$increaseChunkUpdateCount(CallbackInfoReturnable<Set<BlockEntity>> cir) {
        ChunkRendererInfo.chunkUpdateCount++;
    }
}