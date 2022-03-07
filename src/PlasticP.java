import bagel.Image;
import bagel.util.Rectangle;
import java.util.Random;

/**
 * Creation of Plastic Pipes class which inherits
 * Pipes class
 */
public class PlasticP extends Pipes{

    private Image PLASTIC = new Image("res/level/plasticPipe.png");
    private final int WINDOW_WIDTH = 1024;
    private final double MIN_HEIGHT = 100;
    private final double MAX_HEIGHT = 500;
    private int [] starting_y = {100, 300, 500};
    private final double GAP = 168;
    private Random rand = new Random();

    /**
     * Constructor for level 0 (3 types of heights for pipes)
     */
    public PlasticP(){
        super();

        // Get Pipe Type (High,Mid,Low)
        int type = rand.nextInt(3);

        // point array will have
        // point0 = x-coordinate for both pipes (top left)
        // point1 = y-coordinate of top pipe (top left)
        // point2 = y-coordinate of bottom pipe (top left)

        // Spawning pipes at right border of window
        double point0 = WINDOW_WIDTH;
        double point1 = -(PLASTIC.getHeight() - starting_y[type]);
        double point2 = GAP + starting_y[type];

        setPipes(point0, point1, point2);

    }

    /**
     * Constructor for level 1 (many different heights ranging from 100-500 for pipes)
     */
    public PlasticP(int level){
        super();

        // point array will have
        // point0 = x-coordinate for both pipes (top left)
        // point1 = y-coordinate of top pipe (top left)
        // point2 = y-coordinate of bottom pipe (top left)

        double start_y = MIN_HEIGHT + (MAX_HEIGHT-MIN_HEIGHT) * rand.nextDouble();
        // Spawning pipes at right border of window
        double point0 = WINDOW_WIDTH;
        double point1 = -(PLASTIC.getHeight() - start_y);
        double point2 = GAP + start_y;

        setPipes(point0, point1, point2);

    }

    /**
     * draw the pipes onto the window (game)
     * @param speed movement speed that pipes move in the game
     * @return int (1) if pipe is no longer in use (out of range)
     *             (0) if pipe is still in window
     */
    @Override
    public int drawPipes(double speed){
        PLASTIC.drawFromTopLeft(getPoint()[0], getPoint()[1]);
        PLASTIC.drawFromTopLeft(getPoint()[0], getPoint()[2], getROTATE());

        // If pipes are at the end, remove the pipe as no longer in use
        if(getPoint()[0] < 0){
            return 1;
        }

        // pipes move left every frame
        movePipes(speed);
        return 0;
    }

    /**
     * Gets the rectangle dimensions of Pipes (top and bottom) for comparison
     * @return Rectangle[] 2 rectangle objects which are top and bottom rectangles
     */
    @Override
    public Rectangle[] getRectangle(){
        Rectangle[] rec = new Rectangle[2];
        double[] point = getPoint();
        rec[0] = new Rectangle(point[0], point[1],
                PLASTIC.getWidth(), PLASTIC.getHeight());
        rec[1] = new Rectangle(point[0], point[2],
                PLASTIC.getWidth(), PLASTIC.getHeight());

        return rec;
    }
}
