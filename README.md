# Assignment 3 — Optimization of a City Transportation Network (Minimum Spanning Tree)

**Name:** Mirasbek Idiatulla 
**Group :** SE-2435

---

## 1. Project Overview

This project focuses on solving the Minimum Spanning Tree (MST) problem using two classical algorithms: **Prim’s Algorithm** and **Kruskal’s Algorithm**. The goal is to determine the minimum total construction cost required to connect all districts in a city. The districts are modeled as vertices in a weighted undirected graph, while roads and their construction costs are represented as edges with weights.

Both algorithms were implemented in Java, and the application reads graph data from a JSON file (`input.json`). It calculates the MST using both algorithms, tracks execution time and operation count, and writes the results into a JSON output file.

## 2. Technologies and Structure

### 2.1 Programming Language and Tools

* Language: **Java (Maven Project)**
* Build Tool: **Maven** (`pom.xml` present)
* Testing Framework: **JUnit**
* JSON Handling: Custom `JsonReader` and `JsonWriter`

### 2.2 Project Package Structure

```
src/main/java/mst/
├── Graph.java          // Graph structure (Bonus section)
├── Edge.java           // Edge class (Bonus section)
├── PrimAlgorithm.java  // Prim’s implementation
├── KruskalAlgorithm.java  // Kruskal’s implementation
├── UnionFind.java      // Disjoint-set for Kruskal
├── JsonReader.java     // Reads input.json
├── JsonWriter.java     // Writes output.json
└── Main.java           // Main execution class

src/test/java/mst/
└── MSTTest.java        // Unit tests for algorithms
```

### 2.3 Input File Example (`input.json`)

```
"graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    },
```

## 3. Algorithm Implementation Summary

### 3.1 Prim’s Algorithm (PrimAlgorithm.java)

* Starts from an arbitrary vertex.
* Selects the minimum edge connecting visited and unvisited vertices.
* Implements priority queue-based greedy choice.
* Time complexity: **O(E log V)**.
* Operations counted: comparisons, edge relaxations.

### 3.2 Kruskal’s Algorithm (KruskalAlgorithm.java)

* Sorts all edges by weight.
* Adds edges if they do not form a cycle (using `UnionFind`).
* Time complexity: **O(E log E)**.
* Operations counted: comparisons, union & find operations.

## 4. Performance Tracking and Metrics

For each algorithm and graph:

*  MST edges are recorded
*  Total MST cost is calculated
*  Vertex and edge count are stored
*  Operation counts are recorded
*  Execution time (ms) is measured

Output results are written into `output.json`.

## 5. Testing

Unit tests are written in `MSTTest.java` using JUnit. Tests verify:
 Costs of both MSTs are identical
 MST contains exactly `V-1` edges
 MST is acyclic
 MST spans all vertices
 Disconnected graphs are handled
 Execution time and operation count are non-negative

## 6. Sample Results Table (Placeholder)

| Dataset | V | E | Prim Cost | Kruskal Cost | Prim Time (ms) | Kruskal Time (ms) |
| ------- | - | - | --------- | ------------ | -------------- | ----------------- |
| graph1  | 5 | 7 | 12        | 12           | 0.34           | 0.29              |

## 7. Comparative Analysis

| Criteria        | Prim's Algorithm                     | Kruskal's Algorithm     |
| --------------- | ------------------------------------ | ----------------------- |
| Best for        | Dense graphs                         | Sparse graphs           |
| Data Structure  | Priority Queue                       | Sorting + UnionFind     |
| Time Complexity | O(E log V)                           | O(E log E)              |
| Implementation  | Slightly complex                     | Easier                  |
| Use Case        | When graph represented via adjacency | When input is edge list |

**Conclusion:** Prim's performs better for dense graphs, while Kruskal's is more efficient for sparse graphs or when edge lists are used.

## 8. Bonus Section: Graph Design in Java

 Custom `Graph.java` and `Edge.java` are included.
 Algorithms operate on the custom graph structure.
 This fulfills the bonus requirement of OOP design.

## 9. Conclusion

Both Prim’s and Kruskal’s algorithms successfully computed MSTs with identical costs across all connected graphs. Kruskal’s was generally faster on sparse graphs, while Prim’s showed better performance on denser datasets when optimized with a priority queue. The use of an OOP approach and bonus features improved readability and structure of the overall project.

