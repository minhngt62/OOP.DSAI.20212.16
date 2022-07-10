package visualso.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import visualso.view.SortingScreen;

public abstract class MyActionListener implements ActionListener{
	SortingScreen window;
	public MyActionListener(SortingScreen window) {
		super();
		this.window =window;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
