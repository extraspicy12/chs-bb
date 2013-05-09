import java.awt.*;

//center the bar? fix default constructors

public class Bar extends Sprite implements Commons{
	private int lastdx, dx, changeX, convertedDX, angle;
	private final double mark05 = .05, mark1 = .1, mark2 = .2, mark3 = .3, mark4 = .4, mark5 = .5;
	private final double mark6 = .6, mark7 = .7, mark8 = .8, mark9 = .9, mark10 = 1.0;

	public Bar(){
		height = Commons.HEIGHT/40;
		width = Commons.WIDTH/8;
		x = Commons.WIDTH/2 - width/2;
		y = Commons.HEIGHT - Commons.HEIGHT/10;
		dx = 0;
	}	
	public void move(int newX){ //fix and implement - maybe add rotation in a later version
		lastdx = dx; //use to calculate bar speed on contact with ball
		convertedDX = (newX-x)/Commons.WIDTH; //percentage of screen moved
		changeX = newX - x;
		if (Math.abs(changeX) < 10 && changeX != 0) {
			if (Math.abs(changeX) < 3)
				dx = (changeX/Math.abs(changeX));
			else
				dx = 2*(changeX/Math.abs(changeX));
		} else
			dx = changeX/10;
		x += dx;
	}
	public void paint(Graphics block){
		Graphics2D g2d = (Graphics2D)block;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, width, height);
		//g2d.rotate(angle);
	}
}
