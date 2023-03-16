package me.contaria.emulator114.plugin.modifymixin;

import com.llamalad7.mixinextras.utils.MixinInternals;

public class ModifyMixinBootstrap {

    private static boolean initialized;

    public static void init() {
        if (!initialized) {
            initialized = true;
            MixinInternals.registerExtension(new ModifyMixinExtension());
        }
    }
}
