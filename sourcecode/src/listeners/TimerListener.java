package listeners;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import controller.SortingController;
import view.SortingScreen;

public class TimerListener extends MyActionListener{
	private SortingController controller;
	public TimerListener(SortingScreen window, SortingController controller) {
		super(window);
		this.controller= controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (controller.isSorting() && controller.getCurStep() <controller.getStep() ) {				
		   window.getProcessSlider().setValue(controller.getCurStep()+1);	
		   ((Timer )e.getSource()).setDelay(1000 - controller.getDefaultSpeed()*10);
		}
	}
}

