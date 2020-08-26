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
public interface ArrayListwithInterface<T> {
    
    public boolean add(T newEntry);
    
    public boolean add(int givenPos, T newEntry);
    
    public T remove(int givenPos);
    
    public void clear();
    
    public boolean replace(int givenPos, T newEntry);
    
    public T getEntry(int givenPos);
    
    public boolean contains(T anEntry);
    
    public int getLength();
    
    public boolean isEmpty();
    
    public boolean isFull();
}

