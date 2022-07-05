package listeners;

import java.awt.event.ActionEvent;

import view.SortingScreen;

public class TimerListener extends MyActionListener{
	public TimerListener(SortingScreen window) {
		super(window);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (window.isSorting() && window.getCurStep() <window.getStep() ) {				
		   window.getProcessSlider().setValue(window.getCurStep()+1);	
		}
	}
}

