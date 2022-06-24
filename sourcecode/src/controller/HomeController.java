package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import view.CountingSortScreen;
import view.HomeScreen;
import view.MergeSortScreen;
import view.RadixSortScreen;

public class HomeController {
	//Button Controller
	public static class HelpAboutListener implements ActionListener{
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
	
	public static class SortListener implements ActionListener{
		String name;
		public SortListener(String name) {
			super();
			this.name =name;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if( name.equals("Merge Sort")) {
				new MergeSortScreen();
				SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			}
			if( name.equals("Counting Sort")) {
				new CountingSortScreen();
				SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			}
			if( name.equals("Radix Sort")) {
				new RadixSortScreen();
				SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			}
		}
	}
	public static class ExitListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
		}
	}
	public static class BackListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(SwingUtilities.windowForComponent(((JButton)e.getSource())).getClass());
			if(!SwingUtilities.windowForComponent(((JButton)e.getSource())).getClass().equals(new HomeScreen().getClass())) {
				new HomeScreen().setVisible(true);
				SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();

			}
			
		}
	}
}
