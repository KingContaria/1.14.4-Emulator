package me.contaria.emulator114.mixin.world.gen.decorator;

import net.minecraft.world.gen.decorator.BeehiveTreeDecorator;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveTreeDecorator.class)
public abstract class BeehiveTreeDecoratorMixin {

    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/world/gen/decorator/BeehiveTreeDecorator;chance:F", opcode = Opcodes.GETFIELD))
    private float emulator114$noBeehives(BeehiveTreeDecorator decorator) {
        return 0.0f;
    }
}