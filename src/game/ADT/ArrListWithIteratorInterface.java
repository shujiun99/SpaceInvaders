/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public void clear();

    public boolean replace(int givenPosition, ALWIT newEntry);

    public ALWIT getEntry(int givenPosition);

    public boolean contains(ALWIT anEntry);

    public int getLength();

    public boolean isEmpty();

    public boolean isFull();

}
