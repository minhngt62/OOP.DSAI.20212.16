package visualso.listener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import visualso.controller.SortingController;
import visualso.view.SortingScreen;

public class SpeedSliderListener extends MyChangeListener{
	private JLabel count;
	private SortingController controller;
	public SpeedSliderListener(SortingScreen window,JLabel count, SortingController controller) {
		super(window);
		this.count = count;
		this.controller = controller;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		   controller.setSpeed(((JSlider) e.getSource()).getValue());
		   count.setText(Integer.toString(controller.getSpeed()));
		   if (controller.isSorting()) {
		       window.getTimer().stop();
		       window.getTimer().setDelay(1000-controller.getSpeed()*10);
		       window.getTimer().start();
		       if (!controller.isPlay()) {
		    	   window.getTimer().stop();
		       }
		   }
	}
}
