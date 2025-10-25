package mst;

import java.io.File;
import java.util.*;
import java.nio.file.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = "input.json";
        String outputPath = "output.json";
        if (args.length >= 1) inputPath = args[0];
        if (args.length >= 2) outputPath = args[1];

        List<Map<String,Object>> graphs = IOUtil.readInputFile(inputPath);
        List<Map<String,Object>> results = new ArrayList<>();

        for(Map<String,Object> gmap : graphs){
            Number idnum = (Number) gmap.get("id");
            int gid = idnum.intValue();
            Graph g = IOUtil.graphFromMap(gmap);
            Map<String,Object> result = new LinkedHashMap<>();
            result.put("graph_id", gid);
            Map<String,Object> inputStats = new LinkedHashMap<>();
            inputStats.put("vertices", g.vertexCount());
            inputStats.put("edges", g.edgeCount());
            result.put("input_stats", inputStats);

            Kruskal.Result kr = Kruskal.run(g);
            Map<String,Object> kmap = new LinkedHashMap<>();
            kmap.put("mst_edges", kr.mstEdges.stream().map(e -> edgeMap(e)).collect(Collectors.toList()));
            kmap.put("total_cost", kr.totalCost);
            kmap.put("operations_count", kr.operations);
            kmap.put("execution_time_ms", kr.timeMs);

            Prim.Result pr = Prim.run(g);
            Map<String,Object> pmap = new LinkedHashMap<>();
            pmap.put("mst_edges", pr.mstEdges.stream().map(e -> edgeMap(e)).collect(Collectors.toList()));
            pmap.put("total_cost", pr.totalCost);
            pmap.put("operations_count", pr.operations);
            pmap.put("execution_time_ms", pr.timeMs);

            result.put("prim", pmap);
            result.put("kruskal", kmap);
            results.add(result);
        }

        IOUtil.writeOutput(outputPath, results);
        createCSV("summary.csv", results);
        System.out.println("Processed " + results.size() + " graphs. Output saved to " + outputPath);
    }

    private static Map<String,Object> edgeMap(Edge e){
        Map<String,Object> m = new LinkedHashMap<>();
        m.put("from", e.from);
        m.put("to", e.to);
        m.put("weight", e.weight);
        return m;
    }

    private static void createCSV(String path, List<Map<String,Object>> results) throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("graph_id,vertices,edges,prim_cost,kruskal_cost,prim_time_ms,kruskal_time_ms,prim_ops,kruskal_ops");
        for(Map<String,Object> r : results){
            int gid = (int)((Number) r.get("graph_id")).intValue();
            Map<String,Object> ins = (Map<String,Object>) r.get("input_stats");
            int v = ((Number) ins.get("vertices")).intValue();
            int e = ((Number) ins.get("edges")).intValue();
            Map<String,Object> prim = (Map<String,Object>) r.get("prim");
            Map<String,Object> kr = (Map<String,Object>) r.get("kruskal");
            lines.add(String.format(Locale.ROOT, "%d,%d,%d,%d,%d,%.3f,%.3f,%d,%d",
                    gid, v, e,
                    ((Number)prim.get("total_cost")).intValue(),
                    ((Number)kr.get("total_cost")).intValue(),
                    ((Number)prim.get("execution_time_ms")).doubleValue(),
                    ((Number)kr.get("execution_time_ms")).doubleValue(),
                    ((Number)prim.get("operations_count")).longValue(),
                    ((Number)kr.get("operations_count")).longValue()
            ));
        }
        Files.write(Paths.get(path), lines);
    }
}
