package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import view.BaseScreen;
import view.HomeScreen;


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
			JTextArea helpContent = new JTextArea(helpInfo);
			helpContent.setOpaque(true);
			helpContent.setBackground(Color.black);
			helpContent.setForeground(Color.WHITE);
			helpContent.setEditable(false);
			helpContent.setFocusable(false);
			helpContent.setLineWrap(true);
			helpContent.setWrapStyleWord(true);
			helpContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			helpFrame.setSize(500,480);
			helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			helpFrame.setLocationRelativeTo(null);
			helpFrame.setResizable(false);
			helpFrame.add(helpContent);
			helpFrame.setVisible(true);
			helpFrame.addWindowFocusListener(new WindowFocusListener() {
				@Override
				public void windowGainedFocus(WindowEvent e) {
				}
				@Override
				public void windowLostFocus(WindowEvent a) {
					((JFrame)a.getSource()).dispose();
				}
			});
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
