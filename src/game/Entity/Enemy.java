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

    public Enemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {
        initEnemy(x, y, width, height, laser_width, laser_height, imgPath);
    }

    private void initEnemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        laser = new Laser(x, y, laser_width, laser_height);

        var EnemyImg = imgPath;
        var imageIcon = new ImageIcon(EnemyImg);

        setImage(imageIcon.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }

    public class Laser extends Entity {

        private boolean remove;

        public Laser(int x, int y, int width, int height) {
            initLaser(x, y, width, height);
        }

        public boolean isRemove() {
            return remove;
        }

        public void setRemove(boolean remove) {
            this.remove = remove;
        }

        private void initLaser(int x, int y, int width, int height) {
            //disable the laser for the first drawing
            setRemove(true);

            this.x = x;
            this.y = y;
            this.width=width;
            this.height=height;

            var LaserImg = "src/images/laser.png";
            var ii = new ImageIcon(LaserImg);
            setImage(ii.getImage());
        }
    }
}
