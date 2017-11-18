import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This program simulates a box that moves with the arrow keys
 */
public class Box extends JPanel implements ActionListener, KeyListener
{
	private static final int BOX_VELOCITY = 10;
	public static final int LENGTH = 10;
	private static final int MAX_X = 590;
	private static final int MIN_X = 0;
	private static final int MIN_Y = 0;
	private static final int MAX_Y = 350;
	private static final int TOTAL_BLOCK_POSITIONS = 12;
	
	public int[] maxYatPos;
	public int[][] maxXatHeight, minXatHeight;
	
    protected int x, y, blockVelocity, jumpVelocity, fallVelocity, delay, currentPos, currentMaxX, currentMinX, numberOfBoxesToPop, level, badItemsProbability, currentMaxJumpVelocity;
    private String status;
    protected boolean canMove, canJump, canDie, canRestart;
    
    private ArrayList<FallingObject> blocks;
    private ArrayList<Stack<Block>> stackOfBlocksAtPos;
    private ArrayList<Pop> pops;
    private ArrayList<Level> levelHolder;
    
    private Timer jumpTimer, fallTimer, resetBoxTimer, popBoxesTimer, scoreTimer, levelTimer, powerUpTimer;
    
    
    private Key key;
    private Score score;
	private Random gen;
	PowerUp powerUp;
    
    /**
     * setups up the game's frame
     */
    public Box()
    {   
    	
    	canRestart = false;
    	currentMaxJumpVelocity = 9;
    	canDie = false;
    	badItemsProbability = 20;
    	status = "Alive";
    	blockVelocity = 5;
    	gen = new Random();
    	canMove = false;
    	canJump = false;
        x = MIN_X;
        y = MAX_Y;
        currentPos = 0;
        currentMaxX = MAX_X;
        currentMinX = 0;
        addKeyListener(this);
        this.setFocusable(true);
        
        delay = 1000;
        
        jumpVelocity = currentMaxJumpVelocity;
        fallVelocity = 0;
        ActionListener jump = new Jump();
		this.jumpTimer = new Timer(60,jump);
		
		ActionListener fall = new Fall();
		this.fallTimer = new Timer(60, fall);
        
        blocks = new ArrayList<FallingObject>();
        //generateBlocks();
        //resetMovementSpecs();
        
        key = new Key();
        
        ActionListener resetBox = new resetBoxTimer();
        this.resetBoxTimer = new Timer(100,resetBox);
        
        stackOfBlocksAtPos = new ArrayList<Stack<Block>>();
        for (int i = 0; i < TOTAL_BLOCK_POSITIONS; i ++) {
        	Stack<Block> stack = new Stack<>();
        	stackOfBlocksAtPos.add(stack);
        }
        
        currentStackPos = 0;
    	currentIndex = 0;
    	numberOfBoxesToPop = 0;
    	
    	ActionListener  popBoxes = new PopBlocks();
    	this.popBoxesTimer = new Timer(100,popBoxes);
    	
    	pops = new ArrayList<>();
    	
    	score = new Score(0,50, this);
    	
    	ScoreTimerAction scoreAction = new ScoreTimerAction();
    	scoreTimer = new Timer(100, scoreAction);
    	
    	int level = 1;
    	
    	LevelAction levelAction = new LevelAction();
    	levelTimer = new Timer(2000, levelAction);
    	levelHolder = new ArrayList<>();
    	levelHolder.add(new Level(level));
    	levelTimer.start();
    	
    	}
    
