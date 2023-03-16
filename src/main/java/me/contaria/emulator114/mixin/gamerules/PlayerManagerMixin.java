package me.contaria.emulator114.mixin.gamerules;

import net.minecraft.server.PlayerManager;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Redirect(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z", ordinal = 0))
    private boolean emulator114$removeImmediateRespawnGameRule(GameRules instance, GameRules.RuleKey<GameRules.BooleanRule> rule) {
        return false;
    }
}