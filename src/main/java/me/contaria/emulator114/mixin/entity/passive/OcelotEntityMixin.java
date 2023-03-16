package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.attribute.AbstractEntityAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.passive.OcelotEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(OcelotEntity.class)
public abstract class OcelotEntityMixin {

    // Reverts: "Now use the generic.attackDamage attribute."
    // Bugreport: https://bugs.mojang.com/browse/MC-47091
    @MCBug("MC-47091")
    @Redirect(method = "initAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/AbstractEntityAttributeContainer;register(Lnet/minecraft/entity/attribute/EntityAttribute;)Lnet/minecraft/entity/attribute/EntityAttributeInstance;"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/attribute/EntityAttributes;ATTACK_DAMAGE:Lnet/minecraft/entity/attribute/EntityAttribute;")
    ))
    private EntityAttributeInstance emulator114$removeGenericAttackDamageAttribute(AbstractEntityAttributeContainer container, EntityAttribute attribute) {
        return null;
    }

    // see above
    @MCBug("MC-47091")
    @Redirect(method = "initAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/attribute/EntityAttributes;ATTACK_DAMAGE:Lnet/minecraft/entity/attribute/EntityAttribute;")
    ))
    private void emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute, double v) {
    }

    // see above
    @MCBug("MC-47091")
    @Redirect(method = "method_22329", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;getValue()D"))
    private double emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute) {
        return 3.0;
    }
}