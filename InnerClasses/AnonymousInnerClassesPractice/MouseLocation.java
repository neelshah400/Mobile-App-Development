import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class MouseLocation extends JPanel implements MouseMotionListener
{

	public MouseLocation()
	{

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addMouseMotionListener(this);

	}

	public static void main(String [] args)
	{
		MouseLocation mouse = new MouseLocation();
	}

	public void mouseMoved(MouseEvent e)
	{
		System.out.println(e.getX() + ", " + e.getY());
	}

	public void mouseDragged(MouseEvent e){}

}