/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;


public class Level {
    public int lvl ;
    
   public Level(int level)
    {
    initLevel(level);
    }
    
    private void initLevel(int lvel)
    {
    this.lvl = lvel;
    }
    
    //public int IncMove(int direction, int level)
    //{
   //this.lvl = level;
    //return direction + level;
    //}
    
    public int IncLevel(int level)
    {
    this.lvl = level;
    if(level<0)
    {
        
        this.lvl = level - 1;
    }
    else if(level>0)
    {
        
       this.lvl  = level + 1;
    }
    return lvl;
    }
    
       public int getLength() {
        return lvl;
    }
    
    public int resetlvl()
    {
    this.lvl = 1;
    return lvl;
    }
    
}
