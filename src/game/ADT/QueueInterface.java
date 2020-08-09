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
public interface QueueInterface<T> {
    public void enqueue(T newEntry);
    
    public T dequeue();
    
    public boolean isEmpty();
    
    public int size();
    
    public boolean isFull();
}
