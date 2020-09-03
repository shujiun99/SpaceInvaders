/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;


public class Level {
     int numlevel ;
     int Enemy;
     int rightdirection = 1;
     int leftdirection = -1;
    
   public Level(int level)
    {
    setLevel(level);
    }
   
    public void setLevel(int level)
    {
    this.numlevel = level;
    }
    
    public int getLevel() 
    {
    return numlevel;
    }
    
    public int addSpeed(int direction, int level)
    { 
        this.numlevel = level;
    if(direction < 0)
    {//left
        direction = leftdirection - level;
    }
    else if(direction > 0)
    {//right
       direction  = rightdirection + level;
    }
    return direction;
    
    }
    
   public int addEnemy(int level)
    {
    Enemy = 2 + level;
    return Enemy;
    }
   
    public int updateLevel(int level)
    {
    this.numlevel = level;
    level++;
    return level;
    } 
    
    public int resetlvl()
    {
    this.numlevel = 1;
    return numlevel;
    }
    
}
