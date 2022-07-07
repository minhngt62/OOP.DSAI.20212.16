package listeners;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;

import controller.SortingController;
import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;
import sorting.SortingAlgorithm;
import data.*;
import view.RadixSortScreen;
import view.SortingScreen;

public class LeftSideBarBtnListener extends MyActionListener{
	private boolean sorted = false;
	SortingController controller;
	private CreateData data;
	public LeftSideBarBtnListener(SortingScreen window, CreateData data, SortingController controller) {
		super(window);
		this.data = data;
		this.controller= controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(((JButton)e.getSource()).getText()) {
		case ">":
			((JButton)e.getSource()).setText("<");
			window.getBtnCreate().setVisible(true);
			window.getBtnSort().setVisible(true);
			break;
		case "<":
			((JButton)e.getSource()).setText(">");
			window.getBtnCreate().setVisible(false);
			window.getBtnSort().setVisible(false);
			window.getBtnRandom().setVisible(false);
			window.getA().setVisible(false);
			window.getInputArrayField().setVisible(false);
			window.getBtnGo().setVisible(false);
			break;
		case "Create(A)":
			window.getBtnRandom().setVisible(true);
			window.getA().setVisible(true);
			window.getInputArrayField().setVisible(true);
			window.getBtnGo().setVisible(true);
			break;
		case "Random":
			window.updateMainArray(data.randomArray((new Random()).nextInt(90)+10,window.getMaxValue()));
			controller.setSorting(false);
			break;
		case "Go":
			String arr = window.getInputArrayField().getText();
			if (arr.equals(window.placeholder)) {
				arr = "";
			}
			controller.setSorting(false);
            try {
				window.updateMainArray(data.StringToIntArray(arr,SortingController.MAX_NUMBER,window.getMaxValue()));
			
			} catch (DataTypeException e1) {
				window.getErrorLabel().setText(e1.getMessage());	
			} catch (NullException e2) {
				window.getErrorLabel().setText(e2.getMessage());
			} catch (OutOfBoundException e3) {
				window.getErrorLabel().setText(e3.getMessage());
			} catch (Exception e4) {
				window.getErrorLabel().setText(e4.getMessage());

			}finally {
				window.getErrorLabel().setVisible(true);
				window.repaint();
			}
			break;
		case "Sort":
			if (sorted == false) {
				sorted = true;
				controller.setSorting(true);
				window.getErrorLabel().setVisible(false);
				controller.getAlgo().sort();
				controller.setStep(controller.getAlgo().getNumSteps());
				controller.setMainArrayStep(controller.getAlgo().getArrayLog());
				controller.setSubArrayStep(controller.getAlgo().getTempLog());
				controller.setAnimationArrayStep(controller.getAlgo().getPointerLog());
				controller.setInfoArrayStep(controller.getAlgo().getGuideLog());
				window.getProcessSlider().setMaximum(controller.getStep());
				if (controller.isPlay()) {
				   window.getTimer().stop();
				   window.getTimer().start();}	
			}
			break;
		}
	}
}
