package me.contaria.emulator114.mixin.item.fuel;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @ModifyConstant(method = "createFuelTimeMap", constant = @Constant(intValue = 1200))
    private static int emulator114$shorterBoatBurnTime(int i) {
        return 200;
    }

    @ModifyConstant(method = "createFuelTimeMap", constant = @Constant(intValue = 400))
    private static int emulator114$shorterScaffoldingBurnTime(int i) {
        return 50;
    }
}