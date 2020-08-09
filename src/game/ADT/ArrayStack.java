package game.ADT;

public class ArrayStack<T> implements StackInterface<T>{
    private T[] array;
    private int  topIndex;
    private static final int DEFAULT_CAPACITY = 20;
    
    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayStack(int initialCapacity){
        array = (T[]) new Object[initialCapacity]; 
        topIndex = -1;
    }
    
    @Override
    public void push(T newEntry) {
        topIndex++;
        
        if(topIndex < array.length){
            array[topIndex] = newEntry;
        }
    }

    @Override
    public T pop() {
        T top = null;
        
        if(!isEmpty()){
            top = array[topIndex];
            array[topIndex] = null;
            topIndex--;
        }
        return top;
    }

    @Override
    public T peek() {
        T top = null;
        
        if(!isEmpty()){
            top = array[topIndex];
        }
        return top;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void clear() {
        topIndex = -1;
    }
    
}
