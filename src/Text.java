import bagel.*;

/**
 * Creation of Text class which will draw and
 * keep track of all texts that are needed throughout the game
 */
public class Text {

    private Font font = new Font("res/font/slkscr.ttf", 48);
    private final String BEGINNING_TEXT = "PRESS SPACE TO START";
    private final String SHOOT = "PRESS 'S' TO SHOOT";
    private final String LOSE = "GAME OVER";
    private final String SCORE = "FINAL SCORE: ";
    private final String WIN2 = "CONGRATULATIONS!";
    private final String WIN1 = "LEVEL-UP!";
    private final int TEXT_HEIGHT = 48;
    private final int SHOOT_MESSAGE_Y = 68;

    /**
     * Empty Constructor for clarity
     */
    public Text(){
    }

    /**
     * Draws the necessary text for the start of game (Level 1)
     */
    public void start1(){
        font.drawString(BEGINNING_TEXT,
                Window.getWidth()/2.0 - font.getWidth(BEGINNING_TEXT)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2);
        font.drawString(SHOOT,
                Window.getWidth()/2.0 - font.getWidth(SHOOT)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2 + SHOOT_MESSAGE_Y);
    }

    /**
     * Draws the necessary text for the start of game (Level 0)
     */
    public void start(){
        font.drawString(BEGINNING_TEXT,
                Window.getWidth()/2.0 - font.getWidth(BEGINNING_TEXT)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2);
    }

    /**
     * Draws the score counter
     * @param score total score in the game
     */
    public void scoring(int score){
        font.drawString("SCORE: " + score, 100, 100);
    }

    /**
     * Draws the losing message on screen with correct score
     * @param score total score in the game
     */
    public void lose(int score){
        font.drawString(LOSE,
                Window.getWidth()/2.0 - font.getWidth(LOSE)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2);
        font.drawString(SCORE + score,
                Window.getWidth()/2.0 - font.getWidth(SCORE + score)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2 + 75);
    }

    /**
     * Draws the winning message on screen with correct score (Level 1)
     * @param score total score in the game
     */
    public void win2(int score){
        font.drawString(WIN2,
                Window.getWidth()/2.0 - font.getWidth(WIN2)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2);
        font.drawString(SCORE + score,
                Window.getWidth()/2.0 - font.getWidth(SCORE + score)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2 + 75);
    }

    /**
     * Draws the winning message on screen with correct score (Level 0)
     * @param score total score in the game
     */
    public void win1(int score){
        font.drawString(WIN1,
                Window.getWidth()/2.0 - font.getWidth(WIN1)/2.0,
                Window.getHeight()/2.0 - TEXT_HEIGHT/2);
    }
}
