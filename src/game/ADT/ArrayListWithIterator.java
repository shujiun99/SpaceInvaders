package game.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListWithIterator<ALWIT> implements ArrListWithIteratorInterface<ALWIT> {

    private ALWIT[] array;
    private int length;
    private static final int DEFAULT_CAPACITY = 30;

    public ArrayListWithIterator() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListWithIterator(int initialCapacity) {
        length = 0;
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        ALWIT[] tempList = (ALWIT[]) new Object[initialCapacity];
        array = tempList;
    }

    @Override
    public Iterator<ALWIT> getIterator() {
        return new IteratorForArrayList();
    }

    @Override
    public boolean add(ALWIT newEntry) {
        if (!isFull()) {
            array[length] = newEntry;
            length++;
            return true;
        } else {
            System.out.println("Array is full");
            return false;
        }
    }

    @Override
    public boolean add(int Position, ALWIT newEntry) {

        if (!isFull()) {
            if (Position == 0) {

                array[0] = newEntry;
                for (int i = 1; i < Position; i++) {
                    array[i] = array[i + 1];
                }
                length++;
                return true;
            } else if (Position > 0 && Position < DEFAULT_CAPACITY) {
                if (array[Position] == null) {
                    array[Position] = newEntry;
                    length++;
                    return true;
                } else {
                    ALWIT[] tempList = (ALWIT[]) new Object[length];
                    for (int i = Position; i < length; i++) {
                        tempList[i] = array[i];
                    }
                    length++;
                    array[Position] = newEntry;
                    for (int i = Position; i < length - 1; i++) {
                        array[i + 1] = tempList[i];
                    }
                    return true;
                }
            } else {
                System.out.println("Add new Entry failed.");
                return false;
            }
        } else {
            System.out.println("No Space to add new Entry.");
            return false;
        }
    }

    @Override
    public ALWIT remove(int givenPosition) {
        ALWIT result = null;

        if ((givenPosition >= 0) && (givenPosition <= length)) {
            result = array[givenPosition];

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
    public boolean replace(int givenPosition, ALWIT newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 0) && (givenPosition <= length)) {
            array[givenPosition] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public ALWIT getEntry(int givenPosition) {
        ALWIT result = null;

        if ((givenPosition >= 0) && (givenPosition <= length)) {
            result = array[givenPosition];
        }

        return result;
    }

    @Override
    public boolean contains(ALWIT anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < length); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == DEFAULT_CAPACITY;
    }

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
    /*
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = length - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }
     */
    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * length; length is array's length before removal.
     */
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        int lastIndex = length - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    private class IteratorForArrayList implements Iterator<ALWIT> {

        private int nextIndex;
        private boolean wasNextCalled; // needed by remove

        private IteratorForArrayList() {
            nextIndex = 0;
            wasNextCalled = false;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < length;
        }

        @Override
        public ALWIT next() {
            if (hasNext()) {
                wasNextCalled = true;
                ALWIT nextEntry = array[nextIndex];
                nextIndex++; // advance iterator

                return nextEntry;
            } else {
                throw new NoSuchElementException("Illegal call to next();"
                        + "iterator is after end of list.");
            }
        }

        @Override
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
