/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ADT;

/**
 *
 * @author Ben
 */
public class ArrayList<T> implements ArrayListwithInterface<T>{
    
    private T[] array;
    private int length;
    private static final int DEFAULT_CAPACITY = 3;
    
    public ArrayList()
    {
        this(DEFAULT_CAPACITY);
    }
    
    public ArrayList(int iniCapacity)
    {
        length = 0;
        array = (T[]) new Object[iniCapacity];
    }
    
    public boolean add(T newEntry)
    {
        array[length] = newEntry;
        length++;
        return true;
    }
    
    public boolean add(int newPos, T newEntry)
    {
        boolean isSuccess = true;
        
        if((newPos >= 1) && (newPos <= length + 1))
        {
            if(!isArrayFull())
            {
                room(newPos);
                array[newPos - 1] = newEntry;
                length++;
            }
            else
            {
                isSuccess = false;
            }
        }return isSuccess;
    }
    
    private boolean isArrayFull()
    {
        return length == array.length;
    }
    
    private void room(int newPos)
    {
        int newIndex = newPos - 1;
        int lastIndex = length - 1;
        
        for(int index = lastIndex; index >= newIndex; index--)
        {
            array[index + 1] = array[index];
        }
    }
    
    public T remove(int givenPos)
    {
        T result = null;
        
        if((givenPos >= 1) && (givenPos <= length))
        {
            result = array[givenPos - 1];
            
            if(givenPos < length)
            {
                removeGap(givenPos);
            }
            length--;
        }
        return result;
    }
    
    private void removeGap(int givenPos)
    {
        int removedIndex = givenPos - 1;
        int lastIndex = length - 1;
        
        for(int index = removedIndex; index < lastIndex; index++)
        {
            array[index] = array[index + 1];
        }
    }
    
    public void clear()
    {
        length = 0;
    }
    
    public boolean replace(int givenPos, T newEntry)
    {
        boolean isSuccess = true;
        
        if((givenPos >= 1) && (givenPos <= length))
        {
            array[givenPos - 1] = newEntry;
        }
        else
        {
            isSuccess = false;
        }
        return isSuccess;
    }
    
    public T getEntry(int givenPos)
    {
        T result = null;
        
        if((givenPos >= 1) && (givenPos <= length))
        {
            result = array[givenPos - 1];
        }
        return result;
    }
    
    public boolean contains(T anEntry)
    {
        boolean found = false;
        for(int index = 0; !found && (index < length); index++)
        {
            if(anEntry.equals(array[index]))
            {
                found = true;
            }
        }
        return found;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public boolean isEmpty()
    {
        return length == 0;
    }
    
    public boolean isFull()
    {
        return false;
    }
    
    public String toString()
    {
        String output = "";
        for(int index = 0; index < length; ++index)
        {
            output += array[index] + "\n";
        }
        return output;
    }
    
    public int size()
    {
    return length;
    }
    
}
