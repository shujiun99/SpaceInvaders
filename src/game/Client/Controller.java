/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Client;


import game.ADT.ArrayQueue;
import game.ADT.QueueInterface;
import game.Entity.Shot;
import game.Entity.Weapon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author User
 */
 public class Controller{
        
         
         public LinkedList<Shot> es = new LinkedList<Shot>();
         public ArrayList<Weapon> w = new ArrayList<Weapon>();
        public QueueInterface<Weapon> waitingW = new ArrayQueue<Weapon>();
        
         
         Shot ts;
         Game game;
         
         
         
         Weapon tw;
         
         public Controller(Game game){
              this.game = game;
              
             
         }
         
         public void tick(){
             for(int i = 0; i<es.size();i++){
                 ts = es.get(i);
                 
                 if(ts.getY() < 0)
                     removeBullet(ts);
                 
                 
                 ts.tick();
             }
             
             
         }
         
         public void render(Graphics g){
              for(int i = 0; i<es.size();i++){
                 ts = es.get(i);
                 
                 ts.render(g);
             }
              
             
             for(int i = 0; i<w.size();i++){
                 tw = w.get(i);
                 tw.render(g);
                 
             }
         }
         
         public void addBullet(Shot block){
             es.add(block);
         }
         public void removeBullet(Shot block){
             es.remove(block);
         }
         
         
        public void addWeaponW(Weapon block){
            waitingW.enqueue(block);
        }
        
        public void addWeapon(Weapon block){
            w.add(block);
        }
         
        public Weapon removeWeaponW(){
            return waitingW.dequeue();
        }
        
        public void removeWeapon(Weapon block){
            w.remove(block);
        }
         
        public LinkedList getEs(){
            return es;
        }
         
         
        public ArrayList getW(){
            return w;
        }
        
        public boolean isWEmpty(){
            return w.isEmpty();
        }

    public boolean isEmptyWaitingW() {
        return waitingW.isEmpty();
    }
        
        
    
     }
