import java.awt.*;

//add commond and sprites?

public class Ball extends Component{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x, y, dx, dy;
	private int diameter = 30;
	
	public Ball(int xValue, int yValue){
		x = xValue;
		y = yValue;
		dx=0;
		dy=1;
	}
	
	public double getDX(){
		return dx;
	}
	
	public double getDY(){
		return dy;
	}
	
	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setDX(int xdirect){
		dx = xdirect;
	}
	
	public void setDY(int ydirect){
		dy = ydirect;
	}
	
//	private void setVelocity(double xmult, double ymult) {
//		dx *= xmult;
//		dy *= ymult;
//	}
	
	public void move(){
		x+=dx;
		y+=dy;
	}
	
	public void paint (Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLUE);
		g2d.fillOval(x,y,diameter,diameter);
	}
}