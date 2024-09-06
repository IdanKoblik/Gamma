package com.github.idan.koblik;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@GameCollection(name = "Invalid")
record InvalidNameDummyGameModel(
    @JsonProperty("_id")
    UUID uuid,
    String name
) implements GameModel {
}

