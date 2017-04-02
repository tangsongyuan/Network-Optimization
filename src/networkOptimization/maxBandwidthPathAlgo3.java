package networkOptimization;

import sun.java2d.loops.GeneralRenderer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Songyuan on 3/29/17.
 */
public class maxBandwidthPathAlgo3 {

    private static maxHeapForAlgo3 heap;
    private static int[] dad;
    private static int[] rank;
    private static Graph newGraph;
    private static int[] color;
    private static int[] dadBFS;
    private static int[] bw;

    private static int BLACK = 2;
    private static int GREY = 1;
    private static int WHITE = 0;

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

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
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
        //System.out.println("TEST heapNumber " + heap.getHeapNumber());
        for (int e = 0; e < graph.edge(); e++) {
        //for (int i = 0; i < heap.getHeapNumber(); i++) {
            Edge edge = heap.maximum();
            // Get the start and end vertices of maximum edge, then find the ranks for two vertices
            //System.out.println("TEST wt " + edge.getWeight());
            //System.out.println("TEST count e " + e);
            int U = edge.getStart();
            int V = edge.getEnd();
            int R1 = find(U);
            int R2 = find(V);
            if (R1 != R2) {
                newGraph.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
                union(R1, R2);
            }
            heap.delete(1);
        }
        //System.out.println("TEST heapNumber " + heap.getHeapNumber());

        /*
        // Print weight on every edge in newGraph
        for (int i = 0; i < newGraph.vertices(); i++) {
            ArrayList<Edge> vEdge = newGraph.getAdj()[i];
            for (Edge edge : vEdge) {
                System.out.println("TEST bw " + edge.getWeight());
            }
        }
        */

        // Use BFS to traverse from s to t to obtain the max-bandwidth-path
        // Initialize to start BFS
        color = new int[newGraph.vertices()];
        dadBFS = new int[newGraph.vertices()];
        bw = new int[newGraph.vertices()];
        for (int v = 0; v < newGraph.vertices(); v++) {
            color[v] = WHITE;
            dadBFS[v] = -1;
            bw[v] = Integer.MAX_VALUE;
        }
        color[source] = GREY;
        dad[source] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (color[destination] != BLACK && !queue.isEmpty()) {
            int u = queue.poll();
            ArrayList<Edge> uEdgeList = newGraph.getAdj()[u];
            for (Edge edge : uEdgeList) {
                int v = edge.getOtherVertex(u);
                if (color[v] == WHITE) {
                    color[v] = GREY;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                    //System.out.println("TEST white bw " + bw[v]);
                    dadBFS[v] = u;
                    queue.offer(v);
                } else if (color[v] == GREY && bw[v] < Math.min(bw[u], edge.getWeight())) {
                    dadBFS[v] = u;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                    //System.out.println("TEST grey bw " + bw[v]);
                }
            }
            color[u] = BLACK;
        }

        /*
        int countBlack = 0;
        int countGrey = 0;
        int countWhite = 0;
        for (int i = 0; i < color.length; i++) {
            if (color[i] == BLACK) countBlack++;
            if (color[i] == GREY) countGrey++;
            if (color[i] == WHITE) countWhite++;
        }
        System.out.println("TEST vertices " + newGraph.vertices());
        System.out.println("TEST colorBlack " + countBlack);
        System.out.println("TEST countGrey " + countGrey);
        System.out.println("TEST countWhite " + countWhite);

        System.out.println("TEST bw " + bw[destination]);
        */
        return bw[destination];
    }

    public static void main(String[] args) {
        int source = 0, destination = 1000;

        long startTime = System.currentTimeMillis();
        Graph graph = graphGenerator.sparseGraphGenerator(5000);
        long graphGeneratedTime = System.currentTimeMillis();
        System.out.println("denseGraphGenerator");
        //System.out.println("graphGeneratedTime - startTime");
        System.out.println("graphGeneratedTime - startTime " + (graphGeneratedTime - startTime));
        int bw = maxBandwidthPath(graph, source, destination);
        long maxBandwidthTime = System.currentTimeMillis();
        //System.out.println("maxBandwidthTime - graphGeneratedTime");
        System.out.println("maxBandwidthTime - graphGeneratedTime " + (maxBandwidthTime - graphGeneratedTime));
        System.out.println("max bandwidth: " + bw);
    }
}
