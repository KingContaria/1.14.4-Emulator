package me.contaria.emulator114.plugin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Used to annotate methods in Mixins that are not allowed to be disabled.
 */
@Target(ElementType.METHOD)
public @interface CannotDisable {

    String reason() default "Annotated by @CannotDisable.";
}
