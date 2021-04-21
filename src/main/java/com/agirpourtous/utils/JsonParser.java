package com.agirpourtous.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    /**
     * The unique instance of this singleton class
     */
    private static JsonParser jsonParser;
    /**
     * Instance of the JSON parser
     */
    private final Gson gson;

    private JsonParser() {
        this.gson = new GsonBuilder().create();
    }

    /**
     * Creates an instance if it is not instantiated and returns the instance
     *
     * @return The unique instance of this object
     */
    public static JsonParser getGameFileParser() {
        if (jsonParser == null) {
            jsonParser = new JsonParser();
        }
        return jsonParser;
    }

    /**
     * Parse as an instance the given JSON
     *
     * @param data      The json to be parsed as an object
     * @param classType The type of the object to be parsed
     * @param <T>       The type to be returned
     * @return An instance of the given JSON as the given type
     */
    public <T> T parseData(String data, Class<T> classType) {
        return gson.fromJson(data, classType);
    }

    /**
     * Parse a given object as a JSON
     *
     * @param object The object to be parsed
     * @return The JSON data of the given object as a String
     */
    public String dataToJson(Object object) {
        return gson.toJson(object);
    }
}