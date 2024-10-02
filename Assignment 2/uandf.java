/**
 * CS3340 2023
 * Assignment 2
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-02-24
 */

public class uandf {
    // private variables
    private boolean isFinal;  // flag
    private int[] parents;
    private int[] ranks;

    /*
     * constructs a disjoint-set data type with n elements
     */
    public uandf(int n) {
        // initial
        parents = new int[n];
        ranks = new int[n];
        isFinal = false;
    }

    /*
     * creates a new node - the representative with data i
     */
    public void make_set(int i) {
        if(isFinal == false)  // data structure is finalized and the state should not be changed
        {
            parents[i] = i;
        }
    }

    /*
     * returns the data of type int of the representative of the set containing i
     */
    public int find_set(int i) {
        // Find the representative node
        if (isFinal)  // final set
        {
            return parents[i];
        } else {
            if (parents[i] != i)  // data not match
            {
                int pData = find_set(parents[i]); // recursive call
                parents[i] = pData;  // reset value
                return pData;
            } else {
                return i;  // parent data is i
            }
        }
    }

    /*
     *  unites the dynamic sets that contains i and j, respectively,
     *  into a new set that is the union of these two sets.
     */
    public void union_sets(int i, int j) {
        // data structure is finalized and the state should not be changed
        if(isFinal)
        {
            return;
        }
        i = find_set(i);
        j = find_set(j);

        if (ranks[i] < ranks[j])  // rj > ri
        {
            parents[i] = parents[j];  // pj is the representative
        } else  // ri > rj or ri == rj
        {
            parents[j] = parents[i];  // pi is the representative
            // increase ri by 1 if both ranks are the same
            if (ranks[i] == ranks[j]) {
                ranks[i] = ranks[i]++;
            }
        }
    }

    /*
     * returns the total number of current sets
     * finalizes the current sets:
     * resets the representatives of the sets so that integers
     * from 1 to size will be used as representatives.
     */
    public int final_sets() {
        int total = 0;

        // find representatives
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != 0) {
                find_set(i); // find representative
            }
        }
        // find set count and update associated ranks
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == i)  // set found
            {
                total++; // increase set count
                parents[i] = total;
                ranks[i] = 1;
            } else {    // not a set
                ranks[0] = 0;
            }
        }

        for (int i = 0; i < parents.length; i++) {
            if (ranks[i] == 0 && parents[i] > 0) {
                parents[i] = parents[parents[i]];
            }
        }
        isFinal = true;
        return total;
    }
}

