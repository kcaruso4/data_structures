package hw5;

import java.util.Iterator;

/**
 * Set implemented using our abstract List, with a LinkedList.
 *
 * incorporate MoveToFront heuristic each time a value is
 * accessed. Override the relevant method(s) from ListSet.
 *
 * @param <T> Element type.
 */
public class MoveToFrontListSet<T extends Comparable<? super T>> extends ListSet<T> {
    @Override
    protected boolean find(T t) {
        if (list.empty()) {
            return false;
        }
        T temp = null;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            temp = it.next();
            if (temp.compareTo(t) == 0) {
                this.remove(t);
                list.insertFront(t);
                return true;
            }
        }
        return false;
    }
}
