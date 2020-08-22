/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ADT;

/**
 *
 * @author Comp
 */
public class LList<T> implements LListInterface<T>{
    
    private Node fNode;
    private int length;
    
    public LList()
    {
        clear();
    }
    
    @Override
    public final void clear()
    {
        fNode = null;
        length = 0;
    }
    
    public boolean isEmpty()
    {
        boolean result;
        
        result = length == 0;
        
        return result;
    }
    
    @Override
    public boolean isFull()
    {
        return false;
    }
    
    @Override
    public int getLength()
    {
        return length;
    }
    
    @Override
    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        
        if(isEmpty())
        {
            fNode = newNode;
        }
        else
        {
            Node current = fNode;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
        return true;
    }
    
    @Override
    public boolean add(int newPosition, T newEntry)
    {
        boolean isSuccess = true;
        
        if((newPosition >= 1) && (newPosition <= length + 1))
        {
            Node newNode = new Node(newEntry);
            
            if(isEmpty() || (newPosition == 1))
            {
                newNode.next = fNode;
                fNode = newNode;
            }
            else
            {
                Node nodeB4 = fNode;
                
                for(int i = 1; i < newPosition; ++i)
                {
                    nodeB4 = nodeB4.next;
                }
                newNode.next = nodeB4.next;
                nodeB4.next = newNode;
            }
            length++;
        }
        else
        {
            isSuccess = false;
        }
        return isSuccess;
    }
    
    @Override
    public T remove(int gPosition)
    {
        T result = null;
        
        if((gPosition >= 1) && (gPosition <= length))
        {
            if(gPosition == 1)
            {
                result = fNode.data;
                fNode = fNode.next;
            }
            
            else
            {
                Node nodeBefore = fNode;
                for(int i = 1; i < gPosition - 1; ++i)
                {
                    nodeBefore = nodeBefore.next;
                }
                result = nodeBefore.next.data;
                nodeBefore.next = nodeBefore.next.next;
            }
            length--;
        }
        return result;
    }
    
    @Override
    public boolean replace(int givenPosition, T newEntry)
    {
        boolean isSuccess = true;
        
        if((givenPosition >= 1) && (givenPosition <= length))
        {
            Node current = fNode;
            for(int i = 0; i < givenPosition - 1; ++i)
            {
                current = current.next;
            }
            current.data = newEntry;
        }
        else
        {
            isSuccess = false;
        }
        return isSuccess;
    }
    
    @Override
    public T getEntry(int givenPosition)
    {
        T result = null;
        
        if((givenPosition >= 1) && (givenPosition <= length))
        {
            Node current = fNode;
            for(int i = 0; i < givenPosition - 1; ++i)
            {
                current = current.next;
            }
            result = current.data;
        }
        return result;
    }
    
    @Override
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node current = fNode;
        
        while(!found && (current != null))
        {
            if(anEntry.equals(current.data))
            {
                found = true;
            }
            else
            {
                current = current.next;
            }
        }
        return found;
    }
    
    private class Node
    {
        private T data;
        private Node next;
        
        private Node(T data)
        {
            this.data = data;
            this.next = null;
        }
        
        private Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }
}
