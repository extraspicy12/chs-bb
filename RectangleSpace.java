import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RectangleSpace extends JPanel implements MouseMotionListener{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension minSize = new Dimension(screenSize.width, screenSize.height-200);
	
	private Color color;
	private Dimension dim;
	private Bar bar =  new Bar(minSize.width, minSize.height-100);
	private Ball ball = new Ball(minSize.width/2, minSize.height-130);
	private Timer timer;
	private int mouseX;
	
	public RectangleSpace(){
		color = Color.WHITE;
		setOpaque(true);
		add(bar);
		add(ball);
		addMouseMotionListener(this);
	    setBounds(0,0,screenSize.width, screenSize.height-100);
	}	
	
    public Dimension getMinimumSize() {
        return minSize;
    }
 
    public Dimension getPreferredSize() {
        return minSize;
    }
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		bar.paint(g);
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
	
	class ScheduleTask extends TimerTask {

        public void run() {
        	if (mouseX <= screenSize.width/16)
        		bar.move(-10);
        	else if (mouseX >= screenSize.width*(15.0/16.0))
        		bar.move(screenSize.width-screenSize.width/8+10);
        	else
        		bar.move(mouseX-screenSize.width/16);
        	ball.move();
        	repaint();
        }
    }

}
