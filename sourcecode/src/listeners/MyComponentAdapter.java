package listeners;

import java.awt.event.ComponentAdapter;

import view.SortingScreen;

public class MyComponentAdapter extends ComponentAdapter {
	protected SortingScreen window;
	public MyComponentAdapter(SortingScreen window) {
		this.window =window;
	}

}
