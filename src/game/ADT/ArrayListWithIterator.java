/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author User
 */
public class ArrayListWithIterator<Enemy> implements ArrListWithIteratorInterface<Enemy> {

    private Enemy[] array;
    private int length;
    private static final int DEFAULT_CAPACITY = 25;

    public ArrayListWithIterator() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListWithIterator(int initialCapacity) {
        length = 0;
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        Enemy[] tempList = (Enemy[]) new Object[initialCapacity];
        array = tempList;
    }

    @Override
    public Iterator<Enemy> getIterator() {
        return new IteratorForArrayList();
    }

    @Override
    public boolean add(Enemy newEntry) {
        array[length] = newEntry;
        length++;
        return true;
    }

    @Override
    public boolean add(int newPosition, Enemy newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= length + 1)) {
            if (!isArrayFull()) {
                makeRoom(newPosition);
                array[newPosition - 1] = newEntry;
                length++;
            }
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public Enemy remove(int givenPosition) {
        Enemy result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            result = array[givenPosition - 1];

            if (givenPosition < length) {
                removeGap(givenPosition);
            }

            length--;
        }

        return result;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean replace(int givenPosition, Enemy newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public Enemy getEntry(int givenPosition) {
        Enemy result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(Enemy anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < length); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }

        return found;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < length; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    private boolean isArrayFull() {
        return length == array.length;
    }

    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= length + 1; length is array's length before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * length; length is array's length before removal.
     */
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    private class IteratorForArrayList implements Iterator<Enemy> {

        private int nextIndex;
        private boolean wasNextCalled; // needed by remove

        private IteratorForArrayList() {
            nextIndex = 0;
            wasNextCalled = false;
        }

        public boolean hasNext() {
            return nextIndex < length;
        }

        public Enemy next() {
            if (hasNext()) {
                wasNextCalled = true;
                Enemy nextEntry = array[nextIndex];
                nextIndex++; // advance iterator

                return nextEntry;
            } else {
                throw new NoSuchElementException("Illegal call to next();"
                        + "iterator is after end of list.");
            }
        }

        public void remove() {
            if (wasNextCalled) {
                // nextIndex was incremented by the call to next, so it 
                // is the position number of the entry to be removed
                ArrayListWithIterator.this.remove(nextIndex);
                nextIndex--;           // index of next entry in iteration
                wasNextCalled = false; // reset flag
            } else {
                throw new IllegalStateException("Illegal call to remove(); "
                        + "next() was not called.");
            }
        }
    }
}
