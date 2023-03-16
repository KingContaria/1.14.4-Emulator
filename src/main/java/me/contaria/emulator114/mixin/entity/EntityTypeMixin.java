package me.contaria.emulator114.mixin.entity;

import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin {

    // Reverts: "Mobs: Bees"
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;register(Ljava/lang/String;Lnet/minecraft/entity/EntityType$Builder;)Lnet/minecraft/entity/EntityType;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=bee"),
            to = @At(value = "CONSTANT", args = "stringValue=blaze")
    ))
    private static EntityType<?> emulator114$removeBees(String id, EntityType.Builder<?> type) {
        return type.build(id);
    }
}