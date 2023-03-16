package me.contaria.emulator114.mixin.command.gamerules;

import net.minecraft.world.GameRules;
import net.minecraft.world.gen.PillagerSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PillagerSpawner.class)
public abstract class PillagerSpawnerMixin {

    @Redirect(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z"))
    private boolean emulator114$removeDoPatrolSpawningGameRule(GameRules instance, GameRules.RuleKey<GameRules.BooleanRule> rule) {
        return true;
    }
}