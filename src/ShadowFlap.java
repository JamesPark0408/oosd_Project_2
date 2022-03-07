import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 2, 2021
 *
 * Please filling your name below
 * @author: Park Chang Whan
 * StudentID: 1129623
 */
public class ShadowFlap extends AbstractGame {

    // variables listed
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private int level;
    private Level [] levels;

    /**
     * Constructor
     */
    public ShadowFlap() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "Shadow Flap");
        level = 0;
        levels = new Level[2];
        levels[0] = new Level0();
        levels[1] = new Level1();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     * @param input Getting the input from users
     */
    @Override
    public void update(Input input) {
        level = levels[level].play(input);

        // Exit Game
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();

        }
    }

}
