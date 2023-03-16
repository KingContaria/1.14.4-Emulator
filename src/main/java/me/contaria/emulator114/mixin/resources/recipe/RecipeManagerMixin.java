package me.contaria.emulator114.mixin.resources.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.contaria.emulator114.ResourceEditorUtil;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static me.contaria.emulator114.ResourceEditorUtil.minecraft;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void emulator114$alterRecipes(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ResourceEditorUtil.Path path = new ResourceEditorUtil.Path();
        ResourceEditorUtil.MapEditor editor = new ResourceEditorUtil.MapEditor(map, path);

        // remove recipes
        editor.remove("beehive");
        editor.remove("honey_block");
        editor.remove("honey_bottle");
        editor.remove("honeycomb_block");
        editor.remove("sugar_from_honey_bottle");
        editor.remove(stripped("acacia"));
        editor.remove(stripped("birch"));
        editor.remove(stripped("dark_oak"));
        editor.remove(stripped("jungle"));
        editor.remove(stripped("oak"));
        editor.remove(stripped("spruce"));

        // edit composter recipe
        JsonObject composterRecipe = map.get(path.get("composter"));
        composterRecipe.getAsJsonArray("pattern").set(2, new JsonPrimitive("FFF"));
        JsonObject keys = composterRecipe.getAsJsonObject("key");
        keys.getAsJsonObject("#").add("tag", new JsonPrimitive(minecraft("wooden_fences")));
        JsonObject object = new JsonObject();
        object.add("tag", new JsonPrimitive(minecraft("planks")));
        keys.add("F", object);

        // edit dark prismarine recipe
        map.get(path.get("dark_prismarine")).getAsJsonObject("key").getAsJsonObject("I").add("item", new JsonPrimitive(minecraft("ink_sac")));
    }

    @Unique
    private static String stripped(String wood) {
        return "stripped_" + wood + "_wood";
    }
}