package game.ADT;

public class ArraySort<T extends Comparable<T>> implements SortInterface<T> {

    private T[] array;
    private int length;
    private static final int DEFAULT_CAPACITY = 25;
    
    public ArraySort(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArraySort(int initialCapacity){
        length = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        int i = 0;
        while(i < length && newEntry.compareTo(array[i]) > 0){
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        length++;
        return true;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for(int i = 0; !found && (i < length); i++){
            if(anEntry.equals(array[i])){
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

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int OldIndex = length - 1;
        for(int i = OldIndex; i >= newIndex; i--){
            array[i + 1] = array[i];
        }
    }
    
    public String toString(){
        String output = "";
        for (int index = 0; index < length; ++index) {
            output += array[index] + "\n";
        }
        return output;
    }
}
