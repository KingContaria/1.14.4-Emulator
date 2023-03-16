package me.contaria.emulator114.plugin.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CannotDisable {

    String reason() default "Annotated by @CannotDisable.";
}
