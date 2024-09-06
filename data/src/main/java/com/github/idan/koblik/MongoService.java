package com.github.idan.koblik;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * A service class for performing CRUD operations on MongoDB collections.
 * Provides both synchronous and asynchronous methods for interacting with MongoDB.
 *
 * @param <T> The type of the game model that this service handles.
 * @see GameModel
 */
public class MongoService<T extends GameModel> {
    private final MongoDatabase database;
    private final Class<T> type;

    /**
     * Constructs a MongoService instance.
     *
     * @param database The MongoDB database instance.
     * @param type     The class type of the game model.
     */
    public MongoService(MongoDatabase database, Class<T> type) {
        this.database = database;
        this.type = type;
    }

    private String getCollectionName() throws UnsupportedGameModel {
        GameCollection annotation = type.getAnnotation(GameCollection.class);
        if (annotation == null)
            throw new UnsupportedGameModel("GameModel record missing GameCollection annotation");
        else if (CollectionNameHelper.getInstance().isSupported(annotation.name()))
            return annotation.name();
        else
            throw new UnsupportedGameModel("GameModel name unsupported");

    }

    private @NotNull MongoCollection<Document> getCollection() {
        try {
            return database.getCollection(getCollectionName());
        } catch (UnsupportedGameModel e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Asynchronously inserts a document into the MongoDB collection.
     *
     * @param data The game model to be inserted.
     * @return A CompletableFuture that completes when the insertion is done.
     */
    public CompletableFuture<Void> insert(T data) {
        return CompletableFuture.runAsync(() -> insertSync(data));
    }

    /**
     * Asynchronously updates a document in the MongoDB collection by its ID.
     *
     * @param id   The ID of the document to be updated.
     * @param data The new game model data.
     * @return A CompletableFuture that completes when the update is done.
     */
    public CompletableFuture<Void> update(UUID id, T data) {
        return CompletableFuture.runAsync(() -> updateSync(id, data));
    }

    /**
     * Asynchronously removes a document from the MongoDB collection by its ID.
     *
     * @param id The ID of the document to be removed.
     * @return A CompletableFuture that completes when the removal is done.
     */
    public CompletableFuture<Void> remove(UUID id) {
        return CompletableFuture.runAsync(() -> removeSync(id));
    }

    /**
     * Asynchronously retrieves a document from the MongoDB collection by its ID.
     *
     * @param id    The ID of the document to retrieve.
     * @param clazz The class type of the game model.
     * @return A CompletableFuture that completes with the retrieved game model, or null if not found.
     */
    public CompletableFuture<T> get(UUID id, Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> getSync(id, clazz));
    }

    /**
     * Asynchronously increments a field in a document in the MongoDB collection.
     *
     * @param id        The ID of the document to update.
     * @param fieldName The name of the field to increment.
     * @param amount    The amount by which to increment the field.
     * @return A CompletableFuture that completes when the update is done.
     */
    public CompletableFuture<Void> incrementField(UUID id, String fieldName, long amount) {
        return CompletableFuture.runAsync(() -> incrementFieldSync(id, fieldName, amount));
    }

    /**
     * Asynchronously adds an item to a collection field in a document in the MongoDB collection.
     *
     * @param id       The ID of the document to update.
     * @param collectionName The name of the collection field to update.
     * @param item     The item to add to the collection.
     * @return A CompletableFuture that completes when the update is done.
     */
    public CompletableFuture<Void> addToCollection(UUID id, String collectionName, Object item) {
        return CompletableFuture.runAsync(() -> addToCollectionSync(id, collectionName, item));
    }

    /**
     * Asynchronously removes an item from a collection field in a document in the MongoDB collection.
     *
     * @param id       The ID of the document to update.
     * @param collectionName The name of the collection field to update.
     * @param item     The item to remove from the collection.
     * @return A CompletableFuture that completes when the update is done.
     */
    public CompletableFuture<Void> removeFromCollection(UUID id, String collectionName, Object item) {
        return CompletableFuture.runAsync(() -> removeFromCollectionSync(id, collectionName, item));
    }

    /**
     * Synchronously inserts a document into the MongoDB collection.
     *
     * @param data The game model to be inserted.
     */
    public void insertSync(@NotNull T data) {
        MongoCollection<Document> collection = getCollection();
        Document doc = Document.parse(data.toString());
        collection.insertOne(doc);
    }

    /**
     * Synchronously updates a document in the MongoDB collection by its ID.
     *
     * @param id   The ID of the document to be updated.
     * @param data The new game model data.
     */
    public void updateSync(@NotNull UUID id, @NotNull T data) {
        MongoCollection<Document> collection = getCollection();
        Document doc = Document.parse(data.toString());
        collection.replaceOne(Filters.eq("id", id.toString()), doc);
    }

    /**
     * Synchronously removes a document from the MongoDB collection by its ID.
     *
     * @param id The ID of the document to be removed.
     */
    public void removeSync(@NotNull UUID id) {
        MongoCollection<Document> collection = getCollection();
        collection.deleteOne(Filters.eq("id", id.toString()));
    }

    /**
     * Synchronously retrieves a document from the MongoDB collection by its ID.
     *
     * @param id    The ID of the document to retrieve.
     * @param clazz The class type of the game model.
     * @return The retrieved game model, or null if not found.
     */
    public T getSync(@NotNull UUID id, Class<T> clazz) {
        MongoCollection<Document> collection = getCollection();
        Document doc = collection.find(Filters.eq("id", id.toString())).first();
        if (doc != null) {
            try {
                return fromDocument(doc, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Synchronously increments a field in a document in the MongoDB collection.
     *
     * @param id        The ID of the document to update.
     * @param fieldName The name of the field to increment.
     * @param amount    The amount by which to increment the field.
     */
    public void incrementFieldSync(@NotNull UUID id, String fieldName, long amount) {
        MongoCollection<Document> collection = getCollection();
        collection.updateOne(Filters.eq("id", id.toString()), Updates.inc(fieldName, amount));
    }

    /**
     * Synchronously adds an item to a collection field in a document in the MongoDB collection.
     *
     * @param id       The ID of the document to update.
     * @param collectionName The name of the collection field to update.
     * @param item     The item to add to the collection.
     */
    public void addToCollectionSync(@NotNull UUID id, String collectionName, Object item) {
        MongoCollection<Document> collection = getCollection();
        collection.updateOne(Filters.eq("id", id.toString()), Updates.push(collectionName, item));
    }

    /**
     * Synchronously removes an item from a collection field in a document in the MongoDB collection.
     *
     * @param id       The ID of the document to update.
     * @param collectionName The name of the collection field to update.
     * @param item     The item to remove from the collection.
     */
    public void removeFromCollectionSync(@NotNull UUID id, String collectionName, Object item) {
        MongoCollection<Document> collection = getCollection();
        collection.updateOne(Filters.eq("id", id.toString()), Updates.pull(collectionName, item));
    }

    private @NotNull T fromDocument(@NotNull Document doc, @NotNull Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getConstructor(String.class);
        return constructor.newInstance(doc.toJson());
    }
}
