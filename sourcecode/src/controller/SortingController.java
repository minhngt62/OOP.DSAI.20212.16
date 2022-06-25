package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.BaseScreen.*;
import view.SortingScreen;

public class SortingController {
	
	public static class DemonstrateListener implements ActionListener{
		DemonstratePane demonstratePane;
		public DemonstrateListener(DemonstratePane demonstratePane) {
			super();
			this.demonstratePane = demonstratePane;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JButton )e.getSource()).getText().equals("<")) {
				((JButton )e.getSource()).setText(">");
				demonstratePane.setVisible(true);
			}
			else {
				((JButton )e.getSource()).setText("<");
				demonstratePane.setVisible(false);
			}
		}
	}
	
	

	public static class LeftSideBarBtnListener implements ActionListener{
        SortingScreen window;
  
		public LeftSideBarBtnListener(SortingScreen window) {
			super();
            this.window = window;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((JButton )e.getSource()).getText()) {
			case ">":
				((JButton )e.getSource()).setText("<");
				window.getBtnCreate().setVisible(true);
				window.getBtnSort().setVisible(true);
				break;
			case "<":
				((JButton )e.getSource()).setText(">");
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
				//TODO setProcessSlider(0) && create random data 
				window.setSorting(false);
				window.getTimer().start();
				window.getTimer().stop();
				break;
			case "Go":
				//TODO setProcessSlider(0) && update mainArray data 
				window.setInputArray(window.getInputArrayField().getText());
				window.setSorting(false);
				window.getTimer().start();
				window.getTimer().stop();
				break;
			case "Sort":
				window.setSorting(true);
				window.getTimer().stop();
				window.getTimer().start();
				break;
			}
		}
	}
	
	public static class TimerListener implements ActionListener{
		SortingScreen window;
		public TimerListener(SortingScreen window) {
			super();
			this.window =  window;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (window.isSorting() && window.getCurStep() <window.getStep() ) {				
			   window.getProcessSlider().setValue(window.getCurStep()+1);	
			}
		}
	}
	
	public static class SpeedSliderListener implements ChangeListener{
		SortingScreen window;
		JLabel count;
		public SpeedSliderListener(SortingScreen window, JLabel count) {
			super();
			this.window =window;
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
	public static class ProgressSliderListener implements ChangeListener{
		SortingScreen window;
		public ProgressSliderListener(SortingScreen window) {
			super();
			this.window =window;
		}
		@Override
		public void stateChanged(ChangeEvent e) {
	        window.setCurStep( window.getProcessSlider().getValue());
	        window.getDemonstratePane().setText(window.infoArrayStep()[window.getCurStep()]);
	        window.getVisualizer().remove(window.getMain());
	        window.getVisualizer().remove(window.getAnimation());
	        window.getVisualizer().remove(window.getSub());
		    window.setAnimation(window.animation(window.getMain(), window.getSub()
		    		, window.animationArrayStep()[window.getCurStep()],false));
	        window.setMain(window.main(window.mainArrayStep()[window.getCurStep()]));
	        window.getMain().setBounds(45, 30, window.getWidth()-200, 250);
	        
	        window.setSub(window.sub(window.subArrayStep()[window.getCurStep()]));
		    window.getSub().setBounds(45, 300, window.getWidth()-200, 250);

			window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
		    window.getVisualizer().add(window.getAnimation(), JLayeredPane.DEFAULT_LAYER);
		    window.getVisualizer().add(window.getMain(), JLayeredPane.PALETTE_LAYER);
		    window.getVisualizer().add(window.getSub(), JLayeredPane.PALETTE_LAYER);
		}
	}
	public static class ControlBtnListener implements ActionListener{
        SortingScreen window;
    	File directory = new File("").getAbsoluteFile();
        Icon pauseIcon= new ImageIcon(new ImageIcon(directory+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon playIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		public ControlBtnListener(SortingScreen window) {
			super();
            this.window = window;
		}

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
						window.getVisualizer().remove(window.getAnimation());

						window.setAnimation(window.animation(window.getMain(), window.getSub(), 
								window.animationArrayStep()[window.getCurStep()-1], true));
					    window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
					    if (window.getCurStep()<window.getStep()-1) {
					    window.getVisualizer().add(window.getAnimation(), JLayeredPane.DEFAULT_LAYER);}
			    }
				break;
			case "Backward":
				 if (window.isSorting())
					 window.getProcessSlider().setValue(window.getCurStep()-1);
					window.getVisualizer().remove(window.getAnimation());
					window.setAnimation(window.animation(window.getMain(), window.getSub(), 
								window.animationArrayStep()[window.getCurStep()], true));
					window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
					if(window.getCurStep()>0) {
					window.getVisualizer().add(window.getAnimation(), JLayeredPane.DEFAULT_LAYER);}
					
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
	public static class WindowResize extends ComponentAdapter{
		SortingScreen window;
		public WindowResize(SortingScreen window) {
			super();
			this.window =window;
		}
	    @Override
	    public void componentResized( ComponentEvent e ) {
		    	window.getBtnSort().setBounds(3, window.getHeight()-175 , 150, 32);
				window.getBtnCreate().setBounds(3, window.getHeight() -208, 150, 33);
				window.getBtnRandom().setBounds(156, window.getHeight()-205, 78,28);
				window.getA().setBounds(236, window.getHeight()-205, 28,28);
				window.getInputArrayField().setBounds(266, window.getHeight()-205, 228,28);
				window.getBtnGo().setBounds(496, window.getHeight()-205, 50,28);
				window.getDemonstratePane().setBounds(window.getWidth()-490,  window.getHeight() -208,380 , 65);
				window.getMain().setBounds(45, 30, window.getWidth()-200, 250);
				window.getSub().setBounds(45, 300, window.getWidth()-200, 250);
				window.getVisualizer().remove(window.getAnimation());
	    }
	}
}
