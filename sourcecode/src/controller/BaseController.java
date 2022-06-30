package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import view.BaseScreen;
import view.CountingSortScreen;
import view.HomeScreen;
import view.MergeSortScreen;
import view.RadixSortScreen;

public class BaseController {
	BaseScreen window;
	public BaseController(BaseScreen window) {
		this.window = window;
	}
	//Button Controller
	public class HelpAboutListener implements ActionListener{
		String helpInfo;
		String name;
		public HelpAboutListener(String name,String helpInfo ) {
			super();
			this.name = name;
			this.helpInfo = helpInfo;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame helpFrame = new JFrame(name);
			JLabel helpContent = new JLabel(helpInfo, JLabel.CENTER);
			helpFrame.setSize(800,500);
			helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			helpFrame.setLocationRelativeTo(null);
			helpFrame.setResizable(false);
			helpFrame.add(helpContent);
			helpFrame.setVisible(true);
		}
	}
	

	public class ExitListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
		}
	}
	public class BackListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!SwingUtilities.windowForComponent(((JButton)e.getSource())).getClass().equals(new HomeScreen().getClass())) {
				new HomeScreen().setVisible(true);
				SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();

			}
			
		}
	}
}
