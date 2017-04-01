package networkOptimization;

import sun.java2d.loops.GeneralRenderer;

import java.util.ArrayList;

/**
 * Created by Songyuan on 3/29/17.
 */
public class maxBandwidthPathAlgo3 {

    private static maxHeapForAlgo3 heap;
    private static int[] dad;
    private static int[] rank;
    private static Graph newGraph;

    private static void sortEdgesInDecreasingOrder(Graph graph) {
        //System.out.println("TEST " + graph.vertices() + " TEST " + graph.edge());
        heap = new maxHeapForAlgo3(graph.edge() + 1);
        for (int v = 0; v < graph.vertices(); v++) {
            ArrayList<Edge> edgeList = graph.getAdj()[v];
            for (Edge edge : edgeList) {
                heap.insert(edge);
            }
        }
        //System.out.println("TEST " + heap.getHeapNumber());
        //for (int i = 0; i < graph.vertices()/100; i++) {
        //    System.out.println("TEST " + heap.getMaxHeap()[100 * i]);
        //}
    }

    public static int find(int vertex) {
        int v = vertex;
        while (dad[v] != v) {
            v = dad[v];
        }
        //System.out.println("TEST v " + v);
        return v;
    }

    public static void union(int r1, int r2) {
        if (rank[r1] > rank[r2]) {
            dad[r2] = r1;
        } else if (rank[r1] < rank[r2]) {
            dad[r1] = r2;
        } else {
            dad[r1] = r2;
            rank[r1]++;
        }
    }

    public static void maxBandwidthPath(Graph graph, int source, int destination) {
        // Kruskal Algorithm
        // Sort all edges in decreasing order first
        sortEdgesInDecreasingOrder(graph);
        //System.out.println("TEST " + heap.getMaxHeap()[100]);

        // Initialize dad and rank for every vertex
        dad = new int[graph.vertices()];
        rank = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            dad[i] = i;
            rank[i] = 1;
        }

        // Merge into a Maximum Spanning Tree, newGraph stores all edges in MST
        newGraph = new Graph(graph.vertices());
        System.out.println("TEST heapNumber " + heap.getHeapNumber());
        for (int e = 0; e < graph.edge(); e++) {
        //for (int i = 0; i < heap.getHeapNumber(); i++) {
            Edge edge = heap.maximum();
            // Get the start and end vertices of maximum edge, then find the ranks for two vertices
            int U = edge.getStart();
            int V = edge.getEnd();
            int R1 = find(U);
            int R2 = find(V);
            //System.out.println("TEST R1 " + R1 + " R2 " + R2);
            if (R1 != R2) {
                newGraph.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
                union(R1, R2);
            }
            heap.delete(1);
        }
        //for (int i = 0; i < dad.length; i ++) {
        //    System.out.println("TEST dad " + dad[i]);
        //}
        System.out.println("TEST heapNumber " + heap.getHeapNumber());
        System.out.println("TEST newGraph " + newGraph.edge());
        System.out.println("TEST newGraph " + newGraph.vertices());

        // Use DFS to traverse from s to t to obtain the max-bandwidth-path
    }

    public static void main(String[] args) {
        int source = 0, destination = 1000;

        long startTime = System.currentTimeMillis();
        Graph graph = graphGenerator.sparseGraphGenerator(5000);
        long graphGeneratedTime = System.currentTimeMillis();
        System.out.println("denseGraphGenerator");
        //System.out.println("graphGeneratedTime - startTime");
        System.out.println("graphGeneratedTime - startTime " + (graphGeneratedTime - startTime));
        maxBandwidthPath(graph, source, destination);
        long maxBandwidthTime = System.currentTimeMillis();
        //System.out.println("maxBandwidthTime - graphGeneratedTime");
        System.out.println("maxBandwidthTime - graphGeneratedTime " + (maxBandwidthTime - graphGeneratedTime));
    }
}
