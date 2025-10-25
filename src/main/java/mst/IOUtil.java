package mst;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class IOUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Map<String, Object>> readInputFile(String path) throws IOException {
        try (Reader r = new FileReader(path)) {
            JsonObject root = gson.fromJson(r, JsonObject.class);
            JsonArray graphs = root.getAsJsonArray("graphs");
            Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
            return gson.fromJson(graphs, listType);
        }
    }

    public static Graph graphFromMap(Map<String,Object> map){
        Graph g = new Graph();
        @SuppressWarnings("unchecked")
        List<String> nodes = (List<String>) map.get("nodes");
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> edges = (List<Map<String,Object>>) map.get("edges");
        for(String v : nodes) g.addVertex(v);
        for(Map<String,Object> em : edges){
            String from = (String) em.get("from");
            String to = (String) em.get("to");
            int w = ((Number) em.get("weight")).intValue();
            g.addEdge(from, to, w);
        }
        return g;
    }

    public static void writeOutput(String path, List<Map<String,Object>> results) throws IOException {
        Map<String,Object> root = new HashMap<>();
        root.put("results", results);
        try (Writer w = new FileWriter(path)) {
            gson.toJson(root, w);
        }
    }
}
