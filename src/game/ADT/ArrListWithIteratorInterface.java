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
public interface ArrListWithIteratorInterface<Enemy> {

    public Iterator<Enemy> getIterator();

    public boolean add(Enemy newEntry);

    public boolean add(int newPosition, Enemy newEntry);

    public Enemy remove(int givenPosition);

    public void clear();

    public boolean replace(int givenPosition, Enemy newEntry);

    public Enemy getEntry(int givenPosition);

    public boolean contains(Enemy anEntry);

    public int getLength();

    public boolean isEmpty();

    public boolean isFull();

}
