import java.awt.*;

//add commons
//add center to ball constructor and on resetstate
//add variable for ball to be stuck to bar in next modification
//also add resetstate to bar

//call setDX and setDY in run method of bar or during collision check

//mimic move from breakout - then make more advanced and add checkcollision into rectspace

public class Ball extends Sprite implements Commons{
	private int xdir, ydir, xmult, ymult;
	private int diameter = Commons.WIDTH/50;
	public static final int DEFAULT_XMULT = 0, DEFAULT_YMULT=10;
	
	Color color = Color.BLUE;

	public Ball(Bar bar){
		width = diameter;
		height = diameter;
		resetState(bar);
	}
	
	//using to test
	public int getCenter(){
		return x + width/2;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public double getXDir(){
		return xdir;
	}

	public double getYDir(){
		return ydir;
	}

	public void setXDir(int xdirect){
		xdir = xdirect;
	}

	public void setYDir(int ydirect){
		ydir = ydirect;
	}
	
	public double getXMult(){
		return xmult;
	}

	public double getYMult(){
		return ymult;
	}

	public void setXMult(int xmultip) {
		xmult = Math.abs(xmultip);
	}

	public void setYMult(int ymultip) {
		ymult = Math.abs(ymultip);
	}
	
	public void move() {
	      x += xdir*xmult;
	      y += ydir*ymult;
	    }

	public void resetState(Bar bar) {
		x = bar.getX()+bar.getWidth()/2-Commons.WIDTH/100;
		y = bar.getY()-bar.getHeight()/2-Commons.WIDTH/100;
		xmult = 0;
		ymult = DEFAULT_YMULT;
		ydir=-1;
		
	}

	public void setPosition(int xPos){
		x=xPos;
	}
	
	public void paint (Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		g2d.fillOval(x,y,diameter,diameter);
	}
}