/**
 * CS3340 2023
 * Assignment 3
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-04-04
 */
public class Heap implements HeapADT<Edge> {

    // private variables
    private Edge[] edges;  // array of edges
    private int[] H;  // array to represent a heap of indices.
    private int max;
    private int size;

    /*
     * Default Constructor
     */
    public Heap() {
    }

    /*
     * Initializes a heap with the array keys of n edges,
     *
     * @param keys an array of edges
     * @param n the number of edges
     */
    @Override
    public void heap(Edge[] keys, int n) {
        // the array of edges
        edges = keys;
        max = n - 1;
        // array to represent a heap of indices
        H = new int[2 * max];
        size = 2 * max - 1;
        // construct the heap
        heapify();
    }

    /*
     * Returns true if the edge with id is in the heap; false otherwise
     *
     * @param id the edge to check
     * @return true if the edge is in the heap, false otherwise
     */
    @Override
    public boolean in_heap(Edge id) {
        // create an edge for comparison
        Edge edge = new Edge(0, 0, 0);

        for (int i = 0; i < edges.length; i++) {
            edge = edges[i];
            // return true if weight and vertex of edge match
            if (edge.getStartVertex() == id.getStartVertex() && edge.getEndVertex() == id.getEndVertex() && edge.getWeight() == id.getWeight()) {
                return true;
                // return true if reverse match
            } else if (edge.getStartVertex() == id.getEndVertex() && edge.getEndVertex() == id.getStartVertex() && edge.getWeight() == id.getWeight()) {
                return true;
            }
        }
        // id is not found
        return false;
    }

    /*
     * Returns true if heap is empty; false otherwise
     *
     * @return true if heap is empty, false otherwise
     */
    @Override
    public boolean is_empty() {
        return size == 0;
    }

    /*
     * Returns the minimum key of the heap
     *
     * @return the minimum key of the heap
     */
    @Override
    public int min_key() {
        return edges[H[1]].getWeight();
    }

    /*
     * Returns the id of the element with minimum key in the heap
     *
     * @return the id of the element with minimum key in the heap
     */
    @Override
    public int min_id() {
        return H[1];
    }

    /*
     * Returns the key of the edge with id in the heap
     *
     * @param the id of the edge
     * @returns the key of the edge with id in the heap
     */
    @Override
    public int key(int id) {
        return edges[id].getWeight();
    }

    /*
     * Deletes the edge with minimum key from the heap
     *
     * @return the deleted edge
     */
    @Override
    public Edge delete_min() {
        Edge edge = new Edge(0, 0, Integer.MAX_VALUE);
        edges[0] = edge;
        int index = H[1];  // minimum index
        H[index + max - 1] = 0;
        Edge rEdge = edges[index]; // edge to delete

        reheapify(index);
        return rEdge;
    }

    /*
     * Sets the key of the element with id to new key
     * if its current key is greater than new key.
     *
     * @param id the id of the edge
     * @param new_key the value of new key
     */
    @Override
    public void decrease_key(int id, int new_key) {
        edges[id].setWeight(new_key);
        reheapify(id);
    }

    // Help functions
    /*
     * construct the heap
     */
    private void heapify() {
        for (int i = max; i <= size; i++) {
            H[i] = i - max + 1;
        }
        for (int i = max - 1; i >= 1; i--) {
            if (edges[H[2 * i]].getWeight() < edges[H[2 * i + 1]].getWeight()) {
                H[i] = H[2 * i];
            } else {
                H[i] = H[2 * i + 1];
            }
        }
    }

    /*
     *  Help function to do the reheapification
     *
     * @param id parent index
     */
    private void reheapify(int id) {
        int i = (int) Math.floor((id + max - 1) / 2);   // parent index

        while (i >= 1) {
            if (edges[H[2 * i]].getWeight() < edges[H[2 * i + 1]].getWeight()) {
                H[i] = H[2 * i];
            } else {
                H[i] = H[2 * i + 1];
            }
            i = (int) Math.floor(i / 2);
        }
    }
}
