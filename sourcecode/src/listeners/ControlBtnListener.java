package listeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import components.MyButton;
import controller.SortingController;
import view.SortingScreen;

public class ControlBtnListener extends MyActionListener{
	private SortingController controller;
	public ControlBtnListener(SortingScreen window,SortingController controller) {
		super(window);
		this.controller = controller;
	}
    Icon pauseIcon= new ImageIcon(new ImageIcon(window.getDirectory()+"pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	Icon playIcon = new ImageIcon(new ImageIcon(window.getDirectory()+"play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(((MyButton )e.getSource()).getId()) {
		case "Play":
			if (controller.isSorting()) {
				if (!controller.isPlay()) {
					((JButton)e.getSource()).setIcon(pauseIcon);
					controller.setPlay(true);
					window.getTimer().start();
				}
				else {
					((JButton)e.getSource()).setIcon(playIcon);
					controller.setPlay(false);
					window.getTimer().stop();
				}
			}
			break;
		case "Forward":
			if (controller.isSorting()) {
				    window.getProcessSlider().setValue(controller.getCurStep()+1);
		    }
			break;
		case "Backward":
			 if (controller.isSorting())
				 window.getProcessSlider().setValue(controller.getCurStep()-1);
			break;
		case "End":
			if (controller.isSorting()) {
				window.getProcessSlider().setValue(controller.getStep());
				window.getVisualizer().remove(window.getAnimation());
				}
			break;
		case "Start":
			if (controller.isSorting()) {
				window.getProcessSlider().setValue(0);
				if(!controller.isPlay()) {
				   window.getVisualizer().remove(window.getAnimation());
				}
			}
			break;
		}
	}
}
