import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class AnonymousMouse extends JPanel
{

	public double totDist;
	public int oldX, oldY, newX, newY;

	public AnonymousMouse(double px, double in)
	{

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addMouseMotionListener(new MouseMotionListener()
		{

			public void mouseMoved(MouseEvent e)
			{

				newX = e.getX();
				newY = e.getY();

				double distance = Math.sqrt(Math.pow(newX - oldX, 2) + Math.pow(newY - oldY, 2)) * ((in / 12.0) / px);
				totDist += distance;
				System.out.println(Math.round(totDist * 2) / 2.0);

				oldX = e.getX();
				oldY = e.getY();

			}

			public void mouseDragged(MouseEvent e){}

		});

	}

	public static void main(String [] args)
	{
		AnonymousMouse mouse = new AnonymousMouse(1600.0, 16.0);
	}

}