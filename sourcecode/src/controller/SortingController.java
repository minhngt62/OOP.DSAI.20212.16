package controller;

import javax.swing.JLabel;
import listeners.ControlBtnListener;
import listeners.DemonstrateListener;
import listeners.LeftSideBarBtnListener;
import listeners.ProgressSliderListener;
import listeners.SpeedSliderListener;
import listeners.TimerListener;
import listeners.WindowResize;
import view.SortingScreen;

public class SortingController{
	SortingScreen window;
	public SortingController(SortingScreen window) {
		this.window = window;
	}
	
	public WindowResize changeWindowSize() {
		return new WindowResize(window);
	}
	
	public SpeedSliderListener changeSpeed(JLabel count) {
		return new SpeedSliderListener(window,count);
	}
	
	public ProgressSliderListener changeProgressSlider() {
		return new ProgressSliderListener(window);
	}
	
	public DemonstrateListener rightSideButtonClicked() {
		return new DemonstrateListener(window);
	}
	public LeftSideBarBtnListener leftSideButtonClicked() {
		return new LeftSideBarBtnListener(window);
	}
	public TimerListener setTimer() {
		return new TimerListener(window);
	}
	public ControlBtnListener controlButtonCLicked() {
		return new ControlBtnListener(window);
	}
}
