import bagel.Image;
import bagel.util.Rectangle;

/**
 * Creation of Bird class which will draw and
 * keep track of the bird throughout the game
 */
public class Bird {

    // point array will have
    // point[0] = x-coordinate (top left)
    // point[1] = y-coordinate (top left)
    private double [] point;

    private Image flappyup;
    private Image flappydown;

    private int flap_count;
    private double move;
    private final double ACCELERATE = 0.4;

    // For reference: 55x39 size
    private final double INITIAL_X = 200.0 - 55.0/2;
    private final double INITIAL_Y = 350.0 - 39.0/2;

    /**
     * Constructor
     */
    public Bird(int level){
        flappyup = new Image("res/level-" + level + "/birdWingUp.png");
        flappydown = new Image("res/level-" + level + "/birdWingDown.png");
        point = new double [] {INITIAL_X, INITIAL_Y};
        flap_count = 0;
        move = 0.0;
    }

    /**
     * Draws bird on the Window
     *  - flaps up and down every 10 frames
     *  - does not change x position
     *  - moves down by gravity with acceleration of 0.4 pixels (max 10 pixels)
     */
    public void drawBird(){

        // Every 10 frames the bird flaps
        if(flap_count == 10){
            flappyup.drawFromTopLeft(point[0], point[1]);
            flap_count = -1;
        }
        else{
            flappydown.drawFromTopLeft(point[0], point[1]);
        }

        point[1] += move;

        //increase speed of fall due to gravity acceleration
        if(move < 10.0){
            move += ACCELERATE;
        }

        flap_count++;
    }

    /**
     * Bird flies(moves up)
     *  - flies up at initial 6 pixels per frame
     *      which continuously moves down at gravity of 0.4 pixels
     */
    public void fly(){

        // bird flies up (in this case y coordinate decreases)
        move = -6.0;

        point[1] += move;
    }

    /**
     * Gets the Rectangle object for bird for detections
     * @return Rectangle Gets the rectangle dimensions of the bird
     */
    public Rectangle getRectangle(){
        Rectangle rec = new Rectangle(point[0], point[1],
                flappydown.getWidth(), flappydown.getHeight());
        return rec;
    }

    /**
     * If bird goes out of bounds, bird respawns at initial starting location
     * with initial velocity
     */
    public void respawn(){
        point[0] = INITIAL_X;
        point[1] = INITIAL_Y;
        move = 0.0;
    }

    /**
     * Getter for location of right border of bird
     * @return double[] Gets the x and y coordinates of the location
     */
    public double[] getPoint() {
        double[] rightpoint = {point[0]+flappydown.getWidth(), point[1]};
        return rightpoint;
    }
}
