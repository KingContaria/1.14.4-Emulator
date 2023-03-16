package me.contaria.emulator114;

import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Map;

public final class ResourceEditorUtil {

    public static Identifier identifier(final String string) {
        return new Identifier(minecraft(string));
    }

    public static String minecraft(final String string) {
        return "minecraft:" + string;
    }

    public static class Path {
        private static final String DELIMITER = "/";
        private String directory;
        private final String endsIn;

        public Path() {
            this.directory = "";
            this.endsIn = "";
        }

        public Path(final String initialDirectory) {
            this(initialDirectory, "");
        }

        public Path(final String initialDirectory, final String endsIn) {
            this.directory = initialDirectory + DELIMITER;
            this.endsIn = endsIn;
        }

        public void push(final String string) {
            this.directory += string + DELIMITER;
        }

        public void swap(final String string) {
            final String[] path = this.directory.split(DELIMITER);
            path[path.length - 1] = string;
            this.directory = String.join(DELIMITER, path) + DELIMITER;
        }

        public void pop() {
            final String[] path = this.directory.split(DELIMITER);
            this.directory = String.join(DELIMITER, Arrays.copyOf(path, path.length - 1)) + DELIMITER;
        }

        public Identifier get(final String string) {
            return identifier(this.directory + string + this.endsIn);
        }

        @SuppressWarnings("unused")
        public String directory() {
            return this.directory;
        }
    }

    public static class MapEditor {

        private final Map<Identifier, ?> map;
        private final Path path;

        public MapEditor(final Map<Identifier, ?> map, final Path path) {
            this.map = map;
            this.path = path;
        }

        public void remove(final Identifier identifier) {
            this.map.remove(identifier);
        }

        public void remove(final String string) {
            this.remove(this.path.get(string));
        }
    }
}