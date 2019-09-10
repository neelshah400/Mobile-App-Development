import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Keyboard0 extends JPanel implements KeyListener
{

	public Keyboard0()
	{

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addKeyListener(this);

	}

	public static void main(String [] args)
	{
		Keyboard0 demo = new Keyboard0();
	}

	public void keyPressed(KeyEvent e)
	{
		System.out.println(e.getKeyChar());
	}

	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e){}

}