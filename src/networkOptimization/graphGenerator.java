package networkOptimization;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Songyuan on 17/3/26.
 */
public class graphGenerator {

    private static int NumberOfVertices = 5000;
    private static int DegreeOfUndirectedGraph = 6;
    private static int Percentage = 20;

    public static Graph denseGraphGenerator (int numberOfVertices) {
        Graph graph = new Graph(numberOfVertices);
        Random randomGenerator = new Random();
        //Generate Vertices in Undirected Dense Graph
        int count = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i + 1; j < numberOfVertices; j++) {
                int randomProbability = randomGenerator.nextInt(101);
                //System.out.println("Generate a random number in [0,100]: " + randomProbability);
                if (randomProbability <= Percentage) {
                    graph.addEdge(i, j, randomProbability);
                    count++;
                }
            }
        }

        System.out.println("INFO: Generate Undirected Dense Graph");
        System.out.println("Added total " + count + " number of edges");
        return graph;
    }

    public static Graph sparseGraphGenerator(int numberOfVertices) {
        Graph graph = new Graph(numberOfVertices);
        Random randomGenerator = new Random();
        int[] degree = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            degree[i] = 0;
        }
        //Generate Vertices in Undirected Sparse Graph
        int numberOfEdge = 0;
        while (numberOfEdge < (NumberOfVertices * DegreeOfUndirectedGraph) / 2) {
            int i = randomGenerator.nextInt(numberOfVertices);
            int j = randomGenerator.nextInt(numberOfVertices);
            int weight = randomGenerator.nextInt(101);
            if (degree[i] < 6 && degree[j] < 6 && i != j) {
                Edge edge = new Edge(i ,j, weight);
                if (!graph.adj[i].contains(edge)) {
                    graph.addEdge(i, j ,weight);
                    //System.out.println("Print edge with start, end and weight: " + edge);
                    degree[i]++;
                    degree[j]++;
                    numberOfEdge++;
                }
            }
        }

        System.out.println("INFO: Generate Undirected Sparse Graph");
        //System.out.println("The number of Edges is " + numberOfEdge);
        if (numberOfEdge == NumberOfVertices * DegreeOfUndirectedGraph / 2) {
            System.out.println("Valid graph");
        } else System.out.println("Invalid graph");
        return graph;
    }

    public static void main(String[] args) {

        //Generate Undirected Sparse Graph
        Graph graph = sparseGraphGenerator(NumberOfVertices);
        /*
        boolean flag = true;
        for(int i = 0; i < NumberOfVertices; i++){
            if(graph.degree(graph, i) != DegreeOfUndirectedGraph){
                System.out.println("Vertex " + i + " does has degree " + graph.degree(graph, i));
                flag = false;
            }
        }
        if(flag) {
            System.out.println("Valid Graph");
        }
        */

        //Generate Undirected Dense Graph
        Graph denseGraph = denseGraphGenerator(NumberOfVertices);
    }
}
