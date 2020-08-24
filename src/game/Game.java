package game;

import game.Entity.Enemy;
import game.ADT.ArrayListWithIterator;
import game.ADT.ArrListWithIteratorInterface;
import game.ADT.ArrayQueue;
import game.ADT.ArraySort;
import game.ADT.QueueInterface;
import game.ADT.SortInterface;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import game.Entity.Player;
import game.Entity.Shot;
import game.Entity.Weapon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Game extends Canvas implements Runnable {

    //gameboard width
    public static final int WIDTH = 320;
    //gameboard height
    public static final int HEIGHT = WIDTH / 12 * 9;
    //scaling used in adjusting gameboard height
    public static final int SCALE = 2;
    //default running status
    private boolean running = false;
    //declare thread
    private Thread thread;
    //initialize BufferedImage
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    //space to the left and right border
    int BORDER_RIGHT = 50;
    int BORDER_LEFT = 5;
    //move down range
    int GO_DOWN = 30;
    //enemy height and width
    final int ENEMY_HEIGHT = 30;
    final int ENEMY_WIDTH = 40;
    //enemy default position
    final int ENEMY_INIT_X = 0;
    final int ENEMY_INIT_Y = 35;
    //timer speed delay <- affect enemy speed?
    final int DELAY = 15;
    //enemy default moving speed and direction, positive is right, negative is left
    private int direction = 2;
    //random number match trigger then enemy shoot
    int trigger;
    //laser width and height
    final int LASER_HEIGHT = 10;
    final int LASER_WIDTH = 5;
    //status
    private boolean start = true;
    //buffTime
    private Instant buffUsingTimeStart;
    private boolean startBuff = false;
    //bullet
    private final int bulletSpeed = 2;
    private int BulletTemSpeed = bulletSpeed;
    //declare menu
    private Menu menu;
    //declare level menu
    private LevelMenu lvlmenu;
    //player shooting status
    private boolean isShooting = false;
    //counter for enemy killed
    private int enemyKilled = 0;
    //initialize buff
    public boolean buffIsUsing = false;
    //random number
    private Random r = new Random();
    //declare weapon
    private Weapon usingWeapon;
    //declare timestart
    private Instant timestart;
    //declare player
    private Player player;
    private Player playerShip;
    private ArrayList<Player> ship = new ArrayList();
    //declare enemy
    private ArrListWithIteratorInterface<Enemy> enemyList;
    private String[] enemyPath = {"src/images/enemy.jpg", "src/images/enemyCyan.jpg", "src/images/enemyGreen.jpg",
        "src/images/enemyPurple.jpg", "src/images/enemyRed.jpg"};

    //declare controller
    public static Clip clip;
    
    public SortInterface<Integer> scoreboard = new ArraySort<Integer>();
    public SortInterface<Integer> enekill = new ArraySort<Integer>();
    public LinkedList<Shot> es = new LinkedList<Shot>();
    public ArrayList<Weapon> weapon = new ArrayList<Weapon>();
    public QueueInterface<Weapon> waitingW = new ArrayQueue<Weapon>();
    
    Shot tempShot;
    Weapon tempWeapon;
    int num = 0;
    int playerno = 0;
    int numberLives = 3;
    int score = 0;
    int level = 1;
    int bonus = 0;

    public static enum STATE {
        MENU,
        GAME,
        LVLMENU
    };

    public static STATE state = STATE.MENU;

    public void init() {
        player = new Player();
        this.requestFocus();
        addKeyListener(new TAdapter(this));
        addMouseListener(new MouseInput(this));
        menu = new Menu();
        lvlmenu = new LevelMenu();
        /*
        bonus stage is level -2, t
         */
        if (level == -2) {
            enemyInit(5, 4);
        } else {
            enemyInit(level, 2 + level);
        }
    }

    private void enemyInit(int row, int column) {
        enemyList = new ArrayListWithIterator<>();
        if (level == -2) {
            //row
            for (int i = 0; i < row; i++) {
                //column
                for (int j = 0; j < column; j++) {
                    var enemy = new Enemy(ENEMY_INIT_X + 70 * j,
                            ENEMY_INIT_Y + 60 * i, ENEMY_WIDTH, ENEMY_HEIGHT, LASER_WIDTH, LASER_HEIGHT, enemyPath[i]);
                    enemyList.add(enemy);
                }
            }

        } else {
            //row
            for (int i = 0; i < row; i++) {
                //column
                for (int j = 0; j < column; j++) {
                    var enemy = new Enemy(ENEMY_INIT_X + 70 * j,
                            ENEMY_INIT_Y + 60 * i, ENEMY_WIDTH, ENEMY_HEIGHT, LASER_WIDTH, LASER_HEIGHT, enemyPath[0]);
                    enemyList.add(enemy);
                }
            }
        }

    }

    private static void PlaySound(String song) {

       if(song.equals("menuSong")){
            try{
                File musicPath = new File("src/sounds/Menu.wav");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }catch(Exception ex){
                
            }
            
        }
        
        if(song.equals("gameSong")){
            try {
                File musicPath = new File("src/sounds/Game.wav");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f); // Reduce volume by 25 decibels to make sure it doesn't hurt our ears!
            

            } catch (Exception ex) {

            }
        }

    }

    private synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(1);
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        if (state == STATE.GAME) {
            player.render(g);
            
             for(int i = 0; i<es.size();i++){
                 tempShot = es.get(i);
                 
                 tempShot.render(g);
             }
             
             for(int i = 0; i<weapon.size();i++){
                 tempWeapon = weapon.get(i);
                 tempWeapon.render(g);
                 
             }
            drawEnemies(g);
            drawLaser(g);

            //display player lives
            /*g.setColor(Color.WHITE);
            g.drawString("Lives: ", 11, 20);
            for (int i = 0; i < ship.size(); i++) {
                ship.get(i).lifeDraw(g);
            }*/
                        //display player score
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 420, 20);

            //makes a string that says "+100" on enemy hit
            //show level
            g.setColor(Color.WHITE);
            g.drawString("Level " + level, 590, 20);
            
            g.setColor(Color.WHITE);
            g.drawString("Enemy Kill: " + enemyKilled, 180, 20);
            
            g.setColor(Color.WHITE);
            g.drawString("Player " + playerno, 20, 20);

        } else if (state == STATE.MENU) {
            menu.render(g);
        } else if (state == STATE.LVLMENU) {
            lvlmenu.levelMenu(g);
        }

        g.dispose();
        bs.show();
    }

    private void drawEnemies(Graphics g) {
        var iterator = enemyList.getIterator();

        iterator.forEachRemaining(Enemy -> {
            g.drawImage(Enemy.getImage(), (int) Enemy.getX(), (int) Enemy.getY(), ENEMY_WIDTH, ENEMY_HEIGHT, this);
        });
    }

    private void RandomWeapon() {
        double x = r.nextInt(Game.WIDTH * Game.SCALE);
        double y = r.nextInt(Game.WIDTH * Game.SCALE);
        Instant time = Instant.now();
        weapon.add(new Weapon(x, y, time));
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame("Space Invaders");
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    @Override
    public void run() {
        init();
        int fps = 60;  //frame per second 
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        int updates = 0;
        long lastTime = System.nanoTime();

        if (state != STATE.GAME) {
            render();
            PlaySound("menuSong");
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
                if (state == STATE.GAME) {

                    update();
                }
            }

            if (state == STATE.GAME) {

                if (start) {
                    timestart = Instant.now();
                    start = false;
                }
                if (!start) {
                    Instant stopt = Instant.now();
                    Duration tt = Duration.between(timestart, stopt);
                    if (tt.getSeconds() == 6) {
                        System.out.println("time to create");
                        RandomWeapon();
                        start = true;
                    }
                }

                if (!weapon.isEmpty()) {
                    for (int i = 0; i < weapon.size(); i++) {
                        Instant endTime = Instant.now();
                        Duration interval = Duration.between(weapon.get(i).getStartTime(), endTime);
                        if (interval.getSeconds() == 5) {
                            weapon.remove((Weapon) weapon.get(i));
                        }
                    }
                }
            }
        }

        stop();
    }

    private void tick() {
        if (state == STATE.GAME) {
            for(int i = 0; i<es.size();i++){
                 tempShot = es.get(i);
                 
                 if(tempShot.getY() < 0)
                     es.remove(tempShot);
                 tempShot.tick();
             }

            for (int column = 0; column < numberLives; column++) {
                playerShip = new Player(48 + (column * 20), 10, Color.WHITE);
                ship.add(playerShip);
            }

            if (Collision(player, weapon)) {
                waitingW.enqueue(new Weapon());
                System.out.println("WeaponAdd");
            }
            Collision(player, enemyList);
            //System.out.println("debug");
            //stop();

            Collision(es, weapon);

            Collision(enemyList, es);

            if (buffIsUsing) {
                Instant endBuffTime = Instant.now();
                Duration interval = Duration.between(usingWeapon.getStartTime(), endBuffTime);
                if (interval.getSeconds() == 6) {
                    BulletTemSpeed = bulletSpeed;
                    buffIsUsing = false;
                    System.out.println("Stop Buff");
                    usingWeapon = null;
                }
            }
        }
    }

    private void update() {

        if (enemyList.isEmpty() && level != -2) {
            ship.clear();
            level += 1;
            run();
        } else if(enemyList.isEmpty() && level == -2){
            ship.clear();
            bonus++;
        }

        if (level == 4 || bonus == 1) {
            JOptionPane.showMessageDialog(null,"You win the game with " + score + " points!!!  You WON!!!!!");
                    if(num > 4){
                       scoreboard.clear();
                       enekill.clear();
                       num = 0;
                    }
                     
                    scoreboard.add(score);
                    enekill.add(enemyKilled);
                    num++;
                
                    enemyList.clear();
                    enemyKilled = 0;
                    score = 0;
                    if (level == 4) {
                        level = 1;
                    } else {
                        level = -2;
                        bonus--;
                    }
                    Game.state = Game.STATE.MENU;
                    clip.stop();
                    run();
            
        }

        var iterator = enemyList.getIterator();
        while (iterator.hasNext()) {

            double x = iterator.next().getX();
            //System.out.println(x);
            //when enemy reach right border it change moving direction and move down
            if (level == 1 && x >= WIDTH * SCALE - BORDER_RIGHT && direction != -2) {

                direction = -2;

                var iterator2 = enemyList.getIterator();

                while (iterator2.hasNext()) {

                    Enemy enemy = iterator2.next();
                    enemy.setY(enemy.getY() + GO_DOWN);
                }
            } else if (level == 2 && x >= WIDTH * SCALE - BORDER_RIGHT && direction != -3) {
                direction = -3;

                var iterator4 = enemyList.getIterator();

                while (iterator4.hasNext()) {

                    Enemy enemy3 = iterator4.next();
                    enemy3.setY(enemy3.getY() + GO_DOWN);
                }
            } else if (level == 3 && x >= WIDTH * SCALE - BORDER_RIGHT && direction != -4) {
                direction = -4;

                var iterator6 = enemyList.getIterator();

                while (iterator6.hasNext()) {

                    Enemy enemy5 = iterator6.next();
                    enemy5.setY(enemy5.getY() + GO_DOWN);
                }
            } else if (level == -2 && x >= WIDTH * SCALE - BORDER_RIGHT && direction != -3) {
                direction = -3;
            }
            //when enemy reach left border it change moving direction and move down
            if (level == 1 && x <= BORDER_LEFT && direction != 2) {

                direction = 2;

                var iterator3 = enemyList.getIterator();

                while (iterator3.hasNext()) {

                    Enemy enemy2 = iterator3.next();
                    enemy2.setY(enemy2.getY() + GO_DOWN);
                }
            } else if (level == 2 && x <= BORDER_LEFT && direction != 3) {
                direction = 3;

                var iterator5 = enemyList.getIterator();

                while (iterator5.hasNext()) {

                    Enemy enemy4 = iterator5.next();
                    enemy4.setY(enemy4.getY() + GO_DOWN);

                }
            } else if (level == 3 && x <= BORDER_LEFT && direction != 4) {
                direction = 4;

                var iterator7 = enemyList.getIterator();

                while (iterator7.hasNext()) {

                    Enemy enemy6 = iterator7.next();
                    enemy6.setY(enemy6.getY() + GO_DOWN);

                }
            } else if (level == -2 && x <= BORDER_LEFT && direction != 3) {
                direction = 3;
            }
        }
        var it = enemyList.getIterator();
        //enemy movement
        while (it.hasNext()) {

            Enemy enemy = it.next();

            if (enemy.isVisible()) {
                enemy.move(direction);
            }
        }

        // lasers
        var randomNumber = new Random();

        var iterator3 = enemyList.getIterator();

        iterator3.forEachRemaining(Enemy -> {
            // 0-30
            int rand = randomNumber.nextInt(30);

            Enemy.Laser laser = Enemy.getLaser();
            //System.out.println("rand :"+ rand);
            //System.out.println("trigger :"+trigger);
            //System.out.println("isVisible :"+Enemy.isVisible());
            //System.out.println("isRemove :"+laser.isRemove());

            //when random number match trigger number, enemy shoot
            if (rand == trigger && Enemy.isVisible() && laser.isRemove()) {

                laser.setRemove(false);
                laser.setX(Enemy.getX());
                laser.setY(Enemy.getY());
            }
            //when laser is outside of the gameboard, remove laser
            if (!laser.isRemove()) {
                //laser moves 1px 
                laser.setY(laser.getY() + 1);

                if (laser.getY() >= HEIGHT * SCALE) {

                    laser.setRemove(true);
                }
            }
        });

    }

    private class TAdapter extends KeyAdapter {

        private Game game;

        public TAdapter(Game game) {
            this.game = game;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            player.tick();

            switch (key) {
                case KeyEvent.VK_RIGHT:
                    player.setDx(0);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setDx(0);
                    break;
                case KeyEvent.VK_DOWN:
                    player.setDy(0);
                    break;
                case KeyEvent.VK_UP:
                    player.setDy(0);
                    break;
                case KeyEvent.VK_SPACE:
                    isShooting = false;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            player.tick();

            if (state == STATE.GAME) {
                switch (key) {
                    case KeyEvent.VK_RIGHT:
                        player.setDx(5);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setDx(-5);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.setDy(5);
                        break;
                    case KeyEvent.VK_UP:
                        player.setDy(-5);
                        break;
                    default:
                        break;
                }
                if (key == KeyEvent.VK_SPACE && !isShooting) {
                    isShooting = true;
                    es.add(new Shot(player.getX(), player.getY(), BulletTemSpeed));
                } else if (key == KeyEvent.VK_Z && !buffIsUsing) {
                    if (!waitingW.isEmpty()) {
                        System.out.println(waitingW.size() + 1);
                        usingWeapon = waitingW.dequeue();
                        System.out.println(waitingW.size() + 1);
                        startBuff();
                    }
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            super.keyTyped(e); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class MouseInput implements MouseListener {

        private Game game;

        public MouseInput(Game game) {
            this.game = game;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int mx = e.getX();
            int my = e.getY();

            if (Game.state == Game.STATE.MENU) {
                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 120 && my <= 170) {
                        Game.state = Game.state.LVLMENU;
                        displayLevel();
                        playerno++;
                    }
                }

                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 200 && my <= 250) {
                        displayScore();
                    }
                }

                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 280 && my <= 320) {
                        displayHelp();
                    }
                }

                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 350 && my <= 400) {
                        System.exit(0);
                    }
                }
                
            } else if (Game.state == Game.state.LVLMENU) {
                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 150 && my <= 200) {
                        Game.state = Game.state.GAME;
                        clip.stop();
                        level = 1;
                        PlaySound("gameSong");
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 220 && my <= 270) {
                        Game.state = Game.state.GAME;
                        clip.stop();
                        level = 2;
                        enemyInit(level, 2 + level);
                        PlaySound("gameSong");
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }

                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 290 && my <= 340) {
                        Game.state = Game.state.GAME;
                        clip.stop();
                        level = 3;
                        enemyInit(level, 2 + level);
                        PlaySound("gameSong");
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
                if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
                    if (my >= 360 && my <= 410) {
                        Game.state = Game.state.GAME;
                        clip.stop();
                        level = -2;
                        enemyInit(5, 4);
                        PlaySound("gameSong");
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    public boolean Collision(Player p, ArrayList<Weapon> w) {
        for (int i = 0; i < w.size(); i++) {
            if (p.getBounds().intersects(w.get(i).getBounds())) {
                System.out.println("Weapon Detected");
                weapon.remove(weapon.get(i));
                return true;
            }
        }
        return false;
    }

    public boolean Collision(LinkedList<Shot> es, ArrayList<Weapon> w) {

        for (int i = 0; i < w.size(); i++) {
            for (int j = 0; j < es.size(); j++) {
                if (es.get(j).getBounds().intersects(w.get(i).getBounds())) {
                    weapon.remove(weapon.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Collision(Player p, ArrListWithIteratorInterface<Enemy> enemyList) {
        for (int i = 0; i < enemyList.getLength(); i++) {
            //player touch with enemy or enemy inner class laser, return true
            if (p.getBounds().intersects(enemyList.getEntry(i).getBounds())
                    || p.getBounds().intersects(enemyList.getEntry(i).getLaser().getBounds())) {

                /*int index = ship.size() - 1;
                ship.remove(index);
            }
            else if(ship.isEmpty()){*/
                JOptionPane.showMessageDialog(null,"You lost the game with " + score + " points");
                    if(num > 4){
                       scoreboard.clear();
                       enekill.clear();
                       num = 0;
                    }
                     
                    scoreboard.add(score);
                    enekill.add(enemyKilled);
                    num++;
                
                    enemyList.clear();
                    enemyKilled = 0;
                    score = 0;
                    level = 1;
                    Game.state = Game.STATE.MENU;
                    clip.stop();
                    run();
                
                return true;
                //System.out.println("player bound:" + p.getBounds());

                //System.out.println("laser bound:" + enemyList.getEntry(i).getLaser().getBounds());
                //System.out.println("enemy intersect:" + p.getBounds().intersects(enemyList.getEntry(i).getBounds()));
                //System.out.println("laser intersect:" + p.getBounds().intersects(enemyList.getEntry(i).getLaser().getBounds()));
                //System.out.println(i);
            }
        }

        return false;
    }

    public boolean Collision(ArrListWithIteratorInterface<Enemy> enemyList, LinkedList<Shot> es) {
        //when there's at least 1 shot on the gameboard
        if (!es.isEmpty()) {
            for (int i = 0; i < enemyList.getLength(); i++) {

                for (int j = 0; j < es.size(); j++) {
                    //if enemy touch bullet, remove both
                    if (enemyList.getEntry(i).getBounds().intersects(es.get(j).getBounds())) {
                        enemyKilled++;
                        score += 100;
                        enemyList.remove(i);
                        es.remove(j);
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public void startBuff() {
        System.out.println("Start Buff");
        buffUsingTimeStart = Instant.now();
        usingWeapon.setStartTime(buffUsingTimeStart);
        BulletTemSpeed = usingWeapon.getSpeed();
        buffIsUsing = true;
    }

    public void displayLevel() {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame("Space Invaders");
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    public void displayHelp() {
        JFrame helpFrame = new JFrame();

        String img = "src/images/help.jpg";
        ImageIcon icon = new ImageIcon(img);

        Image img1 = icon.getImage();
        Image newimg = img1.getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        JLabel lable = new JLabel(newIcon);

        helpFrame.add(lable);
        helpFrame.setSize(1000, 600);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setVisible(true);

    }

    private void drawLaser(Graphics g) {
        var iterator = enemyList.getIterator();

        iterator.forEachRemaining(Enemy -> {

            Enemy.Laser laser = Enemy.getLaser();

            if (!laser.isRemove()) {

                g.drawImage(laser.getImage(), (int) laser.getX(), (int) laser.getY(), LASER_WIDTH, LASER_HEIGHT, this);
                //System.out.println(laser.getBounds());
            }
        });
    }

        private void displayScore() {
        JTextArea jtScore = new JTextArea();
    
        String str = String.format("%16s\n\n", "ScoreBoard");
        //str += String.format("%30s %24s\n\n", "Score","Enemy Kill" );
        
        str += String.format("Score  \n" + scoreboard + "\n");
        str += String.format("Enemy Kill  \n" + enekill);
        Font font = new Font("Arial", Font.BOLD, 20);
        jtScore.setText(str);
        jtScore.setEditable(false);
        jtScore.setFont(font);
        
        JFrame jframe = new JFrame();
        jframe.add(jtScore);

        jframe.setSize(200, 450);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe.setVisible(true);
        
    }
        
    public class Menu {
        
        public Rectangle lvlButton = new Rectangle(Game.WIDTH / 2 + 120, 120, 100, 50);
        public Rectangle scoreButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 100, 50);
        public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 280, 100, 50);
        public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
    
        public void render(Graphics g){
        
            Graphics2D g2d = (Graphics2D)g;
        
            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.red);
            g.drawString("Space Invaders", Game.WIDTH / 2, 90);
        
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt2);
            g.drawString("Level", lvlButton.x + 12, lvlButton.y + 36);
            g2d.draw(lvlButton);
        
            Font fnt3 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt3);
            g.drawString("Score", scoreButton.x + 9, scoreButton.y + 35);
            g2d.draw(scoreButton);
        
            Font fnt4 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt4);
            g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
            g2d.draw(helpButton);
        
            Font fnt5 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt5);
            g.drawString("Exit", exitButton.x + 21, exitButton.y + 35);
            g2d.draw(exitButton);
        
        
            String img = "src/images/menu.png";
            ImageIcon icon = new ImageIcon(img);
        
            g.drawImage(icon.getImage(), 0, 340, 136, 145, null);
        
        }
    }
    
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
}
