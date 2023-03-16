package me.contaria.emulator114.mixin.resources.loot;

import com.google.gson.JsonObject;
import me.contaria.emulator114.ResourceEditorUtil;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LootManager.class)
public abstract class LootManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void emulator114$alterLootTables(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ResourceEditorUtil.Path path = new ResourceEditorUtil.Path("blocks");
        ResourceEditorUtil.MapEditor editor = new ResourceEditorUtil.MapEditor(map, path);
        editor.remove("honey_block");
        editor.remove("beehive");
        editor.remove("honeycomb_block");
        editor.remove("bee_nest");

        // remove drops
        map.get(path.get("attached_melon_stem")).remove("pools");
        map.get(path.get("attached_pumpkin_stem")).remove("pools");
        map.get(path.get("large_fern")).getAsJsonArray("pools").get(0).getAsJsonObject().getAsJsonArray("entries").get(0).getAsJsonObject().getAsJsonArray("children").remove(1);
    }
}