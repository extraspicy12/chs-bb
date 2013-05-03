import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class RectangleSpace extends JPanel implements Commons, MouseMotionListener, MouseListener{
	private Color color;
	private Dimension dim;
	private Bar bar;
	private Ball ball;
	private Timer timer;
	private int mouseX, lives = 3;
	private boolean gameOver, stick=false;
	private boolean paused = true;
	private JButton pause;
	private JMenuBar menu = new JMenuBar();
	private JLabel lifeCounter, barD = new JLabel("    " + 0);
	private ArrayList<Brick> bricks;
	
	public RectangleSpace(JMenuBar menubar){
		setLayout(new BorderLayout());
		menu = menubar;


		JMenu game = new JMenu("Game");		

		pause = new JButton("Start");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(!paused){
					pause();
					pause.setText("Unpause");
					stick = false;
				}
				else{
					unPause();
					pause.setText("Pause");
					stick = false;
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
		
		menubar.add(barD);
		
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
		Brick brick1 = new Brick(23, 57, Color.RED);
		add(brick1);
		bricks.add(brick1);
		
		
	}	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		bar.paint(g);
		ball.paint(g);
		for(Brick b : bricks){
			b.paint(g);
		}
	}

	public int getLives(){
		return lives;
	}

	private void loseALife(){
		if (lives >= 1)
			lives--;
		else
			newGame();
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
		pause();
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
		stick = false;
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
			setBallMults(Math.abs(bar.getX()-ball.getX()));
		}
		//collision checking time below 
		//compare x and y coords and width/radius to see if intersecting

	}
	
	private void setBallMults(int col){
		barD.setText("     " + col);
		if(col <= bar.getWidth()/10){
			ball.setXMult(11);
			ball.setYMult(4);
			ball.setXDir(-1);
			barD.setText("     " + 11 + 4 + 1);
		}
		if(col <= 2*(bar.getWidth()/10)){
			ball.setXMult(10);
			ball.setYMult(7);
			ball.setXDir(-1);
			barD.setText("     " + 10 + 7 + 1);
		}
		if(col <= 3*(bar.getWidth()/10)){
			ball.setXMult(9);
			ball.setYMult(8);
			ball.setXDir(1);
			barD.setText("     " + 9 + 8 + 1);
		}
		if(col <= 4*(bar.getWidth()/10)){
			ball.setXMult(7);
			ball.setYMult(10);
			ball.setXDir(1);
			barD.setText("     " + 7 + 10 + 1);
		}
		if(col <= 5*(bar.getWidth()/10)){
			ball.setXMult(4);
			ball.setYMult(11);
			ball.setXDir(1);
			barD.setText("     " + 4 + 11 + 1);
		}
		if(col <= 6*(bar.getWidth()/10)){
			ball.setXMult(4);
			ball.setYMult(11);
			ball.setXDir(-1);
			barD.setText("     " + 4 + 11 + -1);
		}
		if(col <= 7*(bar.getWidth()/10)){
			ball.setXMult(7);
			ball.setYMult(10);
			ball.setXDir(-1);
			barD.setText("     " + 7 + 10 + -1);
		}
		if(col <= 8*(bar.getWidth()/10)){
			ball.setXMult(9);
			ball.setYMult(8);
			ball.setXDir(-1);
			barD.setText("     " + 9 + 8 + -1);
		}
		if(col <= 9*(bar.getWidth()/10)){
			ball.setXMult(10);
			ball.setYMult(7);
			ball.setXDir(-1);
			barD.setText("     " + 10 + 7 + -1);
		}
		if(col <= 10*(bar.getWidth()/10)){
			ball.setXMult(11);
			ball.setYMult(4);
			ball.setXDir(-1);
			barD.setText("     " + 11 + 4 + -1);
		}
		
		else{
			ball.setXMult(7);
			ball.setYMult(10);
			System.out.println("I have failed you, father");
		}		
	}

	class ScheduleTask extends TimerTask {

		public void run() {
			if (mouseX <= Commons.WIDTH/16)
				bar.move(0);
			else if (mouseX >= Commons.WIDTH*(15.0/16.0))
				bar.move(Commons.WIDTH-Commons.WIDTH/8);
			else
				bar.move(mouseX-Commons.WIDTH/16);
			checkCollision();
			if(stick){
				ball.setPosition(bar.getX()+bar.getWidth()/2-Commons.WIDTH/100);
			}
			else
				ball.move();
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {//figure this out
		if(stick){
			unPause();
			stick=false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
