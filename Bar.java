import java.awt.*;

public class Bar extends Component{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x;
	private int yStart, y;
	private int height = 20;
	private int width = screenSize.width/8;
	private double dx, angle;
	private double convertedDX;
	private final double mark05 = .05, mark1 = .1, mark2 = .2, mark3 = .3, mark4 = .4, mark5 = .5;
	private final double mark6 = .6, mark7 = .7, mark8 = .8, mark9 = .9, mark10 = 1.0;

	public Bar(int width, int height){
		x = width/2;
		yStart = height;
		y = height;
	}	
	public void move(int newX){
		convertedDX = (double)(newX-x)/(double)screenSize.width;
		dx = (newX-x)/10;
		if(convertedDX <= mark10){
			angle = 90;
		}else if(convertedDX < mark9){
			angle = 82;
		}else if(convertedDX < mark8){
			angle = 74;
		}else if(convertedDX < mark7){
			angle = 66;
		}else if(convertedDX < mark5){
			angle = 58;
		}else if(convertedDX < mark5){
			angle = 50;
		}else if(convertedDX < mark4){
			angle = 42;
		}else if(convertedDX < mark3){
			angle = 34;
		}else if(convertedDX < mark2){
			angle = 26;
		}else if(convertedDX < mark1){
			angle = 18;
		}else if(convertedDX < mark05){
			angle = 10;
		}else{
			angle = 0;
		}
		x+=dx;

	}
	public void paint(Graphics block){
		Graphics2D g2d = (Graphics2D)block;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, width, height);
		g2d.rotate(angle);
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	public int getX(){
		return x;
	}
}
