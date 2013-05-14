import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

import javax.swing.*;


public class theGame extends JFrame{

	private RectangleSpace space;
	
	public theGame(){
		setTitle("Brick Breaker Prototype");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container Content = getContentPane();
		Content.setLayout(new BorderLayout());
	
		JMenuBar menubar = new JMenuBar();
		
		space = new RectangleSpace(menubar);
		
		Content.add(space, BorderLayout.CENTER);
		pack();
	}

	
	public Dimension getMinimumSize() {
        return Commons.screenSize;
    }
 
    public Dimension getPreferredSize() {
        return Commons.screenSize;
    }

}
