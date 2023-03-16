package me.contaria.emulator114.plugin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Used for annotating Mixin methods that unfix Minecraft Bugs that were fixed by Mojang between 1.14.4 and 1.15.2.
 * Bug-Unfixes can be disabled through the mixin config plugin.
 */
@Target(ElementType.METHOD)
public @interface MCBug {

    String[] value();
}
