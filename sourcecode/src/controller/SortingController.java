package controller;

import java.awt.Color; 
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exception.DataTypeException;
import exception.NullException;
import exception.OutOfBoundException;
import sorting.RadixSort;
import utils.ArrayUtils;
import utils.DataProcessing;
import utils.RandomArray;
import view.BaseScreen.*;
import view.SortingScreen;

public class SortingController extends BaseController{
	public SortingController(SortingScreen window) {
		super(window);
	}
	
	public class DemonstrateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JButton )e.getSource()).getText().equals("<")) {
				((JButton )e.getSource()).setText(">");
				((SortingScreen) window).getDemonstratePane().setVisible(true);
			}
			else {
				((JButton )e.getSource()).setText("<");
				((SortingScreen) window).getDemonstratePane().setVisible(false);
			}
		}
	}
	
	

	public class LeftSideBarBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((JButton )e.getSource()).getText()) {
			case ">":
				((JButton )e.getSource()).setText("<");
				((SortingScreen) window).getBtnCreate().setVisible(true);
				((SortingScreen) window).getBtnSort().setVisible(true);
				break;
			case "<":
				((JButton )e.getSource()).setText(">");
				((SortingScreen) window).getBtnCreate().setVisible(false);
				((SortingScreen) window).getBtnSort().setVisible(false);
				((SortingScreen) window).getBtnRandom().setVisible(false);
				((SortingScreen) window).getA().setVisible(false);
				((SortingScreen) window).getInputArrayField().setVisible(false);
				((SortingScreen) window).getBtnGo().setVisible(false);
				break;
			case "Create(A)":
				((SortingScreen) window).getBtnRandom().setVisible(true);
				((SortingScreen) window).getA().setVisible(true);
				((SortingScreen) window).getInputArrayField().setVisible(true);
				((SortingScreen) window).getBtnGo().setVisible(true);
				break;
			case "Random":
				if (((SortingScreen) window).getMaxValue() != 50) {
					((SortingScreen) window).updateMainArray(RandomArray.random_array((new Random()).nextInt(90)+10));
				}
				else {
					((SortingScreen) window).updateMainArray(RandomArray.random_array((new Random()).nextInt(10)+10));
				}
				((SortingScreen) window).setSorting(false);
				break;
			case "Go":
				String arr = ((SortingScreen) window).getInputArrayField().getText();
				((SortingScreen) window).setSorting(false);
	            try {
					if (DataProcessing.isNullOrEmpty(arr) == true || DataProcessing.StringToIntArray(arr).length <= 0){
						throw new NullException("Array is empty. Please type it");
					}
	            	int[] array = DataProcessing.StringToIntArray(arr);
	            	int length = array.length;
					if (length >SortingScreen.MAX_NUMBER) {
						throw new OutOfBoundException("The array must has under "+SortingScreen.MAX_NUMBER+" components");
					}
					if (ArrayUtils.max(array) > ((SortingScreen) window).getMaxValue()) {
						throw new OutOfBoundException("The the maximum number of the array is "+((SortingScreen) window).getMaxValue());
					}
					else {
						((SortingScreen) window).updateMainArray(array);
					}
				} catch (DataTypeException e1) {
					((SortingScreen) window).getErrorLabel().setText(e1.getMessage());
					
				} catch (NullException e2) {
					((SortingScreen) window).getErrorLabel().setText(e2.getMessage());
				} catch (OutOfBoundException e3) {
					((SortingScreen) window).getErrorLabel().setText(e3.getMessage());
				} catch (Exception e4) {
					((SortingScreen) window).getErrorLabel().setText(e4.getMessage());

				}finally {
					((SortingScreen) window).getErrorLabel().setVisible(true);
					((SortingScreen) window).repaint();
				}
				break;
			case "Sort":
				((SortingScreen) window).setSorting(true);
				((SortingScreen) window).getErrorLabel().setVisible(false);
				if (((SortingScreen) window).isPlay()) {
				   ((SortingScreen) window).getTimer().stop();
				   ((SortingScreen) window).getTimer().start();}		
				break;
			}
		}
	}
	
	public class TimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((SortingScreen) window).isSorting() && ((SortingScreen) window).getCurStep() <((SortingScreen) window).getStep() ) {				
			   ((SortingScreen) window).getProcessSlider().setValue(((SortingScreen) window).getCurStep()+1);	
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
			   ((SortingScreen) window).setDefaultSpeed(((JSlider) e.getSource()).getValue());
			   count.setText(Integer.toString(((SortingScreen) window).getDefaultSpeed()));
			   if (((SortingScreen) window).isSorting()) {
			       ((SortingScreen) window).getTimer().stop();
			       ((SortingScreen) window).getTimer().setDelay(1000-((SortingScreen) window).getDefaultSpeed()*10);
			       ((SortingScreen) window).getTimer().start();
			       if (!((SortingScreen) window).isPlay()) {
			    	   ((SortingScreen) window).getTimer().stop();
			       }
			   }
		}
	}
	public class ProgressSliderListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
	        ((SortingScreen) window).setCurStep( ((SortingScreen) window).getProcessSlider().getValue());
	        ((SortingScreen) window).getDemonstratePane().setText(((SortingScreen) window).getInfoArrayStep()[((SortingScreen) window).getCurStep()]);
	        
	        ((SortingScreen) window).getVisualizer().remove(((SortingScreen) window).getAnimation());
	        ((SortingScreen) window).getVisualizer().remove(((SortingScreen) window).getContainer1());
		    
		    
		    ((SortingScreen) window).setMain(((SortingScreen) window).main(((SortingScreen) window).getMainArrayStep()[((SortingScreen) window).getCurStep()],((SortingScreen) window).getColor()));
	        ((SortingScreen) window).getMain().setBounds(0, 0, ((SortingScreen) window).getWidth()-200, 250);
	        
	        ((SortingScreen) window).setSub(((SortingScreen) window).sub(((SortingScreen) window).getSubArrayStep()[((SortingScreen) window).getCurStep()]));
		    ((SortingScreen) window).getSub().setBounds(0, 270, ((SortingScreen) window).getWidth()-200, 250);
		    
		    ((SortingScreen) window).setContainer(new JPanel(null));
		    ((SortingScreen) window).getContainer1().setBounds(45, 30, ((SortingScreen) window).getWidth()-200, 522);
		    
	        ((SortingScreen) window).setAnimation(((SortingScreen) window).animation(((SortingScreen) window).getMain(), ((SortingScreen) window).getSub()
		    		, ((SortingScreen) window).getAnimationArrayStep()[((SortingScreen) window).getCurStep()]));
			((SortingScreen) window).getAnimation().setBounds(45, 30, ((SortingScreen) window).getWidth()-200, 520);
		    
		    ((SortingScreen) window).getVisualizer().add(((SortingScreen) window).getAnimation(), JLayeredPane.PALETTE_LAYER);
		    ((SortingScreen) window).getContainer1().add(((SortingScreen) window).getMain());
		    ((SortingScreen) window).getContainer1().add(((SortingScreen) window).getSub());
		    ((SortingScreen) window).getVisualizer().add(((SortingScreen) window).getContainer1(), JLayeredPane.DEFAULT_LAYER);
		}
	}
	public class ControlBtnListener implements ActionListener{
        Icon pauseIcon= new ImageIcon(new ImageIcon(((SortingScreen) window).getDirectory()+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon playIcon = new ImageIcon(new ImageIcon(((SortingScreen) window).getDirectory()+"\\resource\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((MyButton )e.getSource()).getId()) {
			case "Play":
				if (((SortingScreen) window).isSorting()) {
					if (!((SortingScreen) window).isPlay()) {
						((JButton)e.getSource()).setIcon(pauseIcon);
						((SortingScreen) window).setPlay(true);
						((SortingScreen) window).getTimer().start();
					}
					else {
						((JButton)e.getSource()).setIcon(playIcon);
						((SortingScreen) window).setPlay(false);
						((SortingScreen) window).getTimer().stop();
					}
				}
				break;
			case "Forward":
				if (((SortingScreen) window).isSorting()) {
					    ((SortingScreen) window).getProcessSlider().setValue(((SortingScreen) window).getCurStep()+1);
			    }
				break;
			case "Backward":
				 if (((SortingScreen) window).isSorting())
					 ((SortingScreen) window).getProcessSlider().setValue(((SortingScreen) window).getCurStep()-1);
				break;
			case "End":
				if (((SortingScreen) window).isSorting()) {
					((SortingScreen) window).getProcessSlider().setValue(((SortingScreen) window).getStep());
					((SortingScreen) window).getVisualizer().remove(((SortingScreen) window).getAnimation());
					}
				break;
			case "Start":
				if (((SortingScreen) window).isSorting()) {
					((SortingScreen) window).getProcessSlider().setValue(0);
					if(!((SortingScreen) window).isPlay()) {
					   ((SortingScreen) window).getVisualizer().remove(((SortingScreen) window).getAnimation());
					}
				}
				break;
			}
		}
	}
	public class WindowResize extends ComponentAdapter{
	    @Override
	    public void componentResized( ComponentEvent e ) {
		    	((SortingScreen) window).getBtnSort().setBounds(3, ((SortingScreen) window).getHeight()-175 , 150, 32);
				((SortingScreen) window).getBtnCreate().setBounds(3, ((SortingScreen) window).getHeight() -208, 150, 33);
				((SortingScreen) window).getBtnRandom().setBounds(156, ((SortingScreen) window).getHeight()-205, 78,28);
				((SortingScreen) window).getA().setBounds(236, ((SortingScreen) window).getHeight()-205, 28,28);
				((SortingScreen) window).getInputArrayField().setBounds(266, ((SortingScreen) window).getHeight()-205, 228,28);
				((SortingScreen) window).getBtnGo().setBounds(496, ((SortingScreen) window).getHeight()-205, 50,28);
				((SortingScreen) window).getDemonstratePane().setBounds(((SortingScreen) window).getWidth()-490,  ((SortingScreen) window).getHeight() -208,380 , 65);
				((SortingScreen) window).getAnimation().setBounds(45, 30, ((SortingScreen) window).getWidth()-200, 520);
				((SortingScreen) window).getContainer1().setBounds(45, 30, ((SortingScreen) window).getWidth()-200, 522);
				((SortingScreen) window).getMain().setBounds(0, 0, ((SortingScreen) window).getWidth()-200, 250);
				((SortingScreen) window).getSub().setBounds(0, 270, ((SortingScreen) window).getWidth()-200, 250);
				((SortingScreen) window).getErrorLabel().setBounds((((SortingScreen) window).getWidth()-450)/2, 5, 300, 20);
	    }
	}
	
}
