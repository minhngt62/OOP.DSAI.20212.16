package listeners;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import controller.SortingController;
import view.SortingScreen;

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
		   controller.setDefaultSpeed(((JSlider) e.getSource()).getValue());
		   count.setText(Integer.toString(controller.getDefaultSpeed()));
		   if (controller.isSorting()) {
		       window.getTimer().stop();
		       window.getTimer().setDelay(1000-controller.getDefaultSpeed()*10);
		       window.getTimer().start();
		       if (!controller.isPlay()) {
		    	   window.getTimer().stop();
		       }
		   }
	}
}
