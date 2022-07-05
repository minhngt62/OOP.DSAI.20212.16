package listeners;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import view.SortingScreen;

public class SpeedSliderListener extends MyChangeListener{
	JLabel count;
	public SpeedSliderListener(SortingScreen window,JLabel count) {
		super(window);
		this.count = count;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		   window.setDefaultSpeed(((JSlider) e.getSource()).getValue());
		   count.setText(Integer.toString(window.getDefaultSpeed()));
		   if (window.isSorting()) {
		       window.getTimer().stop();
		       window.getTimer().setDelay(1000-window.getDefaultSpeed()*10);
		       window.getTimer().start();
		       if (!window.isPlay()) {
		    	   window.getTimer().stop();
		       }
		   }
	}
}
