import bagel.util.Rectangle;
import java.util.Random;

/**
 * Creation of Weapon class which tracks the weapon's
 * location and actions
 */
public abstract class Weapon {

    // point array will have
    // point[0] = x-coordinate of weapon (top left)
    // point[1] = y-coordinate of weapon (top left)
    private double [] point;
    private final double MIN_HEIGHT = 100;
    private final double MAX_HEIGHT = 500;
    private final int WINDOW_WIDTH = 1024;
    private Random rand = new Random();

    /**
     * Constructor
     */
    public Weapon(){
        point = new double[2];
        double start_y = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * rand.nextDouble();
        point[0] = WINDOW_WIDTH;
        point[1] = start_y;
    }

    /**
     * draw the weapon onto the window (game)
     * @param speed movement speed that weapon move in the game
     * @return int (1) if weapon is no longer in use (out of range)
     *             (0) if weapon is still in window
     */
    public abstract int drawWeapon(double speed);

    /**
     * Gets the rectangle dimensions of the weapon for comparison
     * @return Rectangle Gets the rectangle dimensions of weapon
     */
    public abstract Rectangle getRectangle();

    /**
     * Shoots the weapon forward (right) until range ends or collision
     * @return boolean returns state of shoot (if weapon still shooting)
     */
    public abstract boolean shoot();

    /**
     * Gets the right border coordinates of the bird to stick the weapon
     * to bird's mouth (acting as if the bird is holding it after collection)
     * @param x x coordinate of where bird's beak is at
     * @param y y coordinate of where bird's beak is at
     */
    public void followBird(double x, double y){
        point[0] = x;
        point[1] = y;
    }

    /**
     * Getter for 'point' variable
     */
    public double[] getPoint() {
        return point;
    }

    /**
     * Moves weapon (right to left) according to speed from timescale
     * @param speed
     */
    public void moveWeapon(double speed){
        point[0] -= speed;
    }

    /**
     * Draws the weapon onto the window with no changes
     */
    public abstract void draw();
}
