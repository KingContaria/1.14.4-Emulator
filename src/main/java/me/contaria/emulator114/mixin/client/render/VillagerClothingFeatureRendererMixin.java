package me.contaria.emulator114.mixin.client.render;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.client.render.entity.feature.VillagerClothingFeatureRenderer;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerClothingFeatureRenderer.class)
public abstract class VillagerClothingFeatureRendererMixin {

    // Reverts: "Nitwit villagers no longer have a leveling gemstone in their belt."
    // Bugreport: https://bugs.mojang.com/browse/MC-154280
    @MCBug("MC-154280")
    @Redirect(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/village/VillagerProfession;NITWIT:Lnet/minecraft/village/VillagerProfession;"))
    private VillagerProfession emulator114$renderBadgeOnNitwits() {
        return VillagerProfession.NONE;
    }
}