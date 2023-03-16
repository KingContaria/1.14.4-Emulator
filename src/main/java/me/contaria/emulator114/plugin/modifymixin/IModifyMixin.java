package me.contaria.emulator114.plugin.modifymixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public interface IModifyMixin {

    void modifyMixin(ClassNode mixinClass, IMixinInfo mixinInfo);
}
