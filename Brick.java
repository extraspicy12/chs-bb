import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Brick extends Sprite implements Commons {

	private Color BrickColor;
	
	public Brick(int xPos, int yPos, Color color){
		height = Commons.HEIGHT/35;
		width = Commons.WIDTH/10;
		x = xPos;
		y = yPos;
		BrickColor = color;
	}
	
	public void paint(Graphics block){
		Graphics2D g2d = (Graphics2D)block;
		g2d.setColor(BrickColor);
		g2d.fillRect(x, y, width, height);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
}
