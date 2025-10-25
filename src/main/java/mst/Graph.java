package mst;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adj = new HashMap<>();
    private final Set<Edge> edges = new HashSet<>();

    public Graph() {}

    public void addVertex(String v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String a, String b, int w) {
        addVertex(a);
        addVertex(b);
        Edge e = new Edge(a, b, w);
        Edge e2 = new Edge(b, a, w);
        adj.get(a).add(e);
        adj.get(b).add(e2);
        // store single representative (ensure no duplicates by weight+endpoints)
        edges.add(new Edge(a,b,w));
    }

    public Set<String> vertices() {
        return Collections.unmodifiableSet(adj.keySet());
    }

    public Collection<Edge> getEdges() {
        return Collections.unmodifiableCollection(edges);
    }

    public List<Edge> adj(String v) {
        return adj.getOrDefault(v, Collections.emptyList());
    }

    public int vertexCount() {
        return adj.size();
    }

    public int edgeCount() {
        return edges.size();
    }
}
