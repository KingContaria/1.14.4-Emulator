package me.contaria.emulator114.mixin.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LandPathNodeMaker.class)
public abstract class LandPathNodeMakerMixin {

    // Reverts: "Mobs are now better at avoiding walking through lava."
    @Redirect(method = "method_59", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;LAVA:Lnet/minecraft/block/Block;"))
    private static Block emulator114$badAtAvoidingLava() {
        return Blocks.FIRE;
    }
}