package components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyTextField extends JTextField {
	public MyTextField(String placeholder) {
		super();
		setCaretColor(Color.WHITE);
		setBackground(Color.BLACK);
		setText(placeholder);
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(228,28));
		setVisible(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCaretColor(Color.WHITE);
				if (getText().equals(placeholder)) {
					setText("");
				}
			}
		});
	}
}