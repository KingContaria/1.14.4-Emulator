package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LilyPadBlock.class)
public abstract class LilyPadBlockMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-96207
    @MCBug("MC-96207")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "onEntityCollision", constant = @Constant(classValue = ServerWorld.class))
    private boolean emulator114$tooManyParticles(Object o, Class<?> c) {
        return true;
    }
}