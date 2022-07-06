package listeners;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import view.SortingScreen;

public class TimerListener extends MyActionListener{
	public TimerListener(SortingScreen window) {
		super(window);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (window.isSorting() && window.getCurStep() <window.getStep() ) {				
		   window.getProcessSlider().setValue(window.getCurStep()+1);	
		   ((Timer )e.getSource()).setDelay(1000 - window.getDefaultSpeed()*10);
		}
	}
}

