package me.contaria.emulator114.mixin.village.raid;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.raid.Raid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;

@Mixin(Raid.class)
public abstract class RaidMixin {

    @Shadow @Final private ServerWorld world;

    // Bugreport: https://bugs.mojang.com/browse/MC-158373
    @MCBug("MC-158373")
    @Redirect(method = "playRaidHorn", at = @At(value = "INVOKE", target = "Ljava/util/Collection;contains(Ljava/lang/Object;)Z"))
    private boolean emulator114$playRaidHornInAllVillages(Collection<?> instance, Object player) {
        return !this.world.isNearOccupiedPointOfInterest(new BlockPos((ServerPlayerEntity) player));
    }
}