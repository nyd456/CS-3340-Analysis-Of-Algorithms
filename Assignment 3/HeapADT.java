/**
 * CS3340 2023
 * Assignment 3
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-04-04
 */
public interface HeapADT<E> {
    /*
     * Initializes a heap with the array keys of n elements,
     *
     * @param keys an array of elements
     * @param n the number of elements
     */
    void heap(E keys[], int n);

    /*
     * Returns true if the element with id is in the heap; false otherwise
     *
     * @param id the element to check
     * @return true if the element is in the heap, false otherwise
     */
    boolean in_heap(E id);

    /*
     * Returns true if heap is empty; false otherwise
     *
     * @return true if heap is empty, false otherwise
     */
    boolean is_empty();

    /*
     * Returns the minimum key of the heap
     *
     * @return the minimum key of the heap
     */
    int min_key();

    /*
     * Returns the id of the element with minimum key in the heap
     *
     * @return the id of the element with minimum key in the heap
     */
    int min_id();

    /*
     * Returns the key of the element with id in the heap
     *
     * @param the id of the key element
     * @returns the key of the element with id in the heap
     */
    int key(int id);

    /*
     * Deletes the element with minimum key from the heap
     *
     * @return the deleted element
     */
    E delete_min();

    /*
     * Sets the key of the element with id to new key
     * if its current key is greater than new key.
     *
     * @param id the id of the key element
     * @param new_key the value of new key
     */
    void decrease_key(int id, int new_key);
}