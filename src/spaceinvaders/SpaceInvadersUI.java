package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import spaceinvaders.menus.BulletSelection;
import spaceinvaders.menus.InvaderSelection;
import spaceinvaders.menus.ShooterSelection;
import spaceinvaders.menus.MusicSelection;
import spaceinvaders.menus.ExplosionSelection;

public class SpaceInvadersUI extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;
    public ArrayList<InvaderBox> invaderboxes;
    public ArrayList<Bullet> bullets;
    public Random random;
    public boolean moveLeft, moveRight;
    private final ListenerActions listenerActions;
    public final InvaderSelection invaderSelection;
    public final ShooterSelection shooterSelection;
    public final MusicSelection musicSelection;
    public final BulletSelection bulletSelection;
    public final ExplosionSelection explosionSelection;
    public final ScoreCard scoreCounter;
    private final PaintingActions paintingActions;
    private Image backgroundImage;
    private int shooter_width = 50;
    private int shooter_height = 60;
    private int shooter_X_Coordinate = 275;
    private int currentScore;
    private int lastScore;
    private int cooldownCounter = 0;
    private static final int COOLDOWN_DURATION = 60;
    public boolean shooting = false;
    public Image explosionImage;
    public static SpaceInvadersUI gameInstance;

    // Constructor
    public SpaceInvadersUI() {
        //
        timer = new Timer(20, this); // 20ms delay for smoother animations
        invaderboxes = new ArrayList<>(); // Need to describe what ArrayList<> is.
        bullets = new ArrayList<>();
        random = new Random();
        moveLeft = false;
        moveRight = false;
        listenerActions = new ListenerActions();
        invaderSelection = new InvaderSelection();
        shooterSelection = new ShooterSelection();
        musicSelection = new MusicSelection();
        bulletSelection = new BulletSelection();
        paintingActions = new PaintingActions();
        explosionSelection = new ExplosionSelection();
        scoreCounter = new ScoreCard(); 
        // Set images and music
        shooterSelection.setPresetShooterImage("./resources/ShooterImage4.png");
        invaderSelection.setPresetInvaderImage("./resources/InvaderImage4.png");
        musicSelection.loadPresetMusic("./resources/Music.wav");
        gameInstance = this;

        loadBackgroundImage("./menus/resources/Background.png");
        explosionSelection.setPresetExplosionImage("./resources/ExplosionImage.png");

        bulletSelection.setPresetBulletImage("Bullet3");

        setFocusable(true);
        addKeyListener(this);
        timer.start();
    }

    private void loadBackgroundImage(String imagePath) {
        try {
            backgroundImage = ImageIO.read(SpaceInvadersUI.class.getResource(imagePath));
        } catch (IOException e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }
    }

    public void startNewGame(){
        gameInstance.scoreCounter.resetScore(); 
        gameInstance.invaderboxes.clear();
        gameInstance.bullets.clear();

        gameInstance.moveLeft = false;
        gameInstance.moveRight = false;
        gameInstance.shooting = false;
        
        cooldownCounter = 0;
        currentScore = 0;
        lastScore = 0;
        
        gameInstance.shooter_X_Coordinate = gameInstance.getWidth() / 2 - gameInstance.shooter_width / 2;
        
        gameInstance.getInputMap().clear();
        gameInstance.getActionMap().clear();

        gameInstance.timer.restart();

        repaint();
    }

    @Override
    // Perhaps change this to specifically look for timer event or move all code to
    // ListenerActions and add overloading
    public void actionPerformed(ActionEvent e) {
        listenerActions.updatePositions(this);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        listenerActions.keyPressed(e, this);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        listenerActions.keyReleased(e, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used. Not providing an implementation Violates Integration Segregation
        // Principle
        // Could be used for character keys.
    }

    @Override
    // Let's move these methods into a separate PaintUI class
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            setBackground(Color.BLACK);
        }

        // Draw shooter (rectangle)
        paintingActions.drawShooter(g, this);

        // Draw falling invaderboxes (as images)
        paintingActions.drawInvaders(g, invaderboxes, invaderSelection.getInvaderImage(), this);

        // Draw bullets (bullets)
        paintingActions.drawBullets(g, bullets);

        paintingActions.drawExplosions(g, invaderboxes, explosionSelection.getExplosionImage());

        currentScore = scoreCounter.getScore();
        if (currentScore < lastScore) {
            cooldownCounter = COOLDOWN_DURATION;
        }
        
        if (cooldownCounter > 0) {
            g.setColor(Color.RED);
            cooldownCounter--;
        } else {
            g.setColor(Color.WHITE);
        }

        g.setFont(new Font("Papyrus", Font.BOLD, 20));
        g.drawString("Score: " + currentScore, 20, 30);

        if (cooldownCounter > 0) {
            drawGlowingBorder(g);
        }

        lastScore = currentScore;
    }

    private void drawGlowingBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float alpha = Math.max(0.1f, (float) cooldownCounter / COOLDOWN_DURATION);
        Color glowColor = new Color(255, 0, 0, (int) (alpha * 255));
    
        g2d.setColor(glowColor);
        int thickness = 5;
        g2d.drawRect(thickness / 2, thickness / 2, getWidth() - thickness, getHeight() - thickness);
    }

    public int getShooterWidth() {
        return (shooter_width);
    }

    public int getShooterHeight() {
        return (shooter_height);
    }

    public int getShooter_X_Coordinate() {
        return (shooter_X_Coordinate);
    }

    public void setShooter_X_Coordinate(int shooter_X) {
        shooter_X_Coordinate = shooter_X;
    }

    // These are the characters or objects used in the game. Create a shooter class
    // thing.

    // Inner class representing falling invaderboxes
    public class InvaderBox {
        int x, y, size;
        boolean exploding = false;
        int explosionCounter = 0;
    
        public InvaderBox(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    // Inner class representing bullets (bullets)
    // Look for Java bullet class
    public class Bullet {
        int x, y;

        public int getX() {
            return x;
        }
    
        public int getY() {
            return y;
        }

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y - 35;
        }
    }
}
