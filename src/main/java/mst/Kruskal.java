package mst;

import java.util.*;
import java.util.stream.Collectors;

public class Kruskal {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public long totalCost = 0;
        public long operations = 0;
        public double timeMs = 0;
    }

    public static Result run(Graph g){
        Result res = new Result();
        long start = System.nanoTime();
        List<Edge> edges = new ArrayList<>(g.getEdges());
        Collections.sort(edges);
        // count sort as operations roughly n log n comparisons
        res.operations += (long)(edges.size() * Math.log(Math.max(2, edges.size())));
        UnionFind uf = new UnionFind();
        for(String v : g.vertices()) { uf.makeSet(v); res.operations++; }
        for(Edge e : edges){
            res.operations++; // comparison for cycle check
            String ra = uf.find(e.from);
            String rb = uf.find(e.to);
            res.operations += 2;
            if (!ra.equals(rb)){
                boolean united = uf.union(e.from, e.to);
                res.operations++;
                if (united) {
                    res.mstEdges.add(e);
                    res.totalCost += e.weight;
                }
            }
            if (res.mstEdges.size() == g.vertexCount() - 1) break;
        }
        long end = System.nanoTime();
        res.timeMs = (end - start) / 1_000_000.0;
        return res;
    }
}
