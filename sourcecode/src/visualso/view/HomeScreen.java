package visualso.view;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import visualso.component.MyButton;
import visualso.component.SortButton;
import visualso.controller.HomeController;

@SuppressWarnings("serial")
public class HomeScreen extends BaseScreen {
	private HomeController homeController;
	public HomeScreen() {
		super();
		homeController = new HomeController();
		JPanel homePane = new JPanel(new GridLayout(2,1));
		add(homePane, BorderLayout.CENTER);
		homePane.add(createInfoPanel());
		homePane.add(createSortSelectionPane());
		homePane.setBorder(BorderFactory.createEmptyBorder(0, 0, 45, 0));
	}
	
	private JPanel createInfoPanel() {
		JPanel infoPane = new JPanel(new BorderLayout()); 
		infoPane.add(createLogoPane());
		infoPane.add(createHelpPane(),BorderLayout.SOUTH);
		infoPane.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));;
		return infoPane;
	}
	
	private JPanel createLogoPane() {
		JPanel logoPane = new JPanel(new BorderLayout());
		Icon visualSoIcon= new ImageIcon(new ImageIcon(ASSET_PATH+"VisualSO_icon1.png").getImage().getScaledInstance(420, 70, Image.SCALE_SMOOTH));
		JLabel logo = new JLabel(visualSoIcon);
		logoPane.add(logo, BorderLayout.CENTER);
		JLabel infoLabel = new JLabel("Visualizer Sorting Algorithms");
		JPanel desc = new JPanel();
		desc.add(infoLabel);
		logoPane.add(desc, BorderLayout.SOUTH);
		return logoPane;
	}
	
	private JPanel createHelpPane() {
		JPanel helpPane = new JPanel();
		MyButton btnHelp = new MyButton(50,30,Color.BLACK); 
		btnHelp.setText("Help");
		btnHelp.addActionListener(baseController.helpButtonClicked("Help",helpInfo));
		helpPane.add(btnHelp);
		MyButton btnAbout = new MyButton(50,30,Color.BLACK); 
		btnAbout.setText("About");
		btnAbout.addActionListener(baseController.helpButtonClicked("About",aboutInfo));
		helpPane.add(btnAbout);
		return helpPane;
	}
	
	private JPanel createSortSelectionPane() {
		JPanel sortSelectionPane = new JPanel(new GridLayout(1,3));
		SortButton btnMergeSort = new SortButton("Merge Sort",this,homeController);
		SortButton btnCountingSort = new SortButton("Counting Sort",this,homeController);
		SortButton btnRadixSort = new SortButton("Radix Sort",this,homeController);
		sortSelectionPane.add(btnMergeSort);
		sortSelectionPane.add(btnCountingSort);
		sortSelectionPane.add(btnRadixSort);
		return sortSelectionPane;
	}

	
}

