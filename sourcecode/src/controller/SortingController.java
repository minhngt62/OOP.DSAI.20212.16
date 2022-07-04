package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import components.MyButton;
import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;
import utils.ArrayUtils;
import utils.DataProcessing;
import utils.RandomArray;
import view.SortingScreen;

public class SortingController{
	SortingScreen window;
	public SortingController(SortingScreen window) {
		this.window = window;
	}
	
	public class DemonstrateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JButton)e.getSource()).getText().equals("<")) {
				((JButton)e.getSource()).setText(">");
				window.getDemonstratePane().setVisible(true);
			}
			else {
				((JButton )e.getSource()).setText("<");
				window.getDemonstratePane().setVisible(false);
			}
		}
	}
	
	

	public class LeftSideBarBtnListener implements ActionListener{

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
				if (window.getMaxValue() != 40) {
					window.updateMainArray(RandomArray.random_array((new Random()).nextInt(90)+10));
				}
				else {
					window.updateMainArray(RandomArray.random_array((new Random()).nextInt(10)+10));
				}
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
				break;
			}
		}
	}
	
	public class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (window.isSorting() && window.getCurStep() <window.getStep() ) {				
			   window.getProcessSlider().setValue(window.getCurStep()+1);	
			}
		}
	}
	
	public class SpeedSliderListener implements ChangeListener{
		JLabel count;
		public SpeedSliderListener(JLabel count) {
			super();
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
	public class ProgressSliderListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
	        window.getDemonstratePane().setText(window.getInfoArrayStep()[window.getCurStep()]);
	        
	        window.getVisualizer().remove(window.getAnimation());
	        window.getVisualizer().remove(window.getContainer1());
		    
		    
		    window.setMain(window.main(window.getMainArrayStep()[window.getCurStep()],window.getColor()));
	        window.getMain().setBounds(0, 0, window.getWidth()-200, 250);
	        
	        window.setSub(window.sub(window.getSubArrayStep()[window.getCurStep()]));
		    window.getSub().setBounds(0, 270, window.getWidth()-200, 250);
		    
		    window.setContainer(new JPanel(null));
		    window.getContainer1().setBounds(45, 30, window.getWidth()-200, 522);
		    
	        window.setAnimation(window.animation(window.getMain(), window.getSub()
		    		, window.getAnimationArrayStep()[window.getCurStep()]));
			window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
		    
		    window.getVisualizer().add(window.getAnimation(), JLayeredPane.PALETTE_LAYER);
		    window.getContainer1().add(window.getMain());
		    window.getContainer1().add(window.getSub());
		    window.getVisualizer().add(window.getContainer1(), JLayeredPane.DEFAULT_LAYER);
		    window.setCurStep( window.getProcessSlider().getValue());
		}
	}
	public class ControlBtnListener implements ActionListener{
        Icon pauseIcon= new ImageIcon(new ImageIcon(window.getDirectory()+"pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon playIcon = new ImageIcon(new ImageIcon(window.getDirectory()+"play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((MyButton )e.getSource()).getId()) {
			case "Play":
				if (window.isSorting()) {
					if (!window.isPlay()) {
						((JButton)e.getSource()).setIcon(pauseIcon);
						window.setPlay(true);
						window.getTimer().start();
					}
					else {
						((JButton)e.getSource()).setIcon(playIcon);
						window.setPlay(false);
						window.getTimer().stop();
					}
				}
				break;
			case "Forward":
				if (window.isSorting()) {
					    window.getProcessSlider().setValue(window.getCurStep()+1);
			    }
				break;
			case "Backward":
				 if (window.isSorting())
					 window.getProcessSlider().setValue(window.getCurStep()-1);
				break;
			case "End":
				if (window.isSorting()) {
					window.getProcessSlider().setValue(window.getStep());
					window.getVisualizer().remove(window.getAnimation());
					}
				break;
			case "Start":
				if (window.isSorting()) {
					window.getProcessSlider().setValue(0);
					if(!window.isPlay()) {
					   window.getVisualizer().remove(window.getAnimation());
					}
				}
				break;
			}
		}
	}
	public class WindowResize extends ComponentAdapter{
	    @Override
	    public void componentResized( ComponentEvent e ) {
		    	window.getBtnSort().setBounds(3, window.getHeight()-175 , 150, 32);
				window.getBtnCreate().setBounds(3, window.getHeight() -208, 150, 33);
				window.getBtnRandom().setBounds(156, window.getHeight()-205, 78,28);
				window.getA().setBounds(236, window.getHeight()-205, 28,28);
				window.getInputArrayField().setBounds(266, window.getHeight()-205, 228,28);
				window.getBtnGo().setBounds(496, window.getHeight()-205, 50,28);
				window.getDemonstratePane().setBounds(window.getWidth()-490,  window.getHeight() -208,380 , 65);
				window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
				window.getContainer1().setBounds(45, 30, window.getWidth()-200, 522);
				window.getMain().setBounds(0, 0, window.getWidth()-200, 250);
				window.getSub().setBounds(0, 270, window.getWidth()-200, 250);
				window.getErrorLabel().setBounds((window.getWidth()-450)/2, 5, 300, 20);
	    }
	}
	
}
