import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


//use commons to set size


public class RectangleSpace extends JPanel implements Commons, MouseMotionListener {

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
    		int keyCode = e.getKeyCode();
    		if(keyCode==KeyEvent.VK_DOWN){
    			if (stick)
    				stick = false;
    		}
    		if (keyCode==KeyEvent.VK_SPACE){
    			if (paused)
    				unPause();
    			else
    				pause();
    		}
            return false;
        }
	
    }
	private Color color;
	private Dimension dim;
	private Bar bar;
	private Ball ball;
	private Timer timer;
	private int mouseX, lives = 3;
	private boolean gameOver, stick=true;
	private boolean paused = true;
	private JButton pause;
	private JMenuBar menu = new JMenuBar();
	private JLabel lifeCounter;
	private ArrayList<Brick> bricks;
	private String message = "Game Over";

	public RectangleSpace(JMenuBar menubar){
		
      KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
      manager.addKeyEventDispatcher(new MyDispatcher());
		setLayout(new BorderLayout());
		menu = menubar;


		JMenu game = new JMenu("Game");		

		pause = new JButton("Start");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(!paused){
					pause();
					pause.setText("Unpause");
				}
				else{
					unPause();
					pause.setText("Pause");
				}
			}
		});

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setToolTipText("Create a new game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newGame();
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		exit.setToolTipText("Exit game");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		game.add(newGame);
		game.add(exit);

		menubar.add(game);
		menubar.add(pause);

		lifeCounter = new JLabel("  Lives: " + lives);
		menubar.add(lifeCounter);

		color = Color.WHITE;
		setOpaque(true);
		bar =  new Bar();
		ball = new Ball(bar);
		add(bar);
		add(ball);
		addMouseMotionListener(this);
		setBounds(0,0, Commons.WIDTH, Commons.HEIGHT-100);

		add(menu, BorderLayout.NORTH);


		bricks = new ArrayList<Brick>();

		for (int i = 0; i < 20; i+=3) {
			for (int j = 0; j < 19; j+=3) {
				bricks.add(new Brick(j * Commons.WIDTH/20 + 10, i * Commons.HEIGHT/50 + Commons.HEIGHT/25 +10, Color.RED));
			}
		}

		//		Brick brick1 = new Brick(20, 50, Color.RED);
		//		add(brick1);
		//		bricks.add(brick1);


	}	

	public void paintComponent(Graphics g){
		if(gameOver){
			Font font = new Font("Verdana", Font.BOLD, 80);
			FontMetrics metr = this.getFontMetrics(font);
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(message, (Commons.WIDTH - metr.stringWidth(message)) / 2, Commons.HEIGHT / 2);
		}
		else{
			super.paintComponent(g);
			bar.paint(g);
			ball.paint(g);
			for(Brick b : bricks){
				if(!b.isDestroyed())
					b.paint(g);
			}
		}
	}

	public int getLives(){
		return lives;
	}

	private void loseALife(){
		if (lives >= 1)
			lives--;
		//		else
		//			newGame();
	}

	private void newGame(){
		lives = 3;
		lifeCounter.setText("  Lives: " + lives);
		ball.resetState(bar);
		pause();
		pause.setText("Start");
		//clear score too 
		//remake bricks
	}	
	private void resetGame(){
		ball.resetState(bar);
		stick = true;
		pause.setText("Restart");
		loseALife();
		lifeCounter.setText("  Lives: " + lives);
	}

	public void mouseDragged(MouseEvent e) {}


	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	}

	public void pause(){
		timer.cancel();
		paused = true;
	}

	public void unPause(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), 10, 10);
		paused = false;
	}

	public boolean isGameOver(){
		return gameOver;
	}

	public void checkCollision() {
		if (ball.getY() > Commons.BOTTOM - Commons.WIDTH/50) {
			resetGame();	
		}
		if (ball.getX() <= 0) {
			ball.setXDir(1);
		}
		if (ball.getX() >= Commons.WIDTH - Commons.WIDTH/50) {
			ball.setXDir(-1);
		}
		if (ball.getY() <= 0) {
			ball.setYDir(1);
		}  

		if (ball.getY()>=bar.getY()-bar.getHeight() && (ball.getCenter() > bar.getX() && ball.getCenter() < bar.getX() + bar.getWidth())) {
			ball.setYDir(-1);
			setBallMults((int)bar.getRect().getMinX(), (int)ball.getRect().getMinX());
		}
		int k =0, hold=0;
		boolean destroy=false;
		for(Brick b : bricks){
			if(b.getRect().intersects(ball.getRect())){
				b.setDestroyed();
				destroy = true;
				reverseEm(b.getRect(), ball.getRect());
				hold = k;
			}
			k++;
		}
		if(destroy){
			bricks.remove(hold);
			destroy = false;
		}
		//collision checking time below 
		//compare x and y coords and width/radius to see if intersecting

	}

	private void reverseEm(Rectangle brickRect, Rectangle ballRect){
		int ballLeft = (int)ball.getRect().getMinX();
		int ballHeight = (int)ball.getRect().getHeight();
		int ballWidth = (int)ball.getRect().getWidth();
		int ballTop = (int)ball.getRect().getMinY();

		Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
		Point pointLeft = new Point(ballLeft - 1, ballTop);
		Point pointTop = new Point(ballLeft, ballTop - 1);
		Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

		if (brickRect.contains(pointRight)) {
			ball.reverseXDir();
		}

		else if (brickRect.contains(pointLeft)) {
			ball.reverseXDir();
		}

		if (brickRect.contains(pointTop)) {
			ball.reverseYDir();
		}

		else if (brickRect.contains(pointBottom)) {
			ball.reverseYDir();
		}
	}

	private void setBallMults(int RectX, int BarX){

		int col = BarX-RectX;
		if(col <= bar.getWidth()/10){
			ball.setXMult(11);
			ball.setYMult(4);
			ball.setXDir(-1);

		}
		else if(col <= 2*(bar.getWidth()/10)){
			ball.setXMult(10);
			ball.setYMult(7);
			ball.setXDir(-1);

		}
		else if(col <= 3*(bar.getWidth()/10)){
			ball.setXMult(9);
			ball.setYMult(8);
			ball.setXDir(-1);

		}
		else if(col <= 4*(bar.getWidth()/10)){
			ball.setXMult(7);
			ball.setYMult(10);
			ball.setXDir(-1);

		}
		else if(col <= 5*(bar.getWidth()/10)){
			ball.setXMult(4);
			ball.setYMult(11);
			ball.setXDir(-1);

		}
		else if(col <= 6*(bar.getWidth()/10)){
			ball.setXMult(4);
			ball.setYMult(11);
			ball.setXDir(1);
		}
		else if(col <= 7*(bar.getWidth()/10)){
			ball.setXMult(7);
			ball.setYMult(10);
			ball.setXDir(1);

		}
		else if(col <= 8*(bar.getWidth()/10)){
			ball.setXMult(9);
			ball.setYMult(8);
			ball.setXDir(1);

		}
		else if(col <= 9*(bar.getWidth()/10)){
			ball.setXMult(10);
			ball.setYMult(7);
			ball.setXDir(1);

		}
		else if(col <= bar.getWidth()){
			ball.setXMult(11);
			ball.setYMult(4);
			ball.setXDir(1);

		}

		else{
			ball.setXMult(7);
			ball.setYMult(10);
		}		
	}

	class ScheduleTask extends TimerTask {

		public void run() {
			if (bricks.size() == 0){
				gameOver=true;
				message = "Congratulations";
			}
			if (lives == 0){
				gameOver = true;
			}
			if (mouseX <= Commons.WIDTH/16)
				bar.move(0);
			else if (mouseX >= Commons.WIDTH*(15.0/16.0))
				bar.move(Commons.WIDTH-Commons.WIDTH/8);
			else
				bar.move(mouseX-Commons.WIDTH/16);
			checkCollision();
			if(stick){
				ball.setX(bar.getX()+bar.getWidth()/2-Commons.WIDTH/100);
				ball.setY(bar.getY()-bar.getHeight()/2-Commons.WIDTH/100);
			}
			else
				ball.move();
			repaint();
		}
	}

}
