# Game Data API Documentation

## Overview

The `MongoService` class provides a service for CRUD operations on MongoDB collections. It supports both asynchronous and synchronous methods, making it versatile for various use cases. The service is designed to handle game data models that extend the `GameModel` interface.

### Game Data Model

The game data model is defined using the `DummyGameModel` record:

```java
@GameCollection(name = CollectionNameHelper.GAME)
record DummyGameModel(
        @JsonProperty("_id")
        UUID uuid,
        String name
) implements GameModel {
}
```

### MongoService Class

#### Constructor

```java
public MongoService(@NotNull MongoDatabase database, @NotNull Class<T> type)
```

**Parameters:**
- `database`: The MongoDB database instance.
- `type`: The class type of the game model.

#### Methods

The `MongoService` class supports both asynchronous and synchronous operations for interacting with MongoDB collections. This includes:

- **Insert:** Add a new document to the collection.
- **Update:** Modify an existing document by its ID.
- **Remove:** Delete a document by its ID.
- **Get:** Retrieve a document by its ID.
- **Increment Field:** Increment a specific field's value within a document.
- **Add to Collection:** Add an item to a collection field within a document.
- **Remove from Collection:** Remove an item from a collection field within a document.

#### Exception Handling

- **UnsupportedGameModelException:** Thrown if the game model is not annotated correctly or if the collection name is unsupported.

## Notes

- The `MongoService` class uses Jackson's `ObjectMapper` for serializing and deserializing documents.
- Methods can be used either asynchronously or synchronously depending on the requirement.