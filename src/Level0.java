import bagel.*;

/**
 * Level0 gameplay
 */
public class Level0 extends Level{

    private final Image BACKGROUND = new Image("res/level-0/background.png");
    private final int LVL_UP_SCREEN = 150;
    private int lvlUpCount;

    /**
     * Constructor
     */
    public Level0(){
        super(0);
        lvlUpCount = 0;

        // Adding first Pipe
        getPipes().add(new PlasticP());


    }

    /**
     * Start the level gameplay
     * @param input Any input received from user during the level game
     * @return int this returns the level to continue game
     */
    @Override
    public int play(Input input){
        BACKGROUND.drawFromTopLeft(0,0);

        // Start of the level
        if(isStart()){

            // Show the instructions at the start (centered)
            getText().start();

            if(input.wasPressed(Keys.SPACE)){
                setStart(false);
            }
        }

        // Let the game begin
        else if(!isEnd()){

            // timescale change
            if(input.wasPressed(Keys.L)){
                increaseTimescale();
            }
            if(input.wasPressed(Keys.K)){
                decreaseTimescale();
            }


            // Flying the bird
            if(input.wasPressed(Keys.SPACE)){
                getBird().fly();
            }

            getBird().drawBird();


            // There only can be one pipe that will go out of frame
            int remove = 0;
            for(Pipes p: getPipes()){
                remove += p.drawPipes(getSpeed());
            }
            //remove pipe if out of frame
            if(remove == 1){
                getPipes().remove(0);
            }

            // get score and life bar
            getText().scoring(getScore());
            getBar().drawBar();

            //Out-of-Bounds, Collision, Win Detections
            detection();



        }
        else {
            // Game ended, check if won
            win_or_lose(isWin());
        }

        // Check if need to add new Pipe, if not then keep counting frames
        // Make sure frame starts counting only after game starts
        if(!isStart()) {
            boolean add = addPipe();
            if (add) {
                getPipes().add(new PlasticP());
            }
        }


        // Time to go to next Level
        if(lvlUpCount == LVL_UP_SCREEN){
            return 1;
        }
        return 0;
    }

    /**
     * Display game state if won or lost in the level
     * @param win answers for if user won or lost
     */
    @Override
    public void win_or_lose(boolean win){
        if(win){
            lvlUpCount ++;
            getText().win1(getScore());
        }
        else{
            getText().lose(getScore());
        }
    }


}
