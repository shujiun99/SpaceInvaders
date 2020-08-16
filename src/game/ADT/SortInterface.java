package game.ADT;

public interface SortInterface<T extends Comparable<T>>{
    public boolean add(T newEntry);
    public void clear();
    public boolean contains(T anEntry);
    public int getLength();
    public boolean isEmpty();
}

