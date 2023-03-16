package me.contaria.emulator114.mixin.command.data;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.command.StorageDataObject;
import net.minecraft.server.command.DataCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Mixin(DataCommand.class)
public abstract class DataCommandMixin {

    // Reverts: "Can now use storage as a source or target, which is general purpose, key/value storage. "
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"), remap = false)
    private static ImmutableList<Function<String, DataCommand.ObjectType>> emulator114$removeDataCommandArguments(ImmutableList<Function<String, DataCommand.ObjectType>> list) {
        List<Function<String, DataCommand.ObjectType>> editedList = new ArrayList<>(list);
        editedList.remove(StorageDataObject.TYPE_FACTORY);
        return ImmutableList.copyOf(editedList);
    }
}