import bagel.Input;
import bagel.util.Rectangle;
import java.util.ArrayList;


/**
 * Abstract class for levels 0 and 1 to inherit from
 */
public abstract class Level {

    private final int WINDOW_HEIGHT = 768;
    private int to_win;
    // Pipe spawn frames according to timescale with timescale 1 being 100
    private final int [] PIPE_SPAWN = {100,67,44,30,20};
    // Pipe speed according to timescale with timescale 1 being 5
    private final double [] PIPE_SPEED = {5, 7.5, 11.25, 16.875, 25.3125};

    private boolean start;
    private boolean end;
    private boolean win;
    private int score;
    private int timescale;
    private Text text;
    private int frame;

    private Bird bird;
    private Lifebar bar;
    private ArrayList<Pipes> pipes;


    /**
     * Constructor
     */
    public Level(int level){
        start = true;
        end = false;
        win = false;
        score = 0;
        timescale = 1;
        text = new Text();
        bird = new Bird(level);
        bar = new Lifebar(level);
        pipes = new ArrayList<Pipes>();
        if(level == 0){
            to_win = 10;
        }
        else{
            to_win = 30;
        }

    }

    /**
     * Getter for 'start' variable
     */
    public boolean isStart() {
        return start;
    }

    /**
     * Getter for 'end' variable
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * Getter for 'win' variable
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Getter for 'score' variable
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for 'text' variable
     */
    public Text getText() {
        return text;
    }

    /**
     * Getter for 'bird' variable
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Getter for 'bar' variable
     */
    public Lifebar getBar() {
        return bar;
    }

    /**
     * Getter for speed (according to current timescale)
     */
    public double getSpeed(){
        return PIPE_SPEED[timescale-1];
    }

    /**
     * Getter for 'pipes' variable
     */
    public ArrayList<Pipes> getPipes() {
        return pipes;
    }

    /**
     * Setter for 'start' variable
     * @param start boolean for if the game is in start screen or not
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * Increase timescale by 1 (Max is 5)
     */
    public void increaseTimescale(){
        if(timescale<5){
            timescale ++;
        }
    }

    /**
     * Decrease timescale by 1 (Min is 1)
     */
    public void decreaseTimescale(){
        if(timescale>0){
            timescale --;
        }
    }

    /**
     * Increase score by 1
     */
    public void increaseScore(){
        score++;
    }

    /**
     * Checks whether we need to add another Pipe to the window or not
     */
    public boolean addPipe(){
        frame ++;
        if(frame > PIPE_SPAWN[timescale-1]){
            frame = 0;
            return true;

        }
        return false;
    }

    /**
     * Start the level gameplay
     * @param input Any input received from user during the level game
     * @return int this returns the level to continue game
     */
    public abstract int play(Input input);

    /**
     * Display game state if won or lost in the level
     * @param win answers for if user won or lost
     */
    public abstract void win_or_lose(boolean win);

    /**
     * Detects for:
     *      Out-of-Bound --> bird leaves window
     *      Collision --> bird collides with pipes
     *      Win --> bird passes the pipes
     */
    public void detection(){

        // Out of Bounds Detection
        Rectangle birdRectangle = bird.getRectangle();
        if(birdRectangle.bottom() > WINDOW_HEIGHT
                || birdRectangle.top() < 0){
            end = bar.loseLife();
            bird.respawn();
        }

        // Collision Detection (ONLY FOR THE CLOSET PIPE)
        Rectangle[] pipeRectangles = pipes.get(0).getRectangle();
        if(birdRectangle.intersects(pipeRectangles[0])
                || birdRectangle.intersects(pipeRectangles[1])){
            pipes.remove(0);
            end = bar.loseLife();
        }

        // Win Detection (closest pipe only as Only after closest pipe out of
        // frame, next pipe becomes first pipe in array as the closest one (original first) is deleted)
        if((birdRectangle.centre().x > pipeRectangles[0].right()) && !pipes.get(0).isPassed()){
            score ++;
            pipes.get(0).setPassed(true);
            if(score == to_win){
                end = true;
                win = true;
            }
        }
    }


}
