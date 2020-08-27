/**
 * ListInterface.java An interface for the ADT list. Entries in the list have
 * positions that begin with 1.
 *
 * @author Frank M. Carrano
 * @version 2.0
 */
package game.ADT;

import java.util.Iterator;

/**
 *
 * @author User
 */
public interface ArrListWithIteratorInterface<ALWIT> {

    public Iterator<ALWIT> getIterator();

    public boolean add(ALWIT newEntry);

    public boolean add(int newPosition, ALWIT newEntry);

    public ALWIT remove(int givenPosition);
    
    public boolean remove(ALWIT Entry);

    public void clear();

    public boolean replace(int givenPosition, ALWIT newEntry);

    public ALWIT getEntry(int givenPosition);

    public boolean contains(ALWIT anEntry);

    public int getLength();

    public boolean isEmpty();

    public boolean isFull();

}
