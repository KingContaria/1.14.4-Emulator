package me.contaria.emulator114.plugin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Used to annotate mixins who when disabled could cause unforeseen issues.
 */
@Target(ElementType.METHOD)
public @interface Brittle {
}
