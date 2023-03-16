package me.contaria.emulator114.mixin.gamerules;

import net.minecraft.world.GameRules;
import net.minecraft.world.gen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawnerMixin {

    @Redirect(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z"))
    private boolean emulator114$removeDoInsomniaGameRule(GameRules instance, GameRules.RuleKey<GameRules.BooleanRule> rule) {
        return true;
    }
}