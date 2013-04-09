import java.awt.*;

public class Ball extends Component{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x;
	private int yStart, y;
	private int diameter = 30;
	private double dx, dy;
	
	public Ball(int xValue, int yValue){
		x=xValue;
		y = yValue;
		dx=0;
		dy=0;
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
	
	public void setVelocity(double changeX, double changeY){
		dx=changeX;
		dy=changeY;
	}
	
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