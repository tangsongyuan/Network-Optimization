/*
package networkOptimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class maxAlgo3test {
}


package haha;



        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.Stack;



public class MaxCapacityPathAlgo3 {

    private static MinHeapAPI heap;
    private static UndirectedGraphAPI undirectedGraph;
    private static int undirectedGraphOrder[];
    private static int dad[];
    private static HashMap<Integer, Integer> ranks = new HashMap<Integer, Integer>();

    public static void executeAlgorithm(UndirectedGraphAPI graph, int source, int destination) throws Exception{
        undirectedGraph = graph;
        long startTime = System.currentTimeMillis();
        negateAllEdgeWeights(undirectedGraph, -1);
        long negateTime = System.currentTimeMillis();
//		System.out.println("First negate time = " + (negateTime-startTime));
        sortInAscendingOrder(undirectedGraph);
        long sortTime = System.currentTimeMillis();
//		System.out.println("Sorting time = " + (sortTime-negateTime));
        executeKruskalsAlgo(undirectedGraph);
        long kruskalTime = System.currentTimeMillis();
//		System.out.println("Kruskal time = " + (kruskalTime-sortTime));
        negateAllEdgeWeights(undirectedGraph, -1);
        long negateTime2 = System.currentTimeMillis();
//		System.out.println("Second negate time = " + (negateTime2-kruskalTime));
//		System.out.println();
//		System.out.println("Total time = " + (negateTime2-startTime));
    }

    private static void sortInAscendingOrder(UndirectedGraphAPI graph) {
        heap = new MinHeapAPI(graph.E());
        for(int i=0; i<graph.V(); i++){
//			System.out.println("Processing Adjacency List of vertex " + i);
            ArrayList<Edge> list = graph.getAdj()[i];
            for(Edge edge : list){
                if(!heap.exists(edge)){
                    heap.insert(edge);
                }
            }
        }
//		System.out.println("Graph edges = " + graph.E()/2);
//		System.out.println("Heap Size = " + heap.getHeapCount());
//		System.out.println();
    }

    private static void executeKruskalsAlgo(UndirectedGraphAPI graph) throws Exception {
        dad = new int[graph.V()];
        for(int i=0; i<graph.V(); i++){
            dad[i] = i;
        }
        int edgesToBeAdded = graph.V()-1;
//		for(int i=0; i<graph.V(); i++){
        while(heap.getHeapCount() != 0 || edgesToBeAdded != 0){
            Edge edge = heap.minimum();
            int v = edge.getOneVertex();
            int r1 = find(v);
            int r2 = find(edge.getOtherVertex(v));
            //System.out.println("r1 = " + r1 + " and r2 = " + r2);
            if(r1 != r2){
                if(!ranks.containsKey(r1) && !ranks.containsKey(r2)){
                    dad[r2] = r1;
                    ranks.put(r1, 2);
                }else if(ranks.containsKey(r1) && !ranks.containsKey(r2)){
                    dad[r2] = r1;
                    ranks.put(r1, ranks.get(r1) + 1);
                }else if(!ranks.containsKey(r1) && ranks.containsKey(r2)){
                    dad[r1] = r2;
                    ranks.put(r2, ranks.get(r2) + 1);
                }else{
                    if(ranks.get(r1) > ranks.get(r2)){
                        for(int j=0; j<dad.length; j++){
                            if(dad[j] == r2){
                                dad[j] = r1;
                            }
                        }
                        ranks.put(r1, ranks.get(r1) + ranks.get(r2));
                        ranks.remove(r2);
                    }else{
                        for(int j=0; j<dad.length; j++){
                            if(dad[j] == r1){
                                dad[j] = r2;
                            }
                        }
                        ranks.put(r2, ranks.get(r2) + ranks.get(r1));
                        ranks.remove(r1);
                    }
                }
//				System.out.println(edge);
                edgesToBeAdded--;
            }
            heap.delete(1);
        }
//		System.out.println();
    }


    private static int find(int vertex){
        Stack<Integer> stack = new Stack<Integer>();
        int h = vertex;
        while(dad[h] != h){
            stack.add(h);
            h = dad[h];
        }
        int w = -1;
        while(!stack.empty()){
            w = stack.firstElement();
            dad[w] = h;
            stack.remove(0);
        }
        return h;
    }




    public static void main(String[] args) throws Exception {
        UndirectedGraphAPI graph = new UndirectedGraphAPI(7);
        graph.addEdge(0, 1, 40);
        graph.addEdge(0, 2, 8);
        graph.addEdge(0, 3, 11);
        graph.addEdge(1, 2, 29);
        graph.addEdge(1, 5, 17);
        graph.addEdge(2, 5, 31);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 4, 46);
        graph.addEdge(4, 5, 40);
        graph.addEdge(4, 6, 15);
        graph.addEdge(5, 6, 53);
        int numberOfVertices = 5000;
        long startTime = System.currentTimeMillis();
        UndirectedGraphAPI graph1 = GraphGenerator.generateUndirectedGraph(numberOfVertices);
//		DirectedGraphAPI graph1 = GraphGenerator.generateDirectedGraph(numberOfVertices);
        long endTime = System.currentTimeMillis();
        System.out.println("Time take to generate graph = " + (endTime-startTime));
        executeAlgorithm(graph1, 0, 5);
    }
}

*/