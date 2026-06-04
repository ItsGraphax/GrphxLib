package de.itsgraphax.grphxLib.shorthands;

import java.util.List;
import java.util.Set;

/**
 * A set of misc utils
 */
public class Graphies {
    @SafeVarargs
    public static <T> Set<T> setOf(T... o) {
        return Set.of(o);
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... o) {
        return List.of(o);
    }
}
