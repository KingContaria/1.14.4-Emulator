package me.contaria.emulator114.mixin.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.AbstractEntityAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemEntityMixin extends GolemEntity {

    protected IronGolemEntityMixin(EntityType<? extends GolemEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean interactMob(PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    // Reverts: "Now use the generic.attackDamage attribute."
    @Redirect(method = "initAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/AbstractEntityAttributeContainer;register(Lnet/minecraft/entity/attribute/EntityAttribute;)Lnet/minecraft/entity/attribute/EntityAttributeInstance;"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/attribute/EntityAttributes;ATTACK_DAMAGE:Lnet/minecraft/entity/attribute/EntityAttribute;")
    ))
    private EntityAttributeInstance emulator114$removeGenericAttackDamageAttribute(AbstractEntityAttributeContainer container, EntityAttribute attribute) {
        return null;
    }

    // see above
    @Redirect(method = "initAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"), slice = @Slice(
            from = @At(value = "FIELD", target = "Lnet/minecraft/entity/attribute/EntityAttributes;ATTACK_DAMAGE:Lnet/minecraft/entity/attribute/EntityAttribute;")
    ))
    private void emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute, double v) {
    }

    // see above
    @Redirect(method = "getAttackDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;getValue()D"))
    private double emulator114$removeGenericAttackDamageAttribute(EntityAttributeInstance attribute) {
        return 14.0;
    }

    // this was changed as a side effect of using the generic attack damage attribute
    @ModifyArg(method = "tryAttack", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    private int emulator114$increaseRandomExtraDamage(int bound_14) {
        return bound_14 + 1;
    }
}