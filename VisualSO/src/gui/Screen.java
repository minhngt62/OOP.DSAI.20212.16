package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class Screen extends JFrame {
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 700;
	protected static final Color myGREEN = new Color(102,255,102);
	protected static final Color myBLUE = new Color(102,102,255);
	protected static final Color myORANGE = new Color(255,102,0);
	protected static final Color myYELLOW =new Color(255,204,0);
	protected static final Color myPINK = new Color(255,51,153);
	JPanel topbar = new JPanel(new BorderLayout());
	
	public Screen() {
		setTitle("Sorting Visualizer");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
		topbar.setBackground(Color.BLACK);
		topbar.setPreferredSize(new Dimension(1000,45));
		topbar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		add(topbar, "North");
		
	}

}
