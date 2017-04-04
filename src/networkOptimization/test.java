package networkOptimization;

import java.util.Random;

/**
 * Created by Songyuan on 3/29/17.
 */
public class test {
    private static int NumberOfVertices = 5000;
    private static int NumberOfGraphs = 5;
    private static int NumberOfSourceToDestination = 5;

    public static void main(String[] args) {
        // five pairs of graphs
        for (int i = 0; i < NumberOfGraphs; i++) {

            System.out.println("Test Case for Graphs: " + (i + 1));
            long startTime1 = System.currentTimeMillis();
            Graph graph1 = graphGenerator.sparseGraphGenerator(NumberOfVertices);
            long graphGeneratedTime1 = System.currentTimeMillis();
            System.out.println("Sparse undirected graph generation time: " + (graphGeneratedTime1 - startTime1));
            System.out.println(" ");
            for (int j = 0; j < NumberOfSourceToDestination; j++) {
                // five pairs of sources and destinations
                System.out.println("Test Case for vertices: " + (j + 1));

                long startTimeNew = System.currentTimeMillis();
                Random randomGenerator = new Random();
                int source = randomGenerator.nextInt(NumberOfVertices);
                int destination = -1;
                while (true) {
                    destination = randomGenerator.nextInt(NumberOfVertices);
                    if (source != destination) break;
                }
                System.out.println("Source: " + source + " Destination: " + destination);
                int bw1 = maxBandwidthPathAlgo1.maxBandwidthPath(graph1, source, destination);
                //System.out.println("Max Bandwidth: " + bw1);
                long maxBandwidthTime1 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 1 execution time: " + (maxBandwidthTime1 - startTimeNew));
                int bw2 = maxBandwidthPathAlgo2.maxBandwidthPath(graph1, source, destination);
                //System.out.println("Max Bandwidth: " + bw2);
                long maxBandwidthTime2 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 2 execution time: " + (maxBandwidthTime2 - maxBandwidthTime1));
                int bw3 = maxBandwidthPathAlgo3.maxBandwidthPath(graph1, source, destination);
                //System.out.println("Max Bandwidth: " + bw3);
                long maxBandwidthTime3 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 3 execution time: " + (maxBandwidthTime3 - maxBandwidthTime2));
                System.out.println("Max Bandwidth: " + bw1);
                System.out.println(" ");
            }


            long startTime2 = System.currentTimeMillis();
            Graph graph2 = graphGenerator.denseGraphGenerator(NumberOfVertices);
            long graphGeneratedTime2 = System.currentTimeMillis();
            System.out.println("Dense undirected graph generation time: " + (graphGeneratedTime2 - startTime2));
            System.out.println(" ");
            for (int j = 0; j < NumberOfSourceToDestination; j++) {
                // five pairs of sources and destinations
                System.out.println("Test Case for vertices: " + (j + 1));

                long startTimeNew = System.currentTimeMillis();
                Random randomGenerator = new Random();
                int source = randomGenerator.nextInt(NumberOfVertices);
                int destination = -1;
                while (true) {
                    destination = randomGenerator.nextInt(NumberOfVertices);
                    if (source != destination) break;
                }
                System.out.println("Source: " + source + " Destination: " + destination);
                int bw1 = maxBandwidthPathAlgo1.maxBandwidthPath(graph2, source, destination);
                //System.out.println("Max Bandwidth: " + bw1);
                long maxBandwidthTime1 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 1 execution time: " + (maxBandwidthTime1 - startTimeNew));
                int bw2 = maxBandwidthPathAlgo2.maxBandwidthPath(graph2, source, destination);
                //System.out.println("Max Bandwidth: " + bw2);
                long maxBandwidthTime2 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 2 execution time: " + (maxBandwidthTime2 - maxBandwidthTime1));
                int bw3 = maxBandwidthPathAlgo3.maxBandwidthPath(graph2, source, destination);
                //System.out.println("Max Bandwidth: " + bw3);
                long maxBandwidthTime3 = System.currentTimeMillis();
                System.out.println("Max-Bandwidth-Path Algorithm 3 execution time: " + (maxBandwidthTime3 - maxBandwidthTime2));
                System.out.println("Max Bandwidth: " + bw1);
                System.out.println(" ");
            }
        }
    }
}
