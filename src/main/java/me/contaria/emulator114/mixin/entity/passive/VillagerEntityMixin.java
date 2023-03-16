package me.contaria.emulator114.mixin.entity.passive;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends AbstractTraderEntity {

    public VillagerEntityMixin(EntityType<? extends AbstractTraderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract VillagerData getVillagerData();

    // Bugreport: https://bugs.mojang.com/browse/MC-158585
    @MCBug("MC-158585")
    @Redirect(method = "initialize", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/SpawnType;DISPENSER:Lnet/minecraft/entity/SpawnType;"))
    private SpawnType emulator114$noVillagerBasedOnBiomeFromDispenser() {
        return SpawnType.COMMAND;
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-129806
    @MCBug("MC-129806")
    @Override
    public Text getDisplayName() {
        AbstractTeam abstractTeam = this.getScoreboardTeam();
        Text text = this.getCustomName();
        if (text != null) {
            return Team.modifyText(abstractTeam, text).styled((style) -> style.setHoverEvent(this.getHoverEvent()).setInsertion(this.getUuidAsString()));
        } else {
            VillagerProfession villagerProfession = this.getVillagerData().getProfession();
            Text text2 = (new TranslatableText(this.getType().getTranslationKey() + '.' + Registry.VILLAGER_PROFESSION.getId(villagerProfession).getPath())).styled((style) -> style.setHoverEvent(this.getHoverEvent()).setInsertion(this.getUuidAsString()));
            if (abstractTeam != null) {
                text2.formatted(abstractTeam.getColor());
            }

            return text2;
        }
    }
}