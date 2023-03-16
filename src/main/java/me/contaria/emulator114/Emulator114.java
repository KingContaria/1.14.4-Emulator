package me.contaria.emulator114;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Objects;

public final class Emulator114 implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ModContainer EMULATOR114 = Objects.requireNonNull(FabricLoader.getInstance().getModContainer("emulator114").orElse(null));
    public static final String NAME = EMULATOR114.getMetadata().getName();
    public static final Version VERSION = EMULATOR114.getMetadata().getVersion();
    public static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve("emulator114");

    @Override
    public void onInitialize() {
        LOGGER.info("Emulating Minecraft 1.14.4... ({} v{})", NAME, VERSION.getFriendlyString());
    }
}