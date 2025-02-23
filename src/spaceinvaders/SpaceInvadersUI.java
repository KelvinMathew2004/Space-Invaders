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
    private final PaintingActions paintingActions;
    private Image backgroundImage;
    private int shooter_width = 50;
    private int shooter_height = 60;
    private int shooter_X_Coordinate = 200;

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

        // Set images and music
        shooterSelection.setPresetShooterImage("./resources/ShooterImage4.png");
        invaderSelection.setPresetInvaderImage("./resources/InvaderImage4.png");
        musicSelection.loadPresetMusic("/Users/kelvinmathew/Documents/CS3354/project-2/src/spaceinvaders/menus/resources/Music.wav");

        loadBackgroundImage("./menus/resources/Background.png");

        bulletSelection.setPresetBulletImage("./resources/Bullet.png");

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
