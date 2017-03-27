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

    public static Graph undirectedGraphGenerator2 (int numberOfVertices) {
        Graph graph = new Graph(numberOfVertices);
        Random randomGenerator = new Random();
        //Generate Vertices
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


    public static Graph undirectedGraphGenerator (int numberOfVertices) {
        Graph graph = new Graph(numberOfVertices);
        Random randomGenerator = new Random();
        //Generate Vertices
        for (int i = 0; i < numberOfVertices; i++) {
            //System.out.println("Vertex:" + i);
            //System.out.println("Degree left:" + (DegreeOfUndirectedGraph - Graph.degree(graph, i)) );
            int degreeRemaining = DegreeOfUndirectedGraph - Graph.degree(graph, i);
            for (int j = 0; j < degreeRemaining; j++) {
                int count = 0;
                boolean flag = false;
                while (true) {
                    count++;
                    int vertex = -1;
                    if (count < numberOfVertices) {
                        vertex = randomGenerator.nextInt(numberOfVertices);
                    } else {
                        for (int k = 0; k < numberOfVertices; k++) {
                            if (k != i && Graph.degree(graph, k) < DegreeOfUndirectedGraph && !(containsEdgeTo(i, k, graph.getAdj()[i]))) {
                                vertex = k;
                                break;
                            }
                        }
                    }
                    //System.out.println("Vertex generated:" + vertex);
                    if (vertex == -1) {
                        flag = true;
                        break;
                    }
                    if (Graph.degree(graph, vertex) < DegreeOfUndirectedGraph) {
                        if (!(containsEdgeTo(i, vertex, graph.getAdj()[i])) && i != vertex) {
                            int weight = randomGenerator.nextInt(1001);
                            graph.addEdge(i, vertex, weight);
                            //System.out.println("Added edge from " + i + " to " + vertex + " of weight " + weight);
                            break;
                        }
                    }
                }
                if (flag) continue;
            }
        }
        System.out.println("INFO: Generate Undirected Sparse Graph");
        return graph;
    }

    private static boolean containsEdgeTo(int i, int vertex, ArrayList<Edge> arrayList) {
        for (Edge edge : arrayList) {
            int w = edge.getOtherVertex(i);
            if (vertex == w) {
                return  true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //*
        //undirectedGraphGenerator
        Graph graph = undirectedGraphGenerator(NumberOfVertices);
        boolean flag = true;
        for(int i=0; i<NumberOfVertices; i++){
            if(graph.degree(graph, i) != DegreeOfUndirectedGraph){
                System.out.println("Vertex " + i + " does has degree " + graph.degree(graph, i));
                flag = false;
            }
        }
        if(flag) {
            System.out.println("Valid graph");
        }
        //*/

        //undirectedGraphGenerator2
        Graph graph2 = undirectedGraphGenerator2(NumberOfVertices);

    }
}
