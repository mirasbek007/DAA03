package mst;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public void makeSet(String v){
        parent.put(v, v);
        rank.put(v, 0);
    }

    public String find(String v){
        String p = parent.get(v);
        if (p == null) return null;
        if (!p.equals(v)) {
            String root = find(p);
            parent.put(v, root);
            return root;
        }
        return p;
    }

    public boolean union(String a, String b){
        String ra = find(a);
        String rb = find(b);
        if (ra == null || rb == null) return false;
        if (ra.equals(rb)) return false;
        int rka = rank.get(ra);
        int rkb = rank.get(rb);
        if (rka < rkb) {
            parent.put(ra, rb);
        } else if (rka > rkb) {
            parent.put(rb, ra);
        } else {
            parent.put(rb, ra);
            rank.put(ra, rka + 1);
        }
        return true;
    }
}
