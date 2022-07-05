package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SortingScreen;

public abstract class MyActionListener implements ActionListener{
	protected SortingScreen window;
	public MyActionListener(SortingScreen window) {
		super();
		this.window =window;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
