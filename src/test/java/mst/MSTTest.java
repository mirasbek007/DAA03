package mst;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class MSTTest {

    @Test
    public void testSampleGraphsEqualCost(){
        try {
            List<Map<String,Object>> graphs = IOUtil.readInputFile("input.json");
            for(Map<String,Object> gmap : graphs){
                Graph g = IOUtil.graphFromMap(gmap);
                Kruskal.Result kr = Kruskal.run(g);
                Prim.Result pr = Prim.run(g);
                assertEquals(kr.totalCost, pr.totalCost);
                assertEquals(g.vertexCount()-1, kr.mstEdges.size());
                assertEquals(g.vertexCount()-1, pr.mstEdges.size());
            }
        } catch (Exception ex) {
            fail("Exception reading input: " + ex.getMessage());
        }
    }
}
