package listeners;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.SortingScreen;

public abstract class MyChangeListener implements ChangeListener {
	SortingScreen window;
	public MyChangeListener(SortingScreen window) {
		this.window = window;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}

}
