package me.contaria.emulator114.mixin.resources.tag;

import me.contaria.emulator114.statics.MoreBlockTags;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockTags.class)
public abstract class BlockTagsMixin {

    @Shadow
    private static Tag<Block> register(String id) {
        return null;
    }

    // TODO: add the dirt_like.json
    // Reverts: "Removed dirt_like block tag."
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void emulator114$registerDirtLikeTag(CallbackInfo ci) {
        MoreBlockTags.DIRT_LIKE = register("dirt_like");
    }
}