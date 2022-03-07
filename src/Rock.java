import bagel.Image;
import bagel.util.Rectangle;

/**
 * Creation of Rock class which is inherits Weapon class
 */
public class Rock extends Weapon{

    private final int RANGE = 25;
    private final double SHOOT = 5;
    private int total_shot;
    private final Image ROCK = new Image("res/level-1/rock.png");

    /**
     * Constructor
     */
    public Rock(){
        super();
        total_shot = 0;
    }

    /**
     * draw the weapon onto the window (game)
     * @param speed movement speed that weapon move in the game
     * @return int (1) if weapon is no longer in use (out of range)
     *             (0) if weapon is still in window
     */
    @Override
    public int drawWeapon(double speed){
        ROCK.drawFromTopLeft(getPoint()[0], getPoint()[1]);

        // Check if out of window
        if(getPoint()[0] < 0){
            return 1;
        }

        // weapon move left every frame
        moveWeapon(speed);
        return 0;
    }

    /**
     * Gets the rectangle dimensions of the weapon for comparison
     * @return Rectangle Gets the rectangle dimensions of weapon
     */
    @Override
    public Rectangle getRectangle(){
        Rectangle rec = new Rectangle(getPoint()[0], getPoint()[1],
                ROCK.getWidth(), ROCK.getHeight());
        return rec;
    }

    /**
     * Shoots the weapon forward (right) until range ends or collision
     * @return boolean returns state of shoot (if weapon still shooting)
     */
    @Override
    public boolean shoot(){
        ROCK.drawFromTopLeft(getPoint()[0], getPoint()[1]);

        getPoint()[0] += SHOOT;
        total_shot ++;

        // Stop shooting
        if(total_shot > RANGE){
            return false;
        }

        return true;
    }

    /**
     * Draws the weapon onto the window with no changes
     */
    @Override
    public void draw(){
        ROCK.drawFromTopLeft(getPoint()[0], getPoint()[1]);
    }
}
