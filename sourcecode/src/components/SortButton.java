package components;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.HomeController;
import view.HomeScreen;

public class SortButton extends JButton{
	public SortButton(String name, HomeScreen window,HomeController homeController) {
		super(name);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setVerticalTextPosition(SwingConstants.TOP);
		setVerticalAlignment(BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		Icon SortIcon= new ImageIcon(new ImageIcon(window.getDirectory()+String.join("",name.split(" "))+"_icon1.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
		Icon preSortIcon= new ImageIcon(new ImageIcon(window.getDirectory()+String.join("",name.split(" "))+"_icon.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
		setRolloverIcon(preSortIcon);
		setIcon(SortIcon);
		setFocusable(false);
		addActionListener(homeController.sortSelection(name));
	}
}