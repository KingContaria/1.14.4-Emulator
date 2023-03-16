package me.contaria.emulator114.mixin.resources.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.contaria.emulator114.ResourceEditorUtil;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.contaria.emulator114.ResourceEditorUtil.minecraft;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void emulator114$alterAdvancementsAndRecipes(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ResourceEditorUtil.Path path = new ResourceEditorUtil.Path("recipes");
        ResourceEditorUtil.MapEditor editor = new ResourceEditorUtil.MapEditor(map, path);

        // remove recipes
        path.push("decorations");
        editor.remove("beehive");
        editor.remove("honey_block");
        editor.remove("honeycomb_block");
        path.swap("food");
        editor.remove("honey_bottle");
        path.swap("misc");
        editor.remove("sugar_from_honey_bottle");
        path.swap("building_blocks");
        editor.remove(stripped("acacia"));
        editor.remove(stripped("birch"));
        editor.remove(stripped("dark_oak"));
        editor.remove(stripped("jungle"));
        editor.remove(stripped("oak"));
        editor.remove(stripped("spruce"));

        // edit recipes
        editMinCount(map, path.get("bone_block"), "bonemeal");
        editMinCount_block(map, path, "coal");
        editMinCount_block(map, path, "diamond");
        editMinCount_block(map, path, "emerald");
        editMinCount(map, path.get("gold_block"), "gold_ingot");
        editMinCount(map, path.get("iron_block"), "iron_ingot");
        editMinCount(map, path.get("packed_ice"), "ice");
        editMinCount(map, path.get("blue_ice"), "packed_ice");
        editMinCount_block(map, path, "lapis");
        editMinCount_block(map, path, "dried_kelp");
        editMinCount(map, path.get("hay_block"), "wheat");
        path.swap("decorations");
        editMinCount(map, path.get("slime_block"), "slime_ball");
        path.swap("misc");
        editMinCount(map, path.get("iron_ingot_from_nuggets"), "iron_nugget");
        editMinCount(map, path.get("gold_ingot_from_nuggets"), "gold_nugget");
        path.swap("redstone");
        editMinCount_block(map, path, "redstone");

        path.swap("misc");
        addMinCount(map, path.get("bone_meal_from_bone_block"), "bone_meal");
        addMinCount(map, path, "coal");
        addMinCount(map, path, "diamond");
        addMinCount(map, path, "emerald");
        addMinCount(map, path.get("gold_ingot_from_gold_block"), "gold_ingot");
        addMinCount(map, path.get("iron_ingot_from_iron_block"), "iron_ingot");
        addMinCount(map, path, "gold_nugget");
        addMinCount(map, path, "iron_nugget");
        addMinCount(map, path, "lapis_lazuli");
        addMinCount(map, path, "slime_ball");
        addMinCount(map, path, "wheat");
        path.swap("food");
        addMinCount(map, path, "dried_kelp");
        path.swap("redstone");
        addMinCount(map, path, "redstone");

        // edit composter criterion
        path.swap("misc");
        map.get(path.get("composter")).getAsJsonObject("criteria").getAsJsonObject("has_wood_slab").getAsJsonObject("conditions").getAsJsonArray("items").get(0).getAsJsonObject().add("tag", new JsonPrimitive(minecraft("wooden_fences")));

        // remove advancements
        path.pop();
        path.swap("adventure");
        editor.remove("honey_block_slide");
        path.swap("husbandry");
        editor.remove("safely_harvest_honey");
        editor.remove("silk_touch_nest");

        // edit advancements
        removeRequirement(map, path.get("bred_all_animals"), minecraft("bee"));
        removeRequirement(map, path.get("balanced_diet"), "honey_bottle");

        // edit shoot_arrow arrow type (exclude spectral arrows)
        path.swap("adventure");
        map.get(path.get("shoot_arrow")).getAsJsonObject("criteria").getAsJsonObject("shot_arrow").getAsJsonObject("conditions").getAsJsonObject("damage").getAsJsonObject("type").getAsJsonObject("direct_entity").add("type", new JsonPrimitive(minecraft("arrow")));
    }

    @Unique
    private static void removeRequirement(final Map<Identifier, JsonObject> map, final Identifier advancement, final String requirement) {
        final JsonObject advancementObject = map.get(advancement);
        advancementObject.getAsJsonObject("criteria").remove(requirement);
        final JsonArray advancementRequirements = advancementObject.getAsJsonArray("requirements");
        final List<JsonElement> remove = new ArrayList<>();
        advancementRequirements.forEach(jsonElement -> jsonElement.getAsJsonArray().forEach((jsonElement1 -> {
            if (jsonElement1.isJsonPrimitive() && jsonElement1.getAsJsonPrimitive().getAsString().equals(requirement)) {
                remove.add(jsonElement);
            }
        })));
        remove.forEach(advancementRequirements::remove);
    }

    @Unique
    private static void editMinCount_block(final Map<Identifier, JsonObject> map, final ResourceEditorUtil.Path path, final String name) {
        editMinCount(map, path.get(name + "_block"), name);
    }

    @Unique
    private static void editMinCount(final Map<Identifier, JsonObject> map, final Identifier identifier, final String required) {
        final JsonObject minCount = new9MinCount();
        map.get(identifier).getAsJsonObject("criteria").getAsJsonObject("has_" + required).getAsJsonObject("conditions").getAsJsonArray("items").get(0).getAsJsonObject().add("count", minCount);
    }

    @Unique
    private static void addMinCount(final Map<Identifier, JsonObject> map, final ResourceEditorUtil.Path path, final String name) {
        addMinCount(map, path.get(name), name);
    }

    @Unique
    private static void addMinCount(final Map<Identifier, JsonObject> map, final Identifier identifier, final String required) {
        final JsonObject object = new JsonObject();
        object.add("trigger", new JsonPrimitive(minecraft("inventory_changed")));
        final JsonObject conditions = new JsonObject();
        final JsonArray items = new JsonArray();
        final JsonObject item = new JsonObject();
        final JsonObject count = new9MinCount();
        item.add("item", new JsonPrimitive(minecraft(required)));
        items.add(item);
        items.add(count);
        conditions.add("items", items);
        object.add("conditions", conditions);

        final JsonObject recipe = map.get(identifier);

        final String fullName = "has_at_least_9_" + required;
        recipe.getAsJsonObject("criteria").add(fullName, object);
        recipe.getAsJsonArray("requirements").get(0).getAsJsonArray().add(fullName);
    }

    @Unique
    private static JsonObject new9MinCount() {
        final JsonObject count = new JsonObject();
        count.add("min", new JsonPrimitive(9));
        return count;
    }

    @Unique
    private static String stripped(String wood) {
        return "stripped_" + wood + "_wood";
    }
}