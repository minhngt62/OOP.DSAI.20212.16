package visualso.listener;

import java.awt.event.ComponentAdapter;
import visualso.controller.SortingController; 

public class MyComponentAdapter extends ComponentAdapter {
	SortingController controller;
	public MyComponentAdapter(SortingController controller) {
		this.controller = controller;
	}

}
