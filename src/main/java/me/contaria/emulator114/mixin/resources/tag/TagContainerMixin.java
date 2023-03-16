package me.contaria.emulator114.mixin.resources.tag;

import me.contaria.emulator114.ResourceEditorUtil;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.tag.TagContainer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(TagContainer.class)
public abstract class TagContainerMixin {

    @ModifyExpressionValue(method = "method_18243", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;findResources(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Collection;", remap = true), remap = false)
    private Collection<Identifier> emulator114$removeTags(Collection<Identifier> tags) {
        ResourceEditorUtil.Path path = new ResourceEditorUtil.Path("tags", ".json");
        
        path.push("blocks");
        tags.remove(path.get("beehives"));
        tags.remove(path.get("bee_growables"));
        tags.remove(path.get("crops"));
        tags.remove(path.get("flowers"));
        tags.remove(path.get("portals"));
        tags.remove(path.get("shulker_boxes"));
        tags.remove(path.get("tall_flowers"));
        path.swap("entity_types");
        tags.remove(path.get("arrows"));
        tags.remove(path.get("beehive_inhabitors"));
        path.swap("items");
        tags.remove(path.get("flowers"));
        tags.remove(path.get("lectern_books"));
        tags.remove(path.get("tall_flowers"));

        return tags;
    }
}