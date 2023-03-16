package me.contaria.emulator114.mixin.resources.tag;

import me.contaria.emulator114.ResourceEditorUtil;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.tag.TagContainer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(TagContainer.class)
public abstract class TagContainerMixin {

    // Reverts: "Added minecraft:beehives, minecraft:bee_growables, minecraft:crops, minecraft:flowers, minecraft:shulker_boxes, and minecraft:tall_flowers block tags.
    //          Added [...] minecraft:beehive_inhabitors entity tags.
    //          Added minecraft:flowers, minecraft:lectern_books, and minecraft:tall_flowers item tag.
    //          Added new portals block tag."
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
        tags.remove(path.get("beehive_inhabitors"));
        path.swap("items");
        tags.remove(path.get("flowers"));
        tags.remove(path.get("lectern_books"));
        tags.remove(path.get("tall_flowers"));

        return tags;
    }

    // Reverts: "Added minecraft:arrows [...] entity tags."
    // has to be seperated from emulator114$removeTags because MC-152683 depends on this tag
    @MCBug("MC-152683")
    @ModifyExpressionValue(method = "method_18243", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;findResources(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Collection;", remap = true), remap = false)
    private Collection<Identifier> emulator114$removeArrowsTag(Collection<Identifier> tags) {
        ResourceEditorUtil.Path path = new ResourceEditorUtil.Path("tags", ".json");

        path.push("entity_types");
        tags.remove(path.get("arrows"));

        return tags;
    }
}