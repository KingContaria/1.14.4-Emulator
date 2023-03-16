package me.contaria.emulator114.mixin.entity.mob;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.AbstractEntityAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(SlimeEntity.class)
public abstract class SlimeEntityMixin extends MobEntity {

    protected SlimeEntityMixin(EntityType<? extends MobEntity> type, World world) {
        super(type, world);
    }

    @Shadow public abstract int getSize();

    // Reverts: "Now use the generic.attackDamage attribute."
    @Redirect(method = "initAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/AbstractEntityAttributeContainer;register(Lnet/minecraft/entity/attribute/EntityAttribute;)Lnet/minecraft/entity/attribute/EntityAttributeInstance;"))
    private EntityAttributeInstance emulator114$removeGenericAttackDamageAttribute(AbstractEntityAttributeContainer container, EntityAttribute attribute) {
        return null;
    }

    // see above
    @Redirect(method = "setSize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/attribute/EntityAttributes;ATTACK_DAMAGE:Lnet/minecraft/entity/attribute/EntityAttribute;")
    ))
    private void emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute, double v) {
    }

    // see above
    @Redirect(method = "getDamageAmount", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;getValue()D"))
    private double emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute) {
        return this.getSize();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-103313
    @MCBug("MC-103313")
    @Override
    public void calculateDimensions() {
        super.calculateDimensions();
    }
}