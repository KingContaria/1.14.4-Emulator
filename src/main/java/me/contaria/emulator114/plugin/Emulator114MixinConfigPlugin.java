package me.contaria.emulator114.plugin;

import me.contaria.emulator114.Emulator114;
import me.contaria.emulator114.plugin.annotations.Brittle;
import me.contaria.emulator114.plugin.annotations.CannotDisable;
import me.contaria.emulator114.plugin.annotations.MCBug;
import me.contaria.emulator114.plugin.exceptions.CannotDisableException;
import me.contaria.emulator114.plugin.modifymixin.IModifyMixin;
import me.contaria.emulator114.plugin.modifymixin.ModifyMixinBootstrap;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.util.Annotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Emulator114MixinConfigPlugin implements IMixinConfigPlugin, IModifyMixin {

    private static final Set<String> DISBABLED_BUG_UNFIXES = new HashSet<>();
    private static final Set<String> DISABLED_MIXIN_METHODS = new HashSet<>();

    static {
        DISBABLED_BUG_UNFIXES.addAll(readConfigFile("disabled-bugunfixes.txt"));
        DISABLED_MIXIN_METHODS.addAll(readConfigFile("disabled-mixinmethods.txt"));

        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            try {
                CustomValue customValue = mod.getMetadata().getCustomValues().get("emulator114");
                if (customValue != null) {
                    if (mod != Emulator114.EMULATOR114) {
                        Emulator114.LOGGER.info("'{}' is disabling some of {}'s mixins.", mod, Emulator114.NAME);
                    }
                    CustomValue disabledBugUnfixes = customValue.getAsObject().get("disabled-bugunfixes");
                    if (disabledBugUnfixes != null) {
                        for (CustomValue disabledBugUnfix : disabledBugUnfixes.getAsArray()) {
                            DISBABLED_BUG_UNFIXES.add(disabledBugUnfix.getAsString());
                        }
                    }
                    CustomValue disabledMixinMethods = customValue.getAsObject().get("disabled-mixinmethods");
                    if (disabledMixinMethods != null) {
                        for (CustomValue disabledMixinMethod : disabledMixinMethods.getAsArray()) {
                            DISABLED_MIXIN_METHODS.add(disabledMixinMethod.getAsString());
                        }
                    }
                }
            } catch (ClassCastException e) {
                Emulator114.LOGGER.error("'{}' passed a wrongly formatted custom value for disabling {}'s mixins.", mod.getMetadata().getName(), Emulator114.NAME, e);
            }
        }

        if (!DISBABLED_BUG_UNFIXES.isEmpty()) {
            Emulator114.LOGGER.info("Please note that not all of {}'s Bug-Unfixes are properly annotated yet, so some bugs, while unfixed, might not be able to be fixed through the config.", Emulator114.NAME);
        }
    }

    private static List<String> readConfigFile(String fileName) {
        Path path = Emulator114.CONFIG.resolve(fileName);
        if (Files.exists(path)) {
            try {
                return Files.readAllLines(path);
            } catch (IOException e) {
                Emulator114.LOGGER.error("Failed to read {}", path, e);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void modifyMixin(ClassNode mixinClass, IMixinInfo mixinInfo) {
        String classNameFromMixinPackageRoot = mixinClass.name.replaceFirst(mixinInfo.getConfig().getMixinPackage(), "");

        try {
            Set<MethodNode> mixinsToRemove = new HashSet<>();

            checking:
            for (MethodNode method : mixinClass.methods) {
                String methodNameFromMixinPackageRoot = classNameFromMixinPackageRoot + "." + method.name;

                AnnotationNode mcBug = Annotations.getInvisible(method, MCBug.class);
                if (mcBug != null) {
                    for (String bug : (Collection<String>) Annotations.getValue(mcBug)) {
                        if (DISBABLED_BUG_UNFIXES.contains(bug)) {
                            removeMixinMethod(mixinsToRemove, method, methodNameFromMixinPackageRoot);
                            Emulator114.LOGGER.info("Disabled '{}' mixin because it fixes {}.", method.name + method.desc, bug);
                            continue checking;
                        }
                    }
                }

                if (DISABLED_MIXIN_METHODS.contains(methodNameFromMixinPackageRoot) || DISABLED_MIXIN_METHODS.contains(methodNameFromMixinPackageRoot + method.desc)) {
                    removeMixinMethod(mixinsToRemove, method, methodNameFromMixinPackageRoot);
                    Emulator114.LOGGER.info("Disabled '{}' mixin.", method.name + method.desc);
                }
            }
            if (mixinClass.methods.removeAll(mixinsToRemove)) {
                Emulator114.LOGGER.info("Successfully transformed '{}'!", classNameFromMixinPackageRoot);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to modify '" + classNameFromMixinPackageRoot + "' via Mixin Config Plugin!", e);
        }
    }

    private static void removeMixinMethod(Set<MethodNode> mixinsToRemove, MethodNode method, String methodNameFromMixinPackageRoot) throws CannotDisableException {
        AnnotationNode cannotDisable = Annotations.getInvisible(method, CannotDisable.class);
        if (cannotDisable != null) {
            throw new CannotDisableException(methodNameFromMixinPackageRoot, cannotDisable);
        }
        AnnotationNode shadow = Annotations.getInvisible(method, Shadow.class);
        if (shadow != null) {
            throw new CannotDisableException(methodNameFromMixinPackageRoot, "Annotated by @Shadow.");
        }
        if (method.name.equals("<init>")) {
            throw new CannotDisableException(methodNameFromMixinPackageRoot, "Cannot disable constructors.");
        }
        if (Annotations.getInvisible(method, Brittle.class) != null) {
            Emulator114.LOGGER.warn("'{}' is marked as brittle, please be sure you know what side effects you could be causing when disabling it!", methodNameFromMixinPackageRoot);
        }
        mixinsToRemove.add(method);
    }
}
