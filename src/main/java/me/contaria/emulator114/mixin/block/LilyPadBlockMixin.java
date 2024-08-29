package me.contaria.emulator114.mixin.block;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LilyPadBlock.class)
public abstract class LilyPadBlockMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-96207
    @MCBug("MC-96207")
    @Definition(id = "ServerWorld", type = ServerWorld.class)
    @Expression("? instanceof ServerWorld")
    @Redirect(method = "onEntityCollision", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean emulator114$tooManyParticles(Object o, Class<?> c) {
        return true;
    }
}