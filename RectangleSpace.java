import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

//use commons to set size


public class RectangleSpace extends JPanel implements Commons, MouseMotionListener{
	private Color color;
	private Dimension dim;
	private Bar bar;
	private Ball ball;
	private Timer timer;
	private int mouseX;
	
	public RectangleSpace(){
		color = Color.WHITE;
		setOpaque(true);
		bar =  new Bar();
		ball = new Ball(bar);
		add(bar);
		add(ball);
		addMouseMotionListener(this);
	    setBounds(0,0, Commons.WIDTH, Commons.HEIGHT-100);
	}	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		bar.paint(g);
		ball.paint(g);
	}

	public void mouseDragged(MouseEvent e) {}


	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
	}
	
	public void pause(){
		timer.cancel();
	}
	
	public void unPause(){
		timer = new Timer();
	    timer.scheduleAtFixedRate(new ScheduleTask(), 10, 10);
	}
	
	public void checkCollision() {
		if (ball.getY() > Commons.BOTTOM - Commons.WIDTH/50) {
			Random randomGenerator = new Random();
			int red = randomGenerator.nextInt(255);
			int green = randomGenerator.nextInt(255);
			int blue = randomGenerator.nextInt(255);

			Color randomColour = new Color(red,green,blue);
			ball.setColor(randomColour);
			ball.setYDir(-1);
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
		}
		//collision checking time below 
		//compare x and y coords and width/radius to see if intersecting

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
        	ball.move();
        	repaint();
        }
    }

}
