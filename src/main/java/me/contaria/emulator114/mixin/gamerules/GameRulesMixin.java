package me.contaria.emulator114.mixin.gamerules;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GameRules.class)
public abstract class GameRulesMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;register(Ljava/lang/String;Lnet/minecraft/world/GameRules$RuleType;)Lnet/minecraft/world/GameRules$RuleKey;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=doInsomnia")
    ))
    private static GameRules.RuleKey<?> emulator114$removeGameRules(String name, GameRules.RuleType<?> type) {
        return new GameRules.RuleKey<>(name);
    }
}