package com.example.game.util;

import com.example.game.model.Copyable;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtility {

    private CollectionUtility() {
        // this is private to block instantiation
    }

    public static <T extends Copyable<T>> List<T> cloneList(List<T> list) {
        return list
                .stream()
                .map(e -> e.copyOf(e))
                .collect(Collectors.toList());
    }
}
