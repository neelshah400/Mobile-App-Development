import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;

public class AnonymousKeyboard extends JPanel
{

	public AnonymousKeyboard()
	{

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		addKeyListener(new KeyAdapter()
		{

			public void keyPressed(KeyEvent e)
			{
				String key = e.getKeyChar() + "";
				if("aeiouAEIOU".contains(key))
					System.out.println(key + " - vowel");
				else if("bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".contains(key))
					System.out.println(key + " - consonant");
				else
					System.out.println(key + " - other");
			}

		});

	}

	public static void main(String [] args)
	{
		AnonymousKeyboard keyboard = new AnonymousKeyboard();
	}

}