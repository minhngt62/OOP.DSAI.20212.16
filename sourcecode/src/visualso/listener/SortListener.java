package visualso.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import visualso.view.CountingSortScreen;
import visualso.view.MergeSortScreen;
import visualso.view.RadixSortScreen;

public class SortListener implements ActionListener{
	private String name;
	public SortListener(String name) {
		super();
		this.name =name;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(name) {
		case "Merge Sort":
			new MergeSortScreen();
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			break;
		case "Counting Sort":
			new CountingSortScreen();
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			break;
		case "Radix Sort":
			new RadixSortScreen();
			SwingUtilities.windowForComponent(((JButton)e.getSource())).dispose();
			break;
		}
	}
}
