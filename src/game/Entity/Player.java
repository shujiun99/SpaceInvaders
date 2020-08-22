/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public class Player extends Entity{
    
    ImageIcon life = new ImageIcon("src/images/shipSkinSmall.gif");
    
    public Player() {
        initPlayer();
    }
    
    private void initPlayer() {
        
        width = 40;
        height = 45;

        var playerImg = "src/images/player.jpg";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        
        bounds = new Rectangle((int)x,(int)y,width,height);

        double START_X = 270;
        setX(START_X);

        double START_Y = 420;
        setY(START_Y);
    }
    
  public void tick(){
      x+=dx;
      y+=dy;
      
      if(x <= 0)
          x = 0;
      if(x >= 600)
          x = 600;
      if(y <= 0)
          y = 0;
      if(y >= 430)
          y = 430;
  }
  
  public void lifeDraw(Graphics g)
  {
      life.paintIcon(null, g, (int)this.x, (int)this.y);
  }
  
  public Player(double x, double y, Color color) 
    {
        super(x, y, color);
    }

}
