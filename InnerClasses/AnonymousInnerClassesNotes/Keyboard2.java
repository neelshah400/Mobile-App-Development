import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Keyboard2 extends JPanel
{

	public Keyboard2()
	{

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addKeyListener(new KeyListenerInterface());

	}

	public static void main(String [] args)
	{
		Keyboard2 demo = new Keyboard2();
	}

	public class KeyListenerInterface implements KeyListener
	{

		public void keyPressed(KeyEvent e)
		{
			System.out.println(e.getKeyChar());
		}

		public void keyReleased(KeyEvent e){}

		public void keyTyped(KeyEvent e){}

	}

}