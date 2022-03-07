import bagel.Image;
import bagel.util.Rectangle;
import java.util.Random;

/**
 * Creation of Plastic Pipes class which inherits
 * Pipes class
 */
public class SteelP extends Pipes{

    private Image STEEL = new Image("res/level-1/steelPipe.png");
    private Image FLAME = new Image("res/level-1/flame.png");
    private final int WINDOW_WIDTH = 1024;
    private final double MIN_HEIGHT = 100;
    private final double MAX_HEIGHT = 500;
    private final double GAP = 168;
    private final int RENDER = 30;
    private final int RESET = 49;
    private final int LEEWAY = 10;
    private Random rand = new Random();
    private double [] flamePoint;
    private int frames;

    /**
     * Constructor
     */
    public SteelP() {
        super();

        // point array will have
        // point0 = x-coordinate for both pipes (top left)
        // point1 = y-coordinate of top pipe (top left)
        // point2 = y-coordinate of bottom pipe (top left)

        double start_y = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * rand.nextDouble();
        // Spawning pipes at right border of window
        double point0 = WINDOW_WIDTH;
        double point1 = -(STEEL.getHeight() - start_y);
        double point2 = GAP + start_y;

        setPipes(point0, point1, point2);

        // Initialize for the flames as well
        // flamePoint[0] = y-coordinate of top flame (top left)
        // flamePoint[1] = y-coordinate of bottom flame (top left)
        // Will be using same x coordinate as pipes (point0)
        flamePoint = new double[2];
        flamePoint[0] = start_y - FLAME.getHeight()/2 + LEEWAY;
        flamePoint[1] = start_y + GAP - FLAME.getHeight()/2 - LEEWAY;

        frames = 0;
    }

    /**
     * draw the pipes onto the window (game)
     * @param speed movement speed that pipes move in the game
     * @return int (1) if pipe is no longer in use (out of range)
     *             (0) if pipe is still in window
     */
    @Override
    public int drawPipes(double speed){
        STEEL.drawFromTopLeft(getPoint()[0], getPoint()[1]);
        STEEL.drawFromTopLeft(getPoint()[0], getPoint()[2], getROTATE());

        // Draw flames accordingly
        // First 30 frames (flame) last 20 frames (no flame)
        if(frames < RENDER){
            FLAME.drawFromTopLeft(getPoint()[0], flamePoint[0]);
            FLAME.drawFromTopLeft(getPoint()[0], flamePoint[1], getROTATE());
        }

        // Reset frames for flame to spawn
        if(frames == RESET){
            frames = 0;
        }

        // If pipes are at the end, remove the pipe as no longer in use
        if(getPoint()[0] < 0){
            return 1;
        }

        // pipes move left every frame
        movePipes(speed);
        frames ++;
        return 0;

    }


    /**
     * Gets the rectangle dimensions of Pipes (top and bottom) for comparison
     *      - adds the flame's dimensions accordingly if present or not
     * @return Rectangle[] 2 rectangle objects which are top and bottom rectangles
     */
    @Override
    public Rectangle[] getRectangle(){
        Rectangle[] rec = new Rectangle[2];
        double[] point = getPoint();

        double height = STEEL.getHeight();

        // Adding flame heights if there is flame
        if(frames < RENDER){
            height += FLAME.getHeight() + LEEWAY;
        }

        rec[0] = new Rectangle(point[0], point[1],
                STEEL.getWidth(), height);
        rec[1] = new Rectangle(point[0], point[2] - FLAME.getHeight() - LEEWAY,
                STEEL.getWidth(), height);

        return rec;
    }


}
