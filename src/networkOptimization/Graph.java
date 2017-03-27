package networkOptimization;

import java.util.ArrayList;

/**
 * Created by Songyuan on 17/3/26.
 */
public class Graph {

    private int vertices;
    private int edge;
    ArrayList<Edge>[] adj;

    public Graph(int vertices) {
        //Initialize an empty graph with vertices and 0 edges.
        this.vertices = vertices;
        this.edge = 0;
        adj = (ArrayList<Edge>[])new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public ArrayList<Edge>[] getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edge>[] adj) {
        this.adj = adj;
    }

    public int vertices() {
        return vertices;
    }

    public int edge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    public void addEdge(int start, int end, int weight) {
        Edge edge1 = new Edge(start, end, weight);
        adj[start].add(edge1);
        Edge edge2 = new Edge(start, end, weight);
        adj[end].add(edge2);
        edge += 2;
    }

    public Iterable<Edge> adj(int start) {
        return adj[start];
    }

    public static int degree(Graph G, int start) {
        int degree = 0;
        for (Edge e : G.adj(start)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.vertices(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    public static double averageDegree(Graph G) {
        return 2.0 * G.edge() / G.vertices();
    }

    public static int numberOfSelfLoops(Graph G) {
      int count = 0;
      for (int v = 0; v < G.vertices(); v++) {
          for (Edge edge : G.adj(v)) {
              if (edge.getStart() == edge.getEnd()) {
                  count++;
              }
          }
      }
      return count / 2;
    }
}
