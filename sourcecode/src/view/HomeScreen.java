package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import components.MyButton;
import controller.HomeController;

public class HomeScreen extends BaseScreen {
	public HomeScreen() {
		super();
		controller = new HomeController(this);
		JPanel homePane = new JPanel(new GridLayout(2,1));
		add(homePane, BorderLayout.CENTER);
		homePane.add(createInfoPanel());
		homePane.add(createSortSelectionPane());
		homePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 45, 0));
	}
	
	JPanel createInfoPanel() {
		JPanel infoPane = new JPanel(new BorderLayout()); 
		infoPane.add(createLogoPane());
		infoPane.add(createHelpPane(),BorderLayout.SOUTH);
		infoPane.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));;
		return infoPane;
	}
	
	JPanel createLogoPane() {
		JPanel logoPane = new JPanel(new BorderLayout());
		Icon visualSoIcon= new ImageIcon(new ImageIcon(directory+"VisualSO_icon1.png").getImage().getScaledInstance(420, 70, Image.SCALE_SMOOTH));
		JLabel logo = new JLabel(visualSoIcon);
		logoPane.add(logo, BorderLayout.CENTER);
		JLabel infoLabel = new JLabel("Visualizer Sorting Alogorithms");
		JPanel desc = new JPanel();
		desc.add(infoLabel);
		logoPane.add(desc, BorderLayout.SOUTH);
		return logoPane;
	}
	
	JPanel createHelpPane() {
		JPanel helpPane = new JPanel();
		MyButton btnHelp = new MyButton(50,30,Color.BLACK); 
		btnHelp.setText("Help");
		btnHelp.addActionListener(controller.new HelpAboutListener("Help",helpInfo));
		helpPane.add(btnHelp);
		MyButton btnAbout = new MyButton(50,30,Color.BLACK); 
		btnAbout.setText("About");
		btnAbout.addActionListener(controller.new HelpAboutListener("About",aboutInfo));
		helpPane.add(btnAbout);
		return helpPane;
	}
	
	JPanel createSortSelectionPane() {
		JPanel sortSelectionPane = new JPanel(new GridLayout(1,3));
		SortButton btnMergeSort = new SortButton("Merge Sort");
		SortButton btnCountingSort = new SortButton("Counting Sort");
		SortButton btnRadixSort = new SortButton("Radix Sort");
		sortSelectionPane.add(btnMergeSort);
		sortSelectionPane.add(btnCountingSort);
		sortSelectionPane.add(btnRadixSort);
		return sortSelectionPane;
	}
	public class SortButton extends JButton{
		public SortButton(String name) {
			super(name);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			setVerticalTextPosition(SwingConstants.TOP);
			setVerticalAlignment(BOTTOM);
			setHorizontalTextPosition(SwingConstants.CENTER);
			Icon SortIcon= new ImageIcon(new ImageIcon(directory+String.join("",name.split(" "))+"_icon1.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
			Icon preSortIcon= new ImageIcon(new ImageIcon(directory+String.join("",name.split(" "))+"_icon.png").getImage().getScaledInstance(330, 240, Image.SCALE_SMOOTH));
			setRolloverIcon(preSortIcon);
			setIcon(SortIcon);
			setFocusable(false);
			addActionListener(((HomeController)controller).new SortListener(name));
		}
	}
	
}

