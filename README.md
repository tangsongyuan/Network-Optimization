# csce629project

Network optimization has been an important area in the current research in computer
science and computer engineering. In this course project, you will implement a network
routing protocol using the data structures and algorithms we have studied in class. This
provides you with an opportunity to translate your theoretical understanding into a realworld
practical computer program. Translating algorithmic ideas at a “higher level” of
abstraction into real implementations in a particular programming language is not at
all always trivial. The implementations often force you to work on more details of the
algorithms, which sometimes may lead to much better understanding.
Your implementation should include the following parts:
1. Random Graph Generation. Write subroutines that generate two kinds of “random”
graphs of 5000 vertices.
• In the first graph G1, every vertex has degree exactly 6;
• In the second graph G2, each vertex has edges going to about 20% of the other
vertices;
• Randomly assign positive weights to edges in the graphs.
2. Heap Structure Write subroutines for the max-heap structure. In particular,
your implementation should include subroutines for maximum, insert, and delete. See
Homework #1, Problem 4.
Since the heap structure you implement will be used for a Dijkstra-style algorithm in
the routing protocol, we suggest the following data structures in your implementation:
• The vertices of a graph are named by integers 1, 2, . . ., n;
• The heap is given by an array H[1..5000], where each element H[i] gives the name
of a vertex in the graph;
• The vertex “values” are given in another array D[1..5000]. Thus, to find the value
of a vertex H[i] in the heap, we can use D[H[i]].
3. Routing Algorithms Your algorithms are to solve the Max-Bandwidth-Path
problem for which you need to find a path of the maximum bandwidth between two
vertices in a given weighted undirected graph. You should have three different versions of
implementations:
• An algorithm for Max-Bandwidth-Path based on a modification of Dijkstra’s
algorithm without using a heap structure;
• An algorithm for Max-Bandwidth-Path based on a modification of Dijkstra’s
algorithm using a heap structure for fringes;
• An algorithm for Max-Bandwidth-Path based on a modification of Kruskal’s algorithm,
in which the edges are sorted by HeapSort.
4. Testing. Test you routing algorithms on 5 pairs of graphs, randomly generated using
your subroutines implemented in Step 1. For each generated graph, pick at least 5 pairs
of randomly selected source-destination vertices. For each source-destination pair (s, t) on
a graph G, do the following:
• add a path from s to t that goes through all vertices in the graph G — this is to
ensure that there are always paths connecting s and t, and randomly assign positive
weights to the new edges on the path;
• Run each of the three algorithms on the pair (s, t) and the graph G, and record
their running time (you should find a proper way to “count” the running time of an
algorithm).
