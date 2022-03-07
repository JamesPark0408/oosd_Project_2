import bagel.*;
import bagel.util.Rectangle;
import java.util.Random;

/**
 * Level1 gameplay
 */
public class Level1 extends Level {

    private final Image BACKGROUND = new Image("res/level-1/background.png");
    private final double PIPE_WIDTH = 65;
    private final double BUFFER = 45;
    private Random rand = new Random();
    private boolean pickedUp;
    private boolean shooting;

    // Only have one weapon in use at a time
    private Weapon weapon;

    /**
     * Constructor
     */
    public Level1(){
        super(1);

        // Adding first Pipe
        insertPipe();

        weapon = null;
        pickedUp = false;
        shooting = false;

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
        if(isStart() == true){

            // Show the instructions at the start (centered)
            getText().start1();

            if(input.wasPressed(Keys.SPACE)){
                setStart(false);
            }


        }

        // Let the game begin
        else if(!isEnd()) {

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

            // Randomly add weapon but make sure it's not overlapping
            if(weapon == null) {
                if (getPipes().get(getPipes().size() - 1).getPoint()[0]
                        < (Window.getWidth() - PIPE_WIDTH - BUFFER)) {

                    int type = rand.nextInt(2);
                    if (type == 0) {
                        weapon = new Bomb();
                    } else {
                        weapon = new Rock();
                    }
                    weapon.drawWeapon(getSpeed());

                }
            }

            // This means the weapon is already in play
            else {

                //Check for collision with bird or pipes
                collide();

                // Check see if shooting started
                if(input.wasPressed(Keys.S) && pickedUp){
                    shooting = true;
                    pickedUp = false;
                }

                // Attached to bird mouth
                if(pickedUp){
                    weapon.followBird(getBird().getPoint()[0],getBird().getPoint()[1]);
                    weapon.draw();
                }

                // in path of shooting
                else if(shooting){
                    boolean cont = weapon.shoot();

                    //if end --> it means it reached end of range
                    if(!cont){
                        weapon = null;
                        shooting = false;
                    }

                }

                // it is moving right to left (Not picked up yet)
                else if(weapon != null){
                    int done = weapon.drawWeapon(getSpeed());
                    if(done == 1){
                        weapon = null;
                    }

                }


            }

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
                insertPipe();
            }
        }

        return 1;

    }


    /**
     * Display game state if won or lost in the level
     * @param win answers for if user won or lost
     */
    @Override
    public void win_or_lose(boolean win){
        if(win){
            getText().win2(getScore());
        }
        else{
            getText().lose(getScore());
        }
    }

    /**
     * Creates a new pipe in random (steel or plastic)
     */
    public void insertPipe(){
        int type = rand.nextInt(2);

        //Plastic Pipe
        if(type == 0){
            getPipes().add(new PlasticP(1));
        }
        else{
            getPipes().add(new SteelP());
        }
    }


    /**
     * Checks for collision between
     *      -weapon and bird (collecting the weapon)
     *      -weapon and pipes (hitting the pipes *excluding rocks and steel pipes)
     */
    public void collide(){

        // Check if collide with Bird
        Rectangle birdRectangle = getBird().getRectangle();
        Rectangle weaponRectangle = weapon.getRectangle();
        if(birdRectangle.intersects(weaponRectangle)){
            pickedUp = true;
        }

        // Check if collide with pipes (ONLY DURING SHOOTING)
        if(shooting) {
            int pipeNum = 0;
            for (Pipes p : getPipes()) {
                Rectangle[] pipeRectangles = p.getRectangle();
                if (weaponRectangle.intersects(pipeRectangles[0])
                        || weaponRectangle.intersects(pipeRectangles[1])) {

                    // Rock cannot defeat steel pipes
                    if (!(weapon instanceof Rock && p instanceof SteelP)) {
                        getPipes().remove(pipeNum);
                        increaseScore();
                        weapon = null;
                        shooting = false;
                        break;
                    }
                }
                pipeNum++;
            }
        }

    }

}