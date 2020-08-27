/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;


public class Level {
    public int lvl ;
    private int speed;
    private int NumEnemy;
    
   public Level(int level)
    {
    initLevel(level);
    }
    
    private void initLevel(int lvel)
    {
    this.lvl = lvel;
    }
    
    public int IncMove(int direction, int level)
    {
    this.lvl = level;
    return direction + level;
    }
    
    public int IncLevel(int level)
    {
    this.lvl = level;
    level++;
    return level;
    }
    
    public void IncEnemy(int lvl)
    {
    this.NumEnemy = 2 + lvl;
    }
    
    public void resetlvl()
    {
    lvl = 1;
    NumEnemy = 0;
    speed = 0;
    }
    
}
