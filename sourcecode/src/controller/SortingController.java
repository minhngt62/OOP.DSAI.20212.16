package controller;

import java.util.Random;

import javax.swing.JLabel;

import data.DataGuard;
import listeners.ControlBtnListener;
import listeners.DemonstrateListener;
import listeners.LeftSideBarBtnListener;
import listeners.ProgressSliderListener;
import listeners.SpeedSliderListener;
import listeners.TimerListener;
import listeners.WindowResize;
import sorting.SortingAlgorithm;
import view.RadixSortScreen;
import view.SortingScreen;

public class SortingController{
	private SortingScreen sortingScreen;
	private DataGuard data;
	private SortingAlgorithm model;

	private int speed = 50;
	private boolean isPlay = true;    //if playing
	private boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	private int curStep = 0;
	public static final int MAX_LENGTH = 100;
		
	public SortingController(SortingScreen sortingScreen) {
		this.sortingScreen = sortingScreen;
		this.data = new DataGuard();
	}
	
	public WindowResize changeWindowSize() {
		return new WindowResize(sortingScreen);
	}
	
	public SpeedSliderListener changeSpeed(JLabel count) {
		return new SpeedSliderListener(sortingScreen, count, this);
	}
	
	public ProgressSliderListener changeProgressSlider() {
		return new ProgressSliderListener(sortingScreen, this);
	}
	
	public DemonstrateListener rightSideButtonClicked() {
		return new DemonstrateListener(sortingScreen);
	}
	public LeftSideBarBtnListener leftSideButtonClicked() {
		return new LeftSideBarBtnListener(sortingScreen, data, this);
	}
	public TimerListener setTimer() {
		return new TimerListener(sortingScreen, this);
	}
	public ControlBtnListener controlButtonCLicked() {
		return new ControlBtnListener(sortingScreen, this);
	}
	
	public int[][] getArrayStates() {
		return model.getArrayLog();
	}
	public int[][] getTempStates() {
		return model.getTempLog();
	}
	public int[][] getPointerStates() {
		return model.getPointerLog();
	}
	public String[] getGuideStates() {
		return model.getGuideLog();
	}
	public int getStep() {
		return model.getNumSteps();
	}

	public void setSpeed(int defaultSpeed) {
		this.speed = defaultSpeed;
	}
	public int getSpeed() {
		return this.speed;
	}
	public boolean isPlay() {
		return isPlay;
	}
	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}
	public boolean isSorting() {
		return isSorting;
	}
	public void setSorting(boolean isSorting) {
		this.isSorting = isSorting;
	}
	public int getCurStep() {
		return curStep;
	}
	public void setCurStep(int curStep) {
		this.curStep = curStep;
	}
	public SortingAlgorithm getModel() {
		return model;
	}
	public void setModel(SortingAlgorithm model) {
		this.model = model;
	}
	public DataGuard getData() {
		return new DataGuard() ;
	}
}
