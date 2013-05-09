import java.awt.Dimension;
import java.awt.Toolkit;

public interface Commons {
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = screenSize.width;
    public static final int TOP = 25;
    public static final int HEIGHT = screenSize.height-TOP;
    public static final int BOTTOM = HEIGHT;
    
}