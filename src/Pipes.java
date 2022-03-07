import bagel.DrawOptions;
import bagel.util.Rectangle;

/**
 * Creation of abstract Pipes class which will draw and
 * keep track of both the top and bottom pipes
 * throughout the game
 */
public abstract class Pipes {

    // point array will have
    // point[0] = x-coordinate for both pipes (top left)
    // point[1] = y-coordinate of top pipe (top left)
    // point[2] = y-coordinate of bottom pipe (top left)
    private double [] point;

    private final DrawOptions ROTATE = new DrawOptions().setRotation(Math.PI);
    private boolean passed;


    /**
     * Constructor
     */
    public Pipes() {
        point = new double[3];
        passed = false;
    }

    /**
     * draw the pipes onto the window (game)
     * @param speed movement speed that pipes move in the game
     * @return int (1) if pipe is no longer in use (out of range)
     *             (0) if pipe is still in window
     */
    public abstract int drawPipes(double speed);

    /**
     * Gets the rectangle dimensions of Pipes (top and bottom) for comparison
     * @return Rectangle[] 2 rectangle objects which are top and bottom rectangles
     */
    public abstract Rectangle[] getRectangle();

    /**
     * Getter for 'point' variable
     */
    public double[] getPoint() {
        return point;
    }

    /**
     * Getter for 'ROTATE' variable
     */
    public DrawOptions getROTATE() {
        return ROTATE;
    }

    /**
     * Getter for 'passed' variable
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Setter for 'passed' variable
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    /**
     * Setting coordinates for pipes
     * @param point0 x coordinate for both pipes (top left)
     * @param point1 y coordinate for top pipe (top left)
     * @param point2 y coordinate for btm pipe (top left)
     */
    public void setPipes(double point0, double point1, double point2){
        point[0] = point0;
        point[1] = point1;
        point[2] = point2;
    }

    /**
     * Moving pipes by certain speed according to timescale
     * @param speed movement speed of pipes
     */
    public void movePipes(double speed){
        point[0] -= speed;
    }
}
