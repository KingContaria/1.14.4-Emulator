package me.contaria.emulator114.mixin.item.fuel;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    // Reverts: "When used as fuel, one boat now smelts 6 items in a furnace, blast furnace, or smoker instead of 1."
    @ModifyArg(method = "createFuelTimeMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;addFuel(Ljava/util/Map;Lnet/minecraft/tag/Tag;I)V", ordinal = 0), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/tag/ItemTags;BOATS:Lnet/minecraft/tag/Tag;")
    ), index = 2)
    private static int emulator114$shorterBoatBurnTime(int fuelTime) {
        return 200;
    }

    // Reverts: "Increased scaffolding burn time when used as fuel in a furnace to be able to smelt 2 items, rather than 0.25."
    @ModifyArg(method = "createFuelTimeMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;addFuel(Ljava/util/Map;Lnet/minecraft/item/ItemConvertible;I)V", ordinal = 0), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;SCAFFOLDING:Lnet/minecraft/block/Block;")
    ), index = 2)
    private static int emulator114$shorterScaffoldingBurnTime(int fuelTime) {
        return 50;
    }
}