package mst;

import java.util.*;

public class Prim {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public long totalCost = 0;
        public long operations = 0;
        public double timeMs = 0;
    }

    public static Result run(Graph g){
        Result res = new Result();
        long start = System.nanoTime();
        if (g.vertexCount() == 0) {
            res.timeMs = 0;
            return res;
        }
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        String startV = g.vertices().iterator().next();
        visited.add(startV);
        for (Edge e : g.adj(startV)) { pq.add(e); res.operations++; }
        while(!pq.isEmpty() && res.mstEdges.size() < g.vertexCount()-1){
            Edge e = pq.poll();
            res.operations++;
            if (visited.contains(e.to)) continue;
            visited.add(e.to);
            res.mstEdges.add(e);
            res.totalCost += e.weight;
            for (Edge ne : g.adj(e.to)){
                if (!visited.contains(ne.to)) { pq.add(ne); res.operations++; }
            }
        }
        long end = System.nanoTime();
        res.timeMs = (end - start) / 1_000_000.0;
        return res;
    }
}
