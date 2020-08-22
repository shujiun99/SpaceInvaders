/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ADT;

/**
 *
 * @author User
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    private int frontIndex = 0;
    private int backIndex;
    private T[] array;
    private static final int DEFAULT_CAPACITY = 10; 
    
    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayQueue(int initialCapacity){
        array = (T[]) new Object[initialCapacity];
        backIndex = -1;
    }
    
    @Override
    public void enqueue(T newEntry) {
        if(!isFull()){
            backIndex++;
            array[backIndex] = newEntry;
        }
    }

    @Override
    public T dequeue() {
        T f = null;
        
        if(!isEmpty()){
            f = array[frontIndex];
            int i =0;
            do{
                array[i]=array[i+1];
                i++;
            }while(i<backIndex);
            backIndex--;
        }
        return f;
    }

    @Override
    public boolean isEmpty() {
        if(backIndex == -1){
            return true;
        }
        return false;
    }
    
    public boolean isFull(){
        if(backIndex == (DEFAULT_CAPACITY-1)){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return backIndex;
    }

    @Override
    public void clear() {
        if(!isEmpty()){
            for(int i=0; i<backIndex;i++){
                array[i] = null;
            }
            
            backIndex = -1;
        }
    }
    
}
