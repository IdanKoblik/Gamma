package com.github.idan.koblik;

import java.util.List;

public class CollectionNameHelper {

    private static CollectionNameHelper instance;

    public static CollectionNameHelper getInstance() {
        return instance == null ? (instance = new CollectionNameHelper()) : instance;
    }

    public static final String
                GAME = "game",
                GAME_STATS = "game-stats";

    private final List<String> allowedCollections = List.of(
            GAME,
            GAME_STATS
    );

    public boolean isSupported(String collectionName) {
        return this.allowedCollections.stream().anyMatch(collectionName::equals);
    }
}
