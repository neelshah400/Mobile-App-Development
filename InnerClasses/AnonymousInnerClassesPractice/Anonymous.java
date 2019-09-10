import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Anonymous extends JPanel
{

	public double totDist;
	public int oldX;
	public int oldY;

	public Anonymous()
	{

		totDist = 0.0;

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addMouseMotionListener(new MouseMotionListener()
		{

			public void mouseMoved(MouseEvent e)
			{

				//System.out.println(e.getX() + ", " + e.getY());

				int px = 1600;
				int in = 16;
				double ft = in / 12;
				double ftperpx = ft / px;

				int newX = e.getX();
				int newY = e.getY();

				double distance = Math.sqrt(Math.pow(newX - oldX, 2) + Math.pow(newY - oldY, 2)) * ftperpx;
				totDist += distance;
				System.out.println(totDist);

				int oldX = e.getX();
				int oldY = e.getY();

			}

			public void mouseDragged(MouseEvent e){}

		});

	}

	public static void main(String [] args)
	{
		Anonymous anonymous = new Anonymous();
	}

}