package me.contaria.emulator114.mixin.enchantment;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.PumpkinBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$5")
public abstract class EnchantmentTarget_WearableMixin {

    // Bugreport: https://bugs.mojang.com/browse/MC-125810
    @MCBug("MC-125810")
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyConstant(method = "isAcceptableItem", constant = @Constant(classValue = CarvedPumpkinBlock.class))
    private boolean emulator114$noFireballsLightingCampfires(Object block, Class<?> c) {
        return block instanceof PumpkinBlock;
    }
}
