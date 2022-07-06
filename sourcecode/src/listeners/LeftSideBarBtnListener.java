package listeners;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;

import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;
import utils.ArrayUtils;
import utils.DataProcessing;
import utils.RandomArray;
import view.SortingScreen;

public class LeftSideBarBtnListener extends MyActionListener{
	private boolean sorted = false;
	public LeftSideBarBtnListener(SortingScreen window) {
		super(window);
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
			window.updateMainArray(RandomArray.random_array((new Random()).nextInt(90)+10,window.getMaxValue()));
			window.setSorting(false);
			break;
		case "Go":
			String arr = window.getInputArrayField().getText();
			window.setSorting(false);
            try {
				if (DataProcessing.isNullOrEmpty(arr) == true || DataProcessing.StringToIntArray(arr).length <= 0){
					throw new NullException("Array is empty. Please type it");
				}
            	int[] array = DataProcessing.StringToIntArray(arr);
            	int length = array.length;
				if (length >SortingScreen.MAX_NUMBER) {
					throw new OutOfBoundException("The array must has under "+SortingScreen.MAX_NUMBER+" components");
				}
				if (ArrayUtils.max(array) > window.getMaxValue()) {
					throw new OutOfBoundException("The the maximum number of the array is "+window.getMaxValue());
				}
				else {
					window.updateMainArray(array);
				}
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
				window.setSorting(true);
				window.getErrorLabel().setVisible(false);
				window.getAlgo().sort();
				window.setStep(window.getAlgo().getNumSteps());
				window.setMainArrayStep(window.getAlgo().getArrayLog());
				window.setSubArrayStep(window.getAlgo().getTempLog());
				window.setAnimationArrayStep(window.getAlgo().getPointerLog());
				window.setInfoArrayStep(window.getAlgo().getGuideLog());
				window.getProcessSlider().setMaximum(window.getStep());
				if (window.isPlay()) {
				   window.getTimer().stop();
				   window.getTimer().start();}	
			}
			break;
		}
	}
}
