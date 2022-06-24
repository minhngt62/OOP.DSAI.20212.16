package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class BaseScreen extends JFrame {
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 700;
	protected static final Color myGREEN = new Color(102,255,102);
	protected static final Color myBLUE = new Color(102,102,255);
	protected static final Color myORANGE = new Color(255,102,0);
	protected static final Color myYELLOW =new Color(255,204,0);
	protected static final Color myPINK = new Color(255,51,153);
	JPanel topBar = new JPanel(new BorderLayout());
	
	public BaseScreen() {
		setTitle("Sorting Visualizer");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
		topBar.setBackground(Color.BLACK);
		topBar.setPreferredSize(new Dimension(1000,45));
		topBar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		add(topBar, "North");
		
	}

}
