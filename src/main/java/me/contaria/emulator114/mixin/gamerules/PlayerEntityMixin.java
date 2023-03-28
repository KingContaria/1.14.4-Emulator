package me.contaria.emulator114.mixin.gamerules;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    // Reverts: "drowningDamage: Whether the player should take damage when drowning.",
    //          "fallDamage: Whether the player should take fall damage.",
    //          "fireDamage: Whether the player should take fire damage."
    @Redirect(method = "isInvulnerableTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z"))
    private boolean emulator114$removeInvulnerabilityGameRules(GameRules rules, GameRules.RuleKey<GameRules.BooleanRule> rule) {
        return true;
    }
}