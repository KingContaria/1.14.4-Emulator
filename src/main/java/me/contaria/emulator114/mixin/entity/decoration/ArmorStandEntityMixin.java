package me.contaria.emulator114.mixin.entity.decoration;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorStandEntity.class)
public abstract class ArmorStandEntityMixin extends Entity {

    public ArmorStandEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "interactAt", at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;PASS:Lnet/minecraft/util/ActionResult;", ordinal = 0))
    private ActionResult emulator114$noUsingItemsWhenLookingAtArmorStand() {
        return ActionResult.SUCCESS;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-862
    @MCBug("MC-862")
    @Override
    public boolean handleAttack(Entity attacker) {
        return super.handleAttack(attacker);
    }
}