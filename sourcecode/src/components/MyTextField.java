package components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class MyTextField extends JTextField {
	public MyTextField() {
		super();
		setCaretColor(Color.WHITE);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(228,28));
		setVisible(false);
	}
}