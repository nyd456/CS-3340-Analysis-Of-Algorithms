/**
 * CS3340 2023
 * Assignment 3
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-04-04
 */

public class Edge {

    // private variables
    private int startVertex;
    private int endVertex;
    private int weight;

    /*
     * creates an edge with a start vertex u, end vertex v, and weight
     *
     * @param startVertex start vertex of the edge
     * @param endVVertex end vertex of the edge
     * @param weight of the edge
     */
    public Edge(int startVertex, int endVVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVVertex;
        this.weight = weight;
    }

    /*
     * Get the start vertex of the edge.
     *
     * @return the start vertex
     */
    public int getStartVertex() {
        return this.startVertex;
    }

    /*
     * Get the end vertex of the edge.
     *
     * @return the end vertex
     */
    public int getEndVertex() {
        return this.endVertex;
    }

    /*
     * Get the weight of the edge.
     *
     * @return the end vertex
     */
    public int getWeight() {
        return weight;
    }

    /*
     * Set the start vertex of the edge.
     *
     * @param the start vertex
     */
    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    /*
     * Set the end vertex of the edge.
     *
     * @param the end vertex
     */
    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }

    /*
     * Set weight of the edge.
     *
     * @param the weight of the edge
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /*
     * Print out the edge
     * u: start Vertex
     * v: end Vertex
     *
     * @return print out text
     */
    public String toString() {
        return "U = " + this.getStartVertex() + " V = " + this.getEndVertex() + " Weight = " + this.weight + "\n";
    }
}
