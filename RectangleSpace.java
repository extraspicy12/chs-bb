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
	private Timer timer;
	private int mouseX;
	
	public RectangleSpace(){
		color = Color.WHITE;
		setOpaque(true);
		add(bar);
		addMouseMotionListener(this);
	    setBounds(0,0,screenSize.width, screenSize.height-100);
	    timer = new Timer();
	    timer.scheduleAtFixedRate(new ScheduleTask(), 10, 10);
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
        	bar.move(mouseX-75);
        	repaint();
        }
    }

}
