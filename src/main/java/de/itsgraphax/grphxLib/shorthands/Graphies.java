package de.itsgraphax.grphxLib.shorthands;

import java.util.List;
import java.util.Set;

/**
 * A set of misc utils
 */
public class Graphies {
    @SafeVarargs
    @Deprecated(forRemoval = true)
    public static <T> Set<T> setOf(T... o) {
        return Set.of(o);
    }
    @Deprecated(forRemoval = true)
    public static <T> Set<T> setOf() {
        return Set.of();
    }

    @SafeVarargs
    @Deprecated(forRemoval = true)
    public static <T> List<T> listOf(T... o) {
        return List.of(o);
    }
    @Deprecated(forRemoval = true)
    public static <T> List<T> listOf() {
        return List.of();
    }
}
