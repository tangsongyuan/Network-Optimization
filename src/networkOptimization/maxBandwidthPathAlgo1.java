package networkOptimization;

import java.util.ArrayList;

/**
 * Created by Songyuan on 3/28/17.
 */
public class maxBandwidthPathAlgo1 {
    public static int[] status;
    public static int[] dad;
    public static int[] bw;

    public static int INTREE = 2;
    public static int FRINGE = 1;
    public static int UNSEEN = 0;

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
        status = new int[graph.vertices()];
        dad = new int[graph.vertices()];
        bw = new int[graph.vertices()];

        for (int i = 0; i < graph.vertices(); i++) {
            status[i] = UNSEEN;
            bw[i] = Integer.MAX_VALUE;
        }

        //Update info of source
        status[source] = INTREE;
        dad[source] = -1;

        //Update vertices adjacent to source
        ArrayList<Edge> verticesToSource = graph.getAdj()[source];
        for (Edge edge : verticesToSource) {
            int w = edge.getOtherVertex(source);
            status[w] = FRINGE;
            dad[w] = source;
            bw[w] = edge.getWeight();
            //System.out.println("TEST " + bw[w]);
        }
        //System.out.println("TEST " + maxBandwidth + " TEST " + maxIndex);

        //Dijsktra algorithm to find destination
        int count = 0;
        while (status[destination] != INTREE) {
            count++;
            //pick a fringe v with max bw[v], that is maxIndex
            int maxBandwidth = Integer.MIN_VALUE, maxIndex = -1;
            for (int i = 0; i < graph.vertices(); i++) {
                if (status[i] == FRINGE) {
                    //System.out.println("TEST " + i + " TEST " + bw[i]);
                    if (bw[i] > maxBandwidth) {
                        maxBandwidth = bw[i];
                        maxIndex = i;
                        //System.out.println("TEST maxBandwidth " + maxBandwidth);
                    }
                }
            }
            status[maxIndex] = INTREE;
            //System.out.println("TEST " + maxBandwidth + " TEST " + maxIndex);

            ArrayList<Edge> verticesToV = graph.getAdj()[maxIndex];
            //System.out.println("TEST verticesToV " + verticesToV);
            for (Edge edge : verticesToV) {
                int w = edge.getOtherVertex(maxIndex);
                //System.out.println("TEST " + w);
                if (status[w] == UNSEEN) {
                    dad[w] = maxIndex;
                    status[w] = FRINGE;
                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                } else if(status[w] == FRINGE && bw[w] < Math.min(bw[maxIndex], edge.getWeight())) {
                    dad[w] = maxIndex;
                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                }
            }
        }
        //System.out.println("TEST count " + count);
        return bw[destination];
    }

    public static void main(String[] args) {
        int source = 0, destination = 3000;

        long startTime = System.currentTimeMillis();
        Graph graph = graphGenerator.sparseGraphGenerator(5000);
        long graphGeneratedTime = System.currentTimeMillis();
        System.out.println("denseGraphGenerator");
        System.out.println("graphGeneratedTime - startTime");
        System.out.println(graphGeneratedTime - startTime);
        maxBandwidthPath(graph, source, destination);
        long maxBandwidthTime = System.currentTimeMillis();
        System.out.println("maxBandwidthTime - graphGeneratedTime");
        System.out.println(maxBandwidthTime - graphGeneratedTime);
        System.out.println(" ");

        long startTime2 = System.currentTimeMillis();
        Graph graph2 = graphGenerator.denseGraphGenerator(5000);
        long graphGeneratedTime2 = System.currentTimeMillis();
        System.out.println("denseGraphGenerator");
        System.out.println("graphGeneratedTime - startTime");
        System.out.println(graphGeneratedTime2 - startTime2);
        maxBandwidthPath(graph2, source, destination);
        long maxBandwidthTime2 = System.currentTimeMillis();
        System.out.println("maxBandwidthTime - graphGeneratedTime");
        System.out.println(maxBandwidthTime2 - graphGeneratedTime2);
    }
}
