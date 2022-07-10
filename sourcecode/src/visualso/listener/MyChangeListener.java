package visualso.listener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import visualso.view.SortingScreen;

public abstract class MyChangeListener implements ChangeListener {
	SortingScreen window;
	public MyChangeListener(SortingScreen window) {
		this.window = window;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

}
