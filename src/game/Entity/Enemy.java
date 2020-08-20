/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import javax.swing.ImageIcon;

public class Enemy extends Entity {

    private Laser laser;

    public Laser getLaser() {
        return laser;
    }

    public Enemy(int x, int y, int width, int height) {
        initAlien(x, y, width, height);
    }

    private void initAlien(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        laser = new Laser(x, y);

        var EnemyImg = "src/images/enemy.jpg";
        var imageIcon = new ImageIcon(EnemyImg);

        setImage(imageIcon.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }

    public class Laser extends Entity {

        private boolean remove;

        public Laser(int x, int y) {
            initLaser(x, y);
        }

        public boolean isRemove() {
            return remove;
        }

        public void setRemove(boolean remove) {
            this.remove = remove;
        }

        private void initLaser(int x, int y) {
            //disable the laser for the first drawing
            setRemove(true);

            this.x = x;
            this.y = y;

            var bombImg = "src/images/Untitled.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }
    }
}
