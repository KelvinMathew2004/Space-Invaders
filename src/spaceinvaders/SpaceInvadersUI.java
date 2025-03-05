package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import spaceinvaders.content.BulletSelection;
import spaceinvaders.content.ExplosionSelection;
import spaceinvaders.content.InvaderSelection;
import spaceinvaders.content.MusicSelection;
import spaceinvaders.content.ShooterSelection;

public class SpaceInvadersUI extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;
    public ArrayList<InvaderBox> invaderboxes;
    public ArrayList<Bullet> bullets;
    public Random random;
    public boolean moveLeft, moveRight;
    private final ListenerActions listenerActions;
    public final MusicSelection musicSelection;
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
    private static boolean gameOver = false;

    // Constructor
    public SpaceInvadersUI() {
        //
        timer = new Timer(20, this); // 20ms delay for smoother animations
        invaderboxes = new ArrayList<>(); // Need to describe what ArrayList<> is.
        bullets = new ArrayList<>();
        random = new Random();
        moveLeft = false;
        moveRight = false;
        gameInstance = this;
        listenerActions = new ListenerActions();
        paintingActions = new PaintingActions();
        musicSelection = new MusicSelection();
        scoreCounter = new ScoreCard(gameInstance); 

        // Set images and music
        ShooterSelection.setPresetShooterImage(gameInstance, "./resources/ShooterImage4.png");
        InvaderSelection.setPresetInvaderImage(gameInstance, "./resources/InvaderImage4.png");
        ExplosionSelection.setPresetExplosionImage("./resources/ExplosionImage.png");
        BulletSelection.setPresetBulletImage(gameInstance, "Bullet3");
        loadBackgroundImage("./content/resources/Background.png");
        musicSelection.loadPresetMusic("./resources/Music.wav");


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
        gameInstance.setGameOver(false);

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

    public void pauseGame() {
        timer.stop();
        repaint();
    }

    public void resumeGame() {
        timer.start();
        repaint();
    }

    public void stopTimer() {
        timer.stop();
        repaint();
    }

    public void setGameOver(boolean isOver) {
        gameOver = isOver;
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
        paintingActions.drawInvaders(g, invaderboxes, InvaderSelection.getInvaderImage(), this);

        // Draw bullets (bullets)
        paintingActions.drawBullets(gameInstance, g, bullets);

        paintingActions.drawExplosions(g, invaderboxes, ExplosionSelection.getExplosionImage());

        if (gameOver) {
            g.setFont(new Font("Papyrus", Font.BOLD, 20));
            g.drawString("Score: " + lastScore, 20, 30);
            return;
        }
        
        if (!gameOver && !timer.isRunning()) {
            g.setFont(new Font("Papyrus", Font.BOLD, 30));
            g.setColor(Color.YELLOW);
            g.drawString("Game Paused", getWidth() / 2 - 80, getHeight() / 2);
        } 
        
        if (!gameOver && timer.isRunning()) {
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