    /**
     * increments the score
     */
    class ScoreTimerAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			score.incrementScore(10);
		}
    }

    
    /**
     * Draw's the hero
     * @param graphic g
     */
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        Rectangle box = new Rectangle(x, y, LENGTH, LENGTH);
        g2.setColor(Color.RED);
        g2.fill(box);
        repaint();
    }
    
    /**
     * Handles most of the GUI aspects of the game
     * @param graphics g
     */
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
		
    	for (int i = 0; i < levelHolder.size(); i ++) {
    		levelHolder.get(i).draw(g2);
    	}
    	
		for (int i = 0; i < blocks.size(); i ++) {
			//Block b = (Block) blocks.get(i);
			//b.draw(g2);
			blocks.get(i).draw(g2);
		}
		
		key.draw(g2);
		
		draw(g2);
		
		for (int i = 0; i < pops.size(); i ++) {
			Pop p = pops.get(i);
			p.draw(g2);
		}
		
		if (status == "Alive") {
			score.draw(g2);
		} else {
			score.drawDead(g2);
		}
		
		if (powerUp != null) {
			if (powerUp.canDraw()) {
				powerUp.draw(g2);
			}
		}
    }
    
    /**
     * Sets how many blocks are created for the frame
     */
    public void resetMovementSpecs() {
    	this.maxYatPos = new int[TOTAL_BLOCK_POSITIONS];
        this.maxXatHeight = new int[12][TOTAL_BLOCK_POSITIONS];
        this.minXatHeight = new int[12][TOTAL_BLOCK_POSITIONS];
        for (int i = 0; i < maxYatPos.length; i ++) {
        	maxYatPos[i] = 360;
        	for (int j = 0; j < 12; j ++) {
        		maxXatHeight[j][i] = 400;
        		minXatHeight[j][i] = 0;
        	}
        }
    }

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	/**
	 *  checks to see if player can jump, jumps if can
	 */
	public void up() {
		canJump = false;
		jumpVelocity  = currentMaxJumpVelocity;
		jumpTimer.start();
	}
	
	/**
	 * jumping class
	 */
	class Jump implements ActionListener {

		/**
		 * performs the jump, constantly redrawing 
		 */
		public void actionPerformed(ActionEvent e) {
			
			int yAtCurrentPos = maxYatPos[currentPos];
			if (y < (yAtCurrentPos + jumpVelocity - 10)) {
				y -= jumpVelocity;
				jumpVelocity --;
				if (atKey()) {
					key.canDraw = false;
					removeBlocks();
				}
				if (powerUp.atPowerUp()) {
					powerUp.activate();
					powerUp.hide();
				}
			} else {
				y = yAtCurrentPos - 10;
				jumpTimer.stop();
				canJump = true;
			}
			currentMaxX = maxXatHeight[(y)/30][currentPos];
			currentMinX = minXatHeight[(y)/30][currentPos];
		}
	}
	
	/**
	 * main left movement
	 */
	public void left() {
		
		if(x != currentMinX) {
			x -= BOX_VELOCITY;
        } else {
            x = currentMinX;
        }
		currentPos = x / 30;
		currentMaxX = maxXatHeight[(y)/30][currentPos];
		currentMinX = minXatHeight[(y)/30][currentPos];
		canFall();
		if (atKey()) {
			removeBlocks();
			key.canDraw = false;
		}
		if (powerUp.atPowerUp()) {
			powerUp.activate();
			powerUp.hide();
		}
	}
	
	/**
	 * main right movement
	 */
	public void right() {
		if(x + BOX_VELOCITY < currentMaxX) {
			x += BOX_VELOCITY;
        } else {
            x = currentMaxX - 10;
        }
		currentPos = x / 30;
		currentMaxX = maxXatHeight[(y-10)/30][currentPos];
		currentMinX = minXatHeight[(y-10)/30][currentPos];
		canFall();
		if (atKey()) {
			removeBlocks();
			key.canDraw = false;
		}
		if (powerUp.atPowerUp()) {
			powerUp.activate();
			powerUp.hide();
		}
	}
	
	/**
	 * Checks to see how far the player will fall
	 */
	public void canFall() {
		if (canJump) {
			int yAtCurrentPos = maxYatPos[currentPos];
			if (y < yAtCurrentPos) {
				canJump = false;
				fallVelocity  = 0;
				fallTimer.start();
			}
		}
	}
	
	/**
	 *	falling class
	 */
	class Fall implements ActionListener {

		/**
		 * calculates the falling positions
		 */
		public void actionPerformed(ActionEvent e) {
			
			int yAtCurrentPos = maxYatPos[currentPos];
			if (y < (yAtCurrentPos + fallVelocity - 10)) {
				y -= fallVelocity;
				fallVelocity --;
			} else {
				y = yAtCurrentPos - 10;
				fallTimer.stop();
				canJump = true;
			}
			currentMaxX = maxXatHeight[(y)/30][currentPos];
			currentMinX = minXatHeight[(y)/30][currentPos];
			if (atKey()) {
				removeBlocks();
			}
		}
		
	}
	
	/**
	 * controller method that calls the appropriate method based
	 * on button press
	 * @param KeyEvent e
	 */
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
        	if (canMove && canJump) {
        		up();
        	}
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
        	if (canMove) {
        		left();
        	}
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
        	if (canMove) {
        		right();
        	}
        } else if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_S) {
        	if (status == "Dead") {
        		if (canRestart) {
        			restart();
        		}
        	}
        }
	}

	public void keyReleased(KeyEvent e) {
		
	}


	public void actionPerformed(ActionEvent e) {
		repaint();	
	}
	
	
	/**
	 * method that generates the blocks, uses a stack
	 */
	public void generateBlocks() {
		
		int startTime = 0;
		int[] totalPerPos = new int[TOTAL_BLOCK_POSITIONS];
		while (!isFull(totalPerPos)) {
			int pos = gen.nextInt(TOTAL_BLOCK_POSITIONS);
			int currentStackSize = totalPerPos[pos];
			if (currentStackSize < 12) {
				Block b = new Block(startTime,blockVelocity,pos, 330 - (currentStackSize * 30), this);
				this.add(b);
				blocks.add(b);
				int randomDelay = gen.nextInt(800);				
				startTime += randomDelay + 200;
				totalPerPos[pos] = currentStackSize + 1;
				if (level == 2) {
					int badNumber = gen.nextInt(100);
					if (badNumber < badItemsProbability) {
						generateIcicle(startTime);
					}
				} else if (level == 3) {
					int badNumber = gen.nextInt(100);
					if (badNumber < badItemsProbability) {
						generateLaser(startTime);
					}
				} else if (level == 4) {
					int badNumber = gen.nextInt(100);
					if (badNumber < badItemsProbability) {
						generateIcicle(startTime);
					}
					badNumber = gen.nextInt(100);
					if (badNumber < badItemsProbability) {
						generateLaser(startTime);
					}
				}
			}
		}
		
		int powerUpDelay = gen.nextInt(20000) + 2000;
		int powerUpX = gen.nextInt(285);
		int powerUpY = gen.nextInt(100) + 125;
		
		powerUp = new HigherJump(powerUpX,powerUpY,powerUpDelay,this);
		
		
		this.canJump = true;
		this.canMove = true;
	}
	
	/**
	 * method to generate the lasers
	 * @param startTime, the startTime of when they appear
	 */
	private void generateLaser(int startTime) {
		int newPos = gen.nextInt(TOTAL_BLOCK_POSITIONS);
		blocks.add(new Laser(startTime,blockVelocity*2,newPos,0,this));
		int randomDelay = gen.nextInt(800);				
		startTime += randomDelay + 200;
	}
	
	/**
	 * method to generate the falling icicles
	 * @param startTime, the startTime of when they appear
	 */
	private void generateIcicle(int startTime) {
		int newPos = gen.nextInt(TOTAL_BLOCK_POSITIONS);
		blocks.add(new Icicle(startTime,blockVelocity,newPos,0,this));
		int randomDelay = gen.nextInt(800);				
		startTime += randomDelay + 200;
	}
	
	/**
	 * checks to see if the stacks are full
	 * @param arr, the position of the stack
	 * @return false if not full, true otherwise
	 */
	public boolean isFull(int[] arr) {
		
		for (int i = 0; i < arr.length; i ++) {
			int total = arr[i];
			if (total < 12) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * checks to see if the player is at the key
	 * @return true if they are, false otherwise
	 */
	public boolean atKey() {
		int keyX = key.getX();
		int keyY = key.getY();
		int keyLength = key.getLength();
		
		if (x > keyX && x < (keyX + keyLength) && y > keyY && y < (keyY + keyLength)) {
			return true;
		}
		if ((x + LENGTH) > keyX && (x + LENGTH) < (keyX + keyLength) && y > keyY && y < (keyY + keyLength)) {
			return true;
		}
		if ((x + LENGTH) > keyX && (x + LENGTH) < (keyX + keyLength) && (y + LENGTH) > keyY && (y + LENGTH) < (keyY + keyLength)) {
			return true;
		}
		if (x > keyX && x < (keyX + keyLength) && (y + LENGTH) > keyY && (y + LENGTH) < (keyY + keyLength)) {
			return true;
		}
		return false;
	}
	
	/**
	 * remove all the blocks
	 */
	public void removeBlocks() {
		scoreTimer.stop();
		while (!didRemoveAllButStopped()) {
			for (int i = 0; i < blocks.size(); i ++) {
				FallingObject b = blocks.get(i);
				if (b.startTimerIsRunning() || b.getName() != "Block") {
					b.stopStartTimer();
					blocks.remove(b);
					i--;
				} else {
					numberOfBoxesToPop++;
				}
			}
		}
		numberOfBoxesToPop = 0;
		moveBoxToCenter();
	}
	
	/**
	 * if the remove function stopped...
	 * @return true if it stopped, false if it didnt
	 */
	public boolean didRemoveAllButStopped() {
		if (blocks.size() == numberOfBoxesToPop) {
			return true;
		}
		return false;
	}
	
	/**
	 * centers hero
	 */
	public void moveBoxToCenter() {
		canMove = false;
		fallTimer.stop();
		jumpTimer.stop();
		//resetBoxTimer.start();
		resetBoxTimer.restart();
	}
	
	/**
	 * Death screen
	 */
	class resetBoxTimer implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (x != 180) {
				if (x > 170 && x < 190) {
					x = 300;
				} else if (x <= 180) {
					x += 10;
				} else {
					 x -= 10;
				}
			} else if (y != 350) {
				if (y > 340 && y < 360) {
					y = 360;
				} else if (y <= 340) {
					y += 10;
				} else {
					y -= 10;
				}

			} else {
				resetBoxTimer.stop();
				if (status != "Dead") {
					popBlocks();
				} else {
					canRestart = true;
				}
			}
		}
	}
	
	/**
	 * Pops all the blocks at the victory screen
	 */
	public void popBlocks() {
		for (int i = 0; i < blocks.size(); i ++) {
			Block b = (Block) blocks.get(i);
			int bPos = b.pos;
			if (stackOfBlocksAtPos.get(bPos) == null) {
				Stack<Block> blocksAtPos = new Stack<>();
				stackOfBlocksAtPos.add(blocksAtPos);
			}
			stackOfBlocksAtPos.get(bPos).add(b);
		}
		popBoxesTimer.restart();
	}
	
	int currentStackPos;
	int currentIndex;
	
	/**
	 * class for popBlocks
	 */
	class PopBlocks implements ActionListener {

		/**
		 * Pops the blocks
		 */
		public void actionPerformed(ActionEvent e) {
			Stack<Block> blocksAtPos = stackOfBlocksAtPos.get(currentStackPos);
			if (!blocksAtPos.isEmpty()) {
				Block b = blocksAtPos.pop();
				blocks.remove(b);
				pops.add(new Pop(b.x,b.y));
				currentIndex ++;
				score.incrementScore(50);
			} else {
				currentStackPos ++;
			}
			if (currentStackPos > stackOfBlocksAtPos.size() - 1) {
				popBoxesTimer.stop();
				System.out.println(level);
				if (level == 0) {
					level = 2;
				} else {
					 level ++;
				}
				System.out.println(level);
				levelHolder.add(new Level(level));
				levelTimer.start();
				currentStackPos = 0;
				currentIndex = 0;
			}
		}
	}
	
	/**
	 * moves to nextLevel after the blocked are popped
	 */
	public void nextLevel() {
		currentMaxJumpVelocity = 9;
		canDie = true;
		blockVelocity += 5;
		pops.clear();
		resetMovementSpecs();
		generateBlocks();
		canMove = true;
		canJump = true;
		scoreTimer.start();
		
		int newY = gen.nextInt(75) + 50;
		int newX = gen.nextInt(200) + 50;
		key.x = newX;
		key.y = newY;
		key.canDraw = true;
		
	}
	
	/**
	 * moves to next level
	 */
	class LevelAction implements ActionListener {

		/**
		 * moves to next level
		 */
		public void actionPerformed(ActionEvent e) {
			levelTimer.stop();
			levelHolder.clear();
			nextLevel();
		}
		
	}
	
	/**
	 * you dead, death screen
	 */
	public void dead() {
		if (canDie) {
			canRestart = false;
			status = "Dead";
			canDie = false;
			moveBoxToCenter();
			levelTimer.stop();
			levelHolder.clear();
			scoreTimer.stop();
		}
	}
	
	/**
	 * stops jumping
	 */
	public void stopJumping() {
		canJump = false;
	}
	
	/**
	 * restart the game
	 */
	public void restart() {
		blockVelocity = 5;
		this.score.score = 0;
		level = 1;
		status = "Alive";
		levelTimer.stop();
		levelHolder.clear();
		System.out.println(blocks.size());
		for (int i = 0; i < blocks.size(); i ++) {
			blocks.get(i).stopAndRemove();
		}
		blocks.clear();
		nextLevel();
	}
}