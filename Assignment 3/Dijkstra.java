/**
 * CS3340 2023
 * Assignment 3
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-04-04
 */

import java.util.*;

public class Dijkstra {
    private static int n;  // the number of vertices of the input graph

    /*
     * print out the shortest path that created using Dijkstra single source shortest path algorithm
     *
     * @param graph built from input file
     * @param source the source vertex in the graph
     */
    public static void shortestPath(ArrayList<LinkedList<Edge>> graph, int source) {
        Edge[] edges = new Edge[n + 1];
        int[] parent_ids = new int[n + 1];

        // Initialize graph with default value
        for (int i = 0; i <= n; i++) {
            //set the weight of each edge in the heap into INFINITY as default
            Edge edge = new Edge(0, 0, Integer.MAX_VALUE);
            edges[i] = edge;
            parent_ids[i] = -1;
        }

        //initialize source
        edges[source].setWeight(0);
        edges[source].setStartVertex(source);
        edges[source].setEndVertex(source);
        parent_ids[source] = source;

        // construct a heap with all edges
        Heap heap = new Heap();
        heap.heap(edges, edges.length);

        System.out.println("\nThe shortest path tree edges with shortest path weights:");

        // Extract minimum edge from heap
        Edge min_edge = heap.delete_min();
        int min_weight = min_edge.getWeight();
        int weight_from_source = 0;  // the weight from source to vertex i
        // Go through all items and pick the one with the minimum weight value
        while (min_weight != Integer.MAX_VALUE) {
            // exclude source
            if (min_edge.getStartVertex() != 0 && min_edge.getEndVertex() != 0) {
                // find all the adjacent edges of the selected vertex
                LinkedList<Edge> adj_list = graph.get(min_edge.getEndVertex());

                // Iterate all adjacent edges
                for (int i = 0; i < adj_list.size(); i++) {
                    // current adjacent edge
                    Edge edge = adj_list.get(i);
                    // change the selected edge to the current one if the current one is not in the heap and the weight is less than the selected one
                    if (!heap.in_heap(edge) && edge.getWeight() < edges[edge.getEndVertex()].getWeight()) {
                        edges[edge.getEndVertex()] = edge;
                        parent_ids[edge.getEndVertex()] = edge.getStartVertex();
                        heap.decrease_key(edge.getEndVertex(), edge.getWeight());
                    }
                }

                // if it is the source
                if (min_edge.getStartVertex() == 0 && min_edge.getEndVertex() == 0) {
                    System.out.println("(" + min_edge.getStartVertex() + ", " +  min_edge.getEndVertex() + ") : " + min_edge.getWeight());
                } else {
                    // calculate the weight from source to j
                    weight_from_source = weight_from_source + min_edge.getWeight();
                    System.out.println("(" + parent_ids[min_edge.getEndVertex()] + ", " + min_edge.getEndVertex() + ") : " + weight_from_source);
                }
            }
            // get next min edge
            min_edge = heap.delete_min();
            min_weight = min_edge.getWeight();
        }
    }

    // main
    public static void main(String[] args) {

        System.out.print("\nThe input graph in adjacency list representation: (vertex):weight\n");

        // start to process input file
        Scanner in = new Scanner(System.in);
        // Read the first integer that represents the number of vertices
        n = Integer.parseInt(in.nextLine());
        System.out.print("\nTotal " + n + " vertices in the graph\n");
        System.out.println();

        // a list of adjacency edges
        ArrayList<LinkedList<Edge>> adjacency_list = new ArrayList<LinkedList<Edge>>();
        for (int i = 0; i <= n; i++) {
            adjacency_list.add(new LinkedList<Edge>());
        }
        // Each line contains a triple "u v w", indicating an edge from vertex u to vertex v with cost w
        while (in.hasNext()) {
            String s = in.nextLine();

            int u, v, w;

            // get the value of u, v, and w
            StringTokenizer tok = new StringTokenizer(s);
            u = Integer.parseInt(tok.nextToken());
            v = Integer.parseInt(tok.nextToken());
            w = Integer.parseInt(tok.nextToken());

            // Create edges: (from u to v) and (from v to u)
            Edge uToV = new Edge(u, v, w);
            Edge vToU = new Edge(v, u, w);

            // add edges to their list
            adjacency_list.get(u).addLast(uToV);
            adjacency_list.get(v).addLast(vToU);
        }

        for (int i = 1; i <= n; i++) {
            // Print out the current vertex
            System.out.print(adjacency_list.get(i).get(0).getStartVertex() + ": ");
            // print out the adjacent edges with weight of the current vertex in format (vertex):weight
            for (int j = 0; j < adjacency_list.get(i).size(); j++) {
                if (j == adjacency_list.get(i).size() - 1) {  // exclude "," for the last item
                    System.out.print("(" + adjacency_list.get(i).get(j).getEndVertex() + "):" + adjacency_list.get(i).get(j).getWeight());
                } else {
                    System.out.print("(" + adjacency_list.get(i).get(j).getEndVertex() + "):" + adjacency_list.get(i).get(j).getWeight() + ",");
                }
            }
            System.out.println();
        }

        // The next vertex will be the source node in the adjacency list
        int source = adjacency_list.get(1).get(0).getStartVertex();
        System.out.println();
        // print out The shortest path tree edges with shortest path weights
        shortestPath(adjacency_list, source);
        in.close();
    }
}