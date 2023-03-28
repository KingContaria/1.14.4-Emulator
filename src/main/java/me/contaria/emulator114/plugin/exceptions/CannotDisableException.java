package me.contaria.emulator114.plugin.exceptions;

import me.contaria.emulator114.plugin.annotations.CannotDisable;
import org.objectweb.asm.tree.AnnotationNode;
import org.spongepowered.asm.util.Annotations;

public class CannotDisableException extends Exception {

    public CannotDisableException(String method, CannotDisable cannotDisable) {
        this(method, cannotDisable.reason());
    }

    public CannotDisableException(String method, AnnotationNode cannotDisable) {
        this(method, Annotations.getValue(cannotDisable, "reason", "Annotated by @CannotDisable."));
    }

    public CannotDisableException(String method, String reason) {
        super("'" + method + "' can not be disabled: " + reason);
    }
}
