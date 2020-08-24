package game.Client;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class LevelMenu {
    public Rectangle beginnerButton = new Rectangle(Game.WIDTH /2 + 100, 150, 150, 50);
    public Rectangle normalButton = new Rectangle(Game.WIDTH /2 + 100, 220, 150, 50);
    public Rectangle intermediateButton = new Rectangle(Game.WIDTH /2 + 100, 290, 150, 50);
    public Rectangle bonusButton = new Rectangle(Game.WIDTH /2 + 100, 360, 150, 50);
    
    public void levelMenu(Graphics g){
        
        Graphics2D g2d = (Graphics2D)g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("Select Level", Game.WIDTH / 2, 100);
        
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Level 1", beginnerButton.x + 25, beginnerButton.y + 36);
        g2d.draw(beginnerButton);
        
        Font fnt2 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt2);
        g.drawString("Level 2", normalButton.x + 25, normalButton.y + 36);
        g2d.draw(normalButton);
        
        Font fnt3 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt3);
        g.drawString("Level 3", intermediateButton.x + 25, intermediateButton.y + 36);
        g2d.draw(intermediateButton);
        
        Font fnt4 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt4);
        g.drawString("Bonus", bonusButton.x + 25, bonusButton.y + 36);
        g2d.draw(bonusButton);
        
        String img = "src/images/menu.png";
        ImageIcon icon = new ImageIcon(img);
        
        g.drawImage(icon.getImage(), 0, 340, 136, 145, null);
    
}
}
