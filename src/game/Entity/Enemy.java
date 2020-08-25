/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Entity;

import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Enemy extends Entity {

    private Laser laser;
    private boolean visible;
    private boolean remove;

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    /**
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    /**
     *
     * @return
     */
    public Laser getLaser() {
        return laser;
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param laser_width
     * @param laser_height
     * @param imgPath
     */
    public Enemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {
        initEnemy(x, y, width, height, laser_width, laser_height, imgPath);
    }

    private void initEnemy(int x, int y, int width, int height, int laser_width, int laser_height, String imgPath) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        visible =true;

        laser = new Laser(x, y, laser_width, laser_height);

        var EnemyImg = imgPath;
        var imageIcon = new ImageIcon(EnemyImg);

        setImage(imageIcon.getImage());
    }

    /**
     *
     * @param direction
     */
    public void move(int direction) {
        this.x += direction;
    }

    /**
     *
     */
    public class Laser extends Entity {

        private boolean remove;

        /**
         *
         * @param x
         * @param y
         * @param width
         * @param height
         */
        public Laser(int x, int y, int width, int height) {
            initLaser(x, y, width, height);
        }

        /**
         *
         * @return
         */
        public boolean isRemove() {
            return remove;
        }

        /**
         *
         * @param remove
         */
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
