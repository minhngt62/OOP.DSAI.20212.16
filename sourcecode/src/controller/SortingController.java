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
	SortingScreen sortingScreen;
	DataGuard data;
	SortingAlgorithm algo;
	

	
	protected int[][] mainArrayStep;
	protected int[][] subArrayStep;
	protected int[][] animationArrayStep;
	protected String[] infoArrayStep;

	protected int step;

	protected int defaultSpeed = 50;
	protected boolean isPlay = true;    //if playing
	protected boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	public static final int MAX_NUMBER = 100;
		
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

	public int[][] getMainArrayStep() {
		return mainArrayStep;
	}

	public void setMainArrayStep(int[][] mainArrayStep) {
		this.mainArrayStep = mainArrayStep;
	}

	public int[][] getSubArrayStep() {
		return subArrayStep;
	}

	public void setSubArrayStep(int[][] subArrayStep) {
		this.subArrayStep = subArrayStep;
	}

	public int[][] getAnimationArrayStep() {
		return animationArrayStep;
	}

	public void setAnimationArrayStep(int[][] animationArrayStep) {
		this.animationArrayStep = animationArrayStep;
	}

	public String[] getInfoArrayStep() {
		return infoArrayStep;
	}

	public void setInfoArrayStep(String[] infoArrayStep) {
		this.infoArrayStep = infoArrayStep;
	}
	public void setDefaultSpeed(int defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}
	public int getDefaultSpeed() {
		return this.defaultSpeed;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
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
	public SortingAlgorithm getAlgo() {
		return algo;
	}
	public void setAlgo(SortingAlgorithm algo) {
		this.algo =algo;
	}
	public DataGuard getData() {
		return new DataGuard() ;
	}

}
