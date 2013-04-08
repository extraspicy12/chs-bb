import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.*;

//DEFUNCT

public class InvisiblePanel extends JFrame{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension minSize = new Dimension(screenSize.width, screenSize.height);
	private Invis Invisible = new Invis();
	private int mouseX, mouseY;
	
	public InvisiblePanel(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(Invisible);	
		pack();
	}
	
	public Dimension getMinimumSize() {
        return minSize;
    }
 
    public Dimension getPreferredSize() {
        return minSize;
    }
        
	private class Invis extends JPanel implements MouseMotionListener{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension minSize = new Dimension(screenSize.width, screenSize.height);
		public Invis(){
			setOpaque(false);
			addMouseMotionListener(this);	
		}
		
		public void mouseDragged(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
		public Dimension getMinimumSize() {
	        return minSize;
	    }
	 
	    public Dimension getPreferredSize() {
	        return minSize;
	    }
	}
}
