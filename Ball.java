import java.awt.*;

//add commons
//add center to ball constructor and on resetstate
//also add resetstate to bar

//call setDX and setDY in run method of bar or during collision check

//mimic move from breakout - then make more advanced and add checkcollision into rectspace

public class Ball extends Component{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x, y;
	private double dx, dy;
	private int diameter = 30;
	Color color = Color.BLUE;

	public Ball(int xValue, int yValue){
		x = xValue;
		y = yValue;
		dx = 1;
		dy = 0;
	}

	public int getY(){
		return y;
	}

	public int getX(){
		return x;
	}
	
	public double getDX(){
		return dx;
	}

	public double getDY(){
		return dy;
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

	public void resetState() {
		x = 230;
		y = 355;
	}

	public void paint (Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		g2d.fillOval(x,y,diameter,diameter);
	}
}