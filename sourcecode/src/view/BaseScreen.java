package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import controller.BaseController;

public abstract class BaseScreen extends JFrame {
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 700;
	protected static final Color myGREEN = new Color(102,255,102);
	protected static final Color myBLUE = new Color(102,102,255);
	protected static final Color myORANGE = new Color(255,102,0);
	protected static final Color myYELLOW =new Color(255,204,0);
	protected static final Color myPINK = new Color(255,51,153);
	
	BaseController controller;
	//File directory
	String directory = new File("").getAbsoluteFile()+ "/sourcecode";
	protected String helpInfo = "Hello from the other side";
	protected String aboutInfo = "At least i could say that i try";
	
	public BaseScreen() {
		controller = new BaseController(this);
		add(createTop(), "North");
		setTitle("Sorting Visualizer");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
	}
	
	JPanel createTop() {
		JPanel topBar = new JPanel(new BorderLayout());
		topBar.setBackground(Color.BLACK);
		topBar.setPreferredSize(new Dimension(1000,45));
		topBar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0,40));
		
		Icon visualSoIcon= new ImageIcon(new ImageIcon(directory+"\\resource\\VisualSO_icon.png").getImage().getScaledInstance(180, 30, Image.SCALE_SMOOTH));
		JLabel icon = new JLabel(visualSoIcon);
		topBar.add(icon,"West");
		
		JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
		buttonGroup.setBackground(Color.BLACK);
		
		MyButton btnBack = new MyButton(80,45,Color.BLACK);
		btnBack.setText("Back");
		btnBack.setFont(new Font("Sans", Font.BOLD, 17));
		btnBack.addActionListener(controller.new BackListener());
		buttonGroup.add(btnBack);
		
		MyButton btnQuit = new MyButton(80,45,Color.RED);
		btnQuit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		btnQuit.setText("EXIT");
		btnQuit.setFont(new Font("Sans", Font.BOLD, 17));
		btnQuit.addActionListener(controller.new ExitListener());
		buttonGroup.add(btnQuit);
		
		topBar.add(buttonGroup,"East");
		return topBar;
	}
	static class FixedStateButtonModel extends DefaultButtonModel  {

		@Override
        public boolean isPressed() {
            return false;
        }
    }
	class HoverMouseAdapter extends MouseAdapter{
		JButton button;
		public HoverMouseAdapter(JButton btn) {
			this.button = btn;
		}
		public void mouseEntered(MouseEvent evt) {
		    button.setBackground(Color.BLACK);
		}
		public void mouseExited(MouseEvent evt){
			button.setBackground(myBLUE);
		}
	}
	public class MyButton extends JButton{
		String id;
		public void setId(String id) {
			this.id = id;
		}
		public String getId() {
			return this.id;
		}
		public MyButton(int x, int y, Color color) {
			super();
			setModel(new FixedStateButtonModel());
			setRolloverEnabled(false);
			setMargin(new Insets(0,0,0,0));	
			setFocusable(false);
			setBackground(color);
			setForeground(Color.WHITE);
			setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			setPreferredSize(new Dimension(x,y));			
		}
	}
	public class DemonstratePane extends JTextArea{
		
		public DemonstratePane(int x, int y,Color color, String sortInfo) {
			setOpaque(true);
			setBackground(color);
			setForeground(Color.WHITE);
			//TODO sortinfo from sorting
			setText(sortInfo);
			setEditable(false);
			setFocusable(false);
			setLineWrap(true);
			setWrapStyleWord(true);
			setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			setPreferredSize(new Dimension(x,y));
			setVisible(false);
		}
	}
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
