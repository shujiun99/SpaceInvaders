/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Player extends Entity{
    
    
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
        
        bounds = new Rectangle((int)x,(int)y,height,width);

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

}
