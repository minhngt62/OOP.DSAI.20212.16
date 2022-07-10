package visualso.view;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import visualso.component.MyButton;
import visualso.controller.BaseController;

@SuppressWarnings("serial")
public abstract class BaseScreen extends JFrame {	
	protected static final int WIDTH = 1000;
	protected static final int HEIGHT = 700;
	BaseController baseController;
	JPanel topBar;
	JPanel buttonGroup;
	//File directory
	protected String directory = new File("").getAbsoluteFile() + "\\assets\\";
	//TODO Help Content for home screen
	protected String helpInfo = "Sorting Alogorithms is a basic concept that every "
			                  + "programmer should have known.\n \n "
			                  + "There are a a lot of sorting algorithms, "
			                  + "but to be suitable with our project, we only focus on 3 algorithms: \n"
			                  + "+ Merge Sort\n"
			                  + "+ Counting Sort\n"
			                  + "+ Radix Sort\n \n"
			                  + "This's app invented aiming to the purpose of visualizing "
			                  + "these alogrithms in a colorful way to help "
			                  + "user understand this concept easier and meet our class "
			                  + "project needs.\n \n"
			                  + "Without loss of generality, we assume that we will sort only Integers, "
			                  + "not necessarily distinct, in non-decreasing order in this visualization.\n\n"
			                  + "Our app is inspired of Visualgo so we named it as VisualSO as "
			                  + "Visual Sorting algOrithms.\n \n"
			                  + "Everything you need is:\n "
			                  + "1.Choosing one of 3 algorithms in the blocks to start your journey\n "
			                  + "2.Create your own array or random array by the leftside button\n "
			                  + "3.Click Sort and view it visualizes, the explanation will be demonstrate on "
			                  + "the right side and flow controller at the bottom.\n"
			                  + "Have fun!";
	//TODO About content
	protected String aboutInfo = "\nVisualSO, a sorting visulizer, is made by Team 16, Object-oriented Programming course (HUST20221) "
			                   + "with the support of:\n\n"
			                   + "1.Dr. Nguyen Thu Trang\n"
			                   + "  Senior Lecture, Hanoi University of Science and Tecnology\n\n"
			                   + "2.Ms. Nguyen Huong Giang and Mr. Vuong Dinh An\n"
			                   + "  Teaching Assistants, Hanoi University of Science and Tecnology\n\n\n"
			                   + "Our team includes:\n\n"
			                   + "1. Nguyen Tong Minh\n     20204885 - Elitech DS&AI K65\n\n"
			                   + "2. Ly Nhat Nam\n     20204886 - Elitech DS&AI K65\n\n"
			                   + "3. Pham Thanh Nam\n     20204921 - Elitech DS&AI K65\n\n"
			                   + "4. Nguyen Xuan Nam\n     20200422 - Elitech DS&AI K65";
	
	public BaseScreen() {
		baseController = new BaseController();
		add(createTop(), "North");
		setTitle("Sorting Visualizer");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		
	}
	
	private JPanel createTop() {
		topBar = new JPanel(new BorderLayout());
		topBar.setBackground(Color.BLACK);
		topBar.setPreferredSize(new Dimension(1000,45));
		topBar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0,40));
		
		Icon visualSoIcon= new ImageIcon(new ImageIcon(directory+"\\VisualSO_icon.png").getImage().getScaledInstance(180, 30, Image.SCALE_SMOOTH));
		JLabel icon = new JLabel(visualSoIcon);
		topBar.add(icon,"West");
		
		buttonGroup = new JPanel(new GridLayout(1,2));
		buttonGroup.setBackground(Color.BLACK);
				
		MyButton btnQuit = new MyButton(80,45,Color.RED);
		btnQuit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		btnQuit.setText("EXIT");
		btnQuit.setFont(new Font("Sans", Font.BOLD, 17));
		btnQuit.addActionListener(baseController.exitButtonClicked());
		buttonGroup.add(btnQuit,1,0);
		
		topBar.add(buttonGroup,"East");
		return topBar;
	}

	public String getDirectory() {
		return directory;
	}
}
