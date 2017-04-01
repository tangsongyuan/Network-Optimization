package networkOptimization;

import java.util.ArrayList;

/**
 * Created by Songyuan on 3/29/17.
 */
public class maxHeapForAlgo3 {

    private static Edge[] maxHeap;
    private static int heapNumber;

    public maxHeapForAlgo3(int edgeNumber) {
        maxHeap = new Edge[edgeNumber];
        heapNumber = 0;
    }

    public static int getHeapNumber() {
        return heapNumber;
    }

    public static Edge[] getMaxHeap() {
        return maxHeap;
    }

    public static void insert(Edge edge) {
        heapNumber++;
        maxHeap[heapNumber] = edge;
        heapfy(heapNumber);
    }

    public static Edge maximum() {
        return maxHeap[1];
    }

    public static void delete(int index) {
        maxHeap[index] = maxHeap[heapNumber];
        maxHeap[heapNumber] = null;
        heapNumber--;
        heapfy(index);
    }

    public static void heapfy(int index) {
        int l = 2 * index;
        int r = 2 * index + 1;
        int largest = index;
        //System.out.println("TEST i " + maxHeap[index].getWeight() + " " + index);
        if (l <= heapNumber && maxHeap[l].getWeight() > maxHeap[index].getWeight()) {
            largest = l;
            //System.out.println("TEST l " + maxHeap[l].getWeight() + " " + l);
        }
        if (r <= heapNumber && maxHeap[r].getWeight() > maxHeap[largest].getWeight()) {
            largest = r;
            //System.out.println("TEST r " + maxHeap[r].getWeight() + " " + r);
        }
        //System.out.println("TEST largest " + largest);
        if (largest != index) {
            swap(index, largest);
            heapfy(largest);
        }
        /*
        if (index > 1 && maxHeap[index].getWeight() > maxHeap[index / 2].getWeight()) {
            // maxHeap[index] is a small number in the bottom, which needs to shift up
            int temp = index;
            while (temp > 1 && maxHeap[temp].getWeight() > maxHeap[temp / 2].getWeight()) {
                swap (temp, temp / 2);
                temp = temp / 2;
            }
        } else if (index < heapNumber / 2 && maxHeap[index].getWeight() <
                Math.max(maxHeap[index * 2].getWeight(), maxHeap[index * 2 + 1].getWeight())) {
            // maxHeap[index] is a large number in the up, which needs to shift down
            int temp = index;
            while (temp <= heapNumber / 2 && maxHeap[index].getWeight() <
                    Math.max(maxHeap[index * 2].getWeight(), maxHeap[index * 2 + 1].getWeight())) {
                int minWeightIndex;
                if (maxHeap[index * 2].getWeight() > maxHeap[index * 2 + 1].getWeight()) {
                    minWeightIndex = index * 2;
                } else minWeightIndex = index * 2 + 1;
                swap (temp, minWeightIndex);
                temp = minWeightIndex;
            }
        }
        */
    }

    public static void swap(int pos1, int pos2) {
        //System.out.println("TEST pos1 pos2 " + maxHeap[pos1] + " " + maxHeap[pos2]);
        Edge tempHeap = maxHeap[pos1];
        maxHeap[pos1] = maxHeap[pos2];
        maxHeap[pos2] = tempHeap;
        //System.out.println("TEST pos1 pos2 " + maxHeap[pos1] + " " + maxHeap[pos2]);
    }

    public static void main(String[] args) {
        /*
        Edge edge1 = new Edge(0, 1, 1);
        Edge edge2 = new Edge(1, 2, 7);
        Edge edge3 = new Edge(2, 3, 16);
        Edge edge4 = new Edge(3, 4, 2);
        Edge edge5 = new Edge(4, 0, 30);
        maxHeapForAlgo3 newHeap = new maxHeapForAlgo3(6);
        Edge max;
        newHeap.insert(edge1);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(edge2);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(edge3);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(edge4);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(edge5);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        */

        Graph graph = graphGenerator.sparseGraphGenerator(5000);
        maxHeapForAlgo3 heap = new maxHeapForAlgo3(graph.edge() + 1);
        for (int v = 0; v < graph.vertices(); v++) {
            ArrayList<Edge> edgeList = graph.getAdj()[v];
            for (Edge edge : edgeList) {
                heap.insert(edge);
            }
        }
        System.out.println("TEST heap number" + heap.getHeapNumber());
        //for (int i = 0; i < heap.getHeapNumber(); i++) {
        for (int i = 0; i < 5; i++) {
            //System.out.println("TEST max bw " + heap.maximum().getWeight());
            delete(1);
        }
        //System.out.println("TEST " + heap.getHeapNumber());

        /*
        int count = 0;
        int wt1 = heap.maximum().getWeight();
        delete(1);
        for (int i = 0; i < heap.getHeapNumber() - 1; i++) {
            int wt2 = heap.maximum().getWeight();
            delete(1);
            if (wt1 < wt2) {
                //System.out.println("TEST wt " + wt1 + " " + wt2);
                count++;
            }
            wt1 = wt2;
        }
        System.out.println("test count " + count);
        */
    }
}
