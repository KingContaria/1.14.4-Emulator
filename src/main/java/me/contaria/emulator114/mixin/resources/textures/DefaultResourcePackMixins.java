package me.contaria.emulator114.mixin.resources.textures;

import me.contaria.emulator114.Emulator114;
import net.minecraft.client.resource.DefaultClientResourcePack;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;

@Mixin({
        DefaultResourcePack.class,
        DefaultClientResourcePack.class
})
public abstract class DefaultResourcePackMixins {

    @Inject(method = "findInputStream", at = @At("HEAD"), cancellable = true)
    private void emulator114$load114Textures(ResourceType type, Identifier id, CallbackInfoReturnable<InputStream> cir) {
        Emulator114.EMULATOR114.findPath(type.getDirectory() + "/" + id.getNamespace() + "/" + id.getPath()).ifPresent(path -> {
            try {
                cir.setReturnValue(path.toUri().toURL().openStream());
            } catch (Exception ignored) {
                // in case of an error just fall back to vanilla logic
            }
        });
    }
}