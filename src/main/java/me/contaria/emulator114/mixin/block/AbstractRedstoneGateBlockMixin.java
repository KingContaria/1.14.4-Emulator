package me.contaria.emulator114.mixin.block;

import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.world.TickPriority;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractRedstoneGateBlock.class)
public abstract class AbstractRedstoneGateBlockMixin {

    @Redirect(method = "scheduledTick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/TickPriority;VERY_HIGH:Lnet/minecraft/world/TickPriority;"))
    private TickPriority emulator114$reducedTickPriority() {
        return TickPriority.HIGH;
    }
}