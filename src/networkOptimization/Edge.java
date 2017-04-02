package networkOptimization;

/**
 * Created by Songyuan on 17/3/26.
 */
public class Edge {
    private int start;
    private int end;
    private int weight;

    public Edge(int start, int end, int weight) {
        if (start < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (end < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");

        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public int getOneVertex() {
        return start;
    }

    public int getOtherVertex(int x) {
        if (x == start) {
            return end;
        } else if (x == end) {
            return start;
        } else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public String toString() {
        return start + "-" + end + " " + weight;
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 45, 3);
        System.out.println(e);
    }
}
