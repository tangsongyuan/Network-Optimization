package networkOptimization;

import java.util.ArrayList;

/**
 * Created by Songyuan on 3/27/17.
 */
public class maxHeap {

    //private static Edge[] maxHeap;
    private static int[] vertices;
    private static int[] value;
    private static int heapNumber;

    public maxHeap(int edgeNumber) {
        vertices = new int[edgeNumber + 1];
        value = new int[edgeNumber + 1];
        heapNumber = 0;
    }

    public static int getHeapNumber() {
        return heapNumber;
    }

    public static int getIndex(int index) {
        return  vertices[index];
    }

    public static void insert(int vertex, int bw) {
        heapNumber++;
        vertices[heapNumber] = vertex;
        value[heapNumber] = bw;
        heapfy(heapNumber);
    }

    public static int maximum() {
        return vertices[1];
    }

    public static void delete(int index) {
        vertices[index] = vertices[heapNumber];
        value[index] = value[heapNumber];
        heapNumber--;
        heapfy(index);
    }

    public static void heapfy(int k) {
        int l = 2 * k;
        int r = 2 * k + 1;
        int largest = k;
        if (l <= heapNumber && value[l] > value[k]) {
            largest = l;
        }
        if (r <= heapNumber && value[r] > value[largest]) {
            largest = r;
        }
        if (largest != k) {
            swap(largest, k);
            heapfy(largest);
        }
        /*
        // this part is not completely correct
        if (k > 1 && value[k] > value[k / 2]) {
            int temp = k;
            while (temp > 1 && value[temp] > value[temp / 2]) {
                swap(temp, temp / 2);
                temp /= 2;
            }
        } else if (k <= heapNumber / 2 && value[k] < Math.max(value[2 * k], value[2 * k + 1])) {
            int temp = k;
            while (temp <= heapNumber / 2 && value[temp] < Math.max(value[2 * temp], value[2 * temp + 1])) {
                int maxValueIndex;
                if (value[2 * temp] > value[2 * temp + 1]) {
                    maxValueIndex = 2 * temp;
                } else  maxValueIndex = 2 * temp + 1;
                swap(temp, maxValueIndex);
                temp = maxValueIndex;
            }
        }
        */
    }

    public static void swap(int pos1, int pos2) {
        int tempVertices = vertices[pos1];
        vertices[pos1] = vertices[pos2];
        vertices[pos2] = tempVertices;
        int tempValue = value[pos1];
        value[pos1] = value[pos2];
        value[pos2] = tempValue;
    }

    public static void main(String[] args) {
        maxHeap newHeap = new maxHeap(10);
        int max;
        newHeap.insert(1,23);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(3,12);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.insert(6,121);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
        newHeap.delete(1);
        newHeap.insert(4,29);
        max = newHeap.maximum();
        System.out.println("TEST " + max);
    }
}
