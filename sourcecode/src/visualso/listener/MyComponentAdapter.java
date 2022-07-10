package visualso.listener;

import java.awt.event.ComponentAdapter;

import visualso.view.SortingScreen;

public class MyComponentAdapter extends ComponentAdapter {
	SortingScreen window;
	public MyComponentAdapter(SortingScreen window) {
		this.window =window;
	}

}
