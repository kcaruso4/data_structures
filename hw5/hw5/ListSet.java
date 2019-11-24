/**
* Keilani Caruso, kcaruso4, kcaruso4@jhu.edu
*/

package hw5;

import java.util.Iterator;

/**
 * Set implemented using our abstract List, with a LinkedList.
 *
 * write the missing method bodies below.
 *
 * @param <T> Element type.
 */
public class ListSet<T extends Comparable<? super T>> implements Set<T> {

    /**
    * This if the linkedlist object of the implementation.
    */
    protected List<T> list;

    /**
    * This si the class constructor.
    */
    public ListSet() {
        list = new LinkedList<T>();
    }

    protected boolean find(T t) {
        if (list.empty()) {
            return false;
        }
        T temp = null;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            temp = it.next();
            if (temp.compareTo(t) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(T t) {
        if (!this.has(t)) {
            list.insertFront(t);
        }
    }

    @Override
    public void remove(T t) {
        if (list.empty()) {
            return;
        }
        if (this.has(t)) {
            LinkedList<T> tempList = new LinkedList<T>();
            T temp = null;
            Iterator<T> it = this.iterator();
            while (it.hasNext()) {
                temp = it.next();
                if (temp.compareTo(t) == 0) {
                    continue;
                }
                tempList.insertFront(temp);
            }
            list = tempList;
        }
    }

    @Override
    public boolean has(T t) {
        return this.find(t);
    }

    @Override
    public int size() {
        return list.length();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

}
