import bagel.Image;

/**
 * Creation of Lifebar class which creates the life bar to show
 * the lives remaining during the game
 */
public class Lifebar {

    private final Image FULL = new Image("res/level/fullLife.png");
    private final Image NO = new Image("res/level/noLife.png");
    private final int GAP = 50;
    private final int [] LOCATION = {100, 15};
    private boolean [] lives;
    private int total;


    /**
     * Constructor
     * @param level different lives according to the level inputted
     */
    public Lifebar(int level){
        if(level == 0){
            lives = new boolean[]{true, true, true};
            total = 3;
        }
        else{
            lives = new boolean[]{true, true, true, true, true, true};
            total = 6;
        }
    }

    /**
     * Loses one life
     * @return boolean Return true if no more lives available
     *                 Returns false otherwise
     */
    public boolean loseLife(){
        total --;
        if(total == 0){
            return true;
        }
        lives[total] = false;
        return false;
    }

    /**
     * Draws the life bar onto the game (window)
     */
    public void drawBar(){
        int x_loc = LOCATION[0];
        int y_loc = LOCATION[1];
        for(boolean alive: lives){
            if(alive){
                FULL.drawFromTopLeft(x_loc, y_loc);
            }
            else{
                NO.drawFromTopLeft(x_loc, y_loc);
            }

            x_loc += GAP;
        }
    }
}
