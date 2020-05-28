import java.util.*;
import java.lang.reflect.*;

/**
 * Generic version of the MinHeap class.
 * @param <T> the type of the value being added
 */

class MinHeap <T extends Comparable> {
	private T[] heapArray;
	private int heapSize;	// number of heap elements

	/**
	 * Create an empty MinHeap of size capacity
	 */
	MinHeap(Class<T> elemType, int capacity) {
		heapArray = (T []) Array.newInstance(elemType, capacity);
		heapSize = 0;
	}


	/**
	 * Insert item into the MinHeap
	 */
	void Insert (T item) {
		if(heapSize ==0) {
			heapArray[1] = item;
			heapSize ++;
		}
		else {
			heapSize++;
			int tmp = heapSize;
			T change;
			heapArray[tmp] = item;
			if(heapArray[tmp/2].compareTo(item)>0) {
				while(tmp!=1) {
					if(heapArray[tmp].compareTo(heapArray[tmp/2])<0) {
						change = heapArray[tmp];
						heapArray[tmp] = heapArray[tmp/2];
						heapArray[tmp/2] =change;
						tmp = tmp/2;
						if(tmp/2<1) {
							break;
					}
					}
					else {
						tmp = tmp/2;
					}
					
				}
				
			}
		}


	}

	void PostOrder (final int idx) { 
		if(heapSize >= idx) {
			PostOrder(idx*2);
			PostOrder(idx*2+1);
			System.out.print(heapArray[idx] +" ");
		}



	}


	public String toString() {
		String a = new String();
        a = "Min Heap : - ";
        for(int i = 1; i <= heapSize; i++) {
            a += heapArray[i] + "  ";
        }
        return a;
    }
}; 




