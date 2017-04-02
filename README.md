CSCE 629 Project
====
In this project, implement a network routing 
protocol using the heap data structure and a modified 
Dijkstra, a modified Kruskal and Breadth-first Search 
(BFS) algorithms to translate theoretical methods into
practical programming language-Java.

This project is achieved in four parts.

**1. Random Graph Generation**
---



Write subroutines that generate two kinds of random 
graphs of 5000 vertices.

1) In the first graph, every vertex has degree
exactly 6.

2) In the second graph, each vertex has edges going to 20% of the
other vertices.


```
Edge.java
Graph.java
graphGenerator.java
```


**2. Heap Structure**
---

Write subroutines for the max-heap structure. In particular, 
implementation should include subrountines for 
MAXIMUM, INSERT and DELETE. Implement two java files for
Maximum-Bandwidth-Path Algorithm 2 and 3.

```
maxHeap.java
maxHeapForAlgo3.java
```

**3. Routing Algorithm**
---

Three algorithms are to solve the MAX-BANDWIDTH-PATH 
problem to find a path of the maximum bandwidth between
two vertices in a given weighted undirected graph.

1) Max-Bandwidth-Path algorithm based on a modification 
of Dijkstra’s algorithm without using a heap structure.

2) Max-Bandwidth-Path algorithm based on a modification 
of Dijkstra’s algorithm with using a heap structure.

3) Max-Bandwidth-Path algorithm based on a modification
 of Kruskal’s algorithm with using a heap structure to sort the edges.

```aidl
maxBandwidthPathAlgo1.java
maxBandwidthPathAlgo2.java
maxBandwidthPathAlgo3.java
```


**4. Testing**
---

Test routing algorithms on 5 pairs of graphs, randomly
generated subroutines implemented in Step 1. For each
generated graph, pick 5 pairs of randomly selected
 source-destination vertices. For each source-destination
 pair (s,t) on a graph G, do the following
 
 1) add a path from s to t that goes through all vertices in the graph G — this is to
 ensure that there are always paths connecting s and t, and randomly assign positive
 weights to the new edges on the path;
 
 2) Run each of the three algorithms on the pair (s, t) and the graph G, and record
    their running time.

```aidl
test.java
```

