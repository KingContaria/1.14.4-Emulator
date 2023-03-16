package me.contaria.emulator114.mixin.block;

import me.contaria.emulator114.plugin.annotations.MCBug;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    // Reverts: "End stone bricks: Now have a hardness of 3. Now have a blast resistance of 9."
    // Bugreport: https://bugs.mojang.com/browse/MC-94013
    @MCBug("MC-94013")
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block$Settings;strength(FF)Lnet/minecraft/block/Block$Settings;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=end_stone_bricks"),
            to = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE_BRICKS:Lnet/minecraft/block/Block;")
    ))
    private static Block.Settings emulator114$oldEndStoneBricksStrength(Block.Settings endStoneBrick, float hardness, float resistance) {
        return endStoneBrick.strength(0.8f, 0.8f);
    }

    // Reverts block additions
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;"), slice = @Slice(
            from = @At(value = "CONSTANT", args = "stringValue=bee_nest")
    ))
    private static Block emulator114$removeBlocks(String id, Block block) {
        return new AirBlock(Block.Settings.of(Material.AIR));
    }
}