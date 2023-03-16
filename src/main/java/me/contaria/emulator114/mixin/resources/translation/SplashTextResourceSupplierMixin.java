package me.contaria.emulator114.mixin.resources.translation;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SplashTextResourceSupplier.class)
public abstract class SplashTextResourceSupplierMixin {

    // Reverts: "Added 3 new splash texts"
    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void emulator_removeSplashes(List<String> splashes, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        splashes.remove("In case it isn't obvious, foxes aren't players.");
        splashes.remove("Buzzy Bees!");
        splashes.remove("Minecraft Java Edition presents: Disgusting Bugs");
    }
}