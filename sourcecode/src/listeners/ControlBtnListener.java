package listeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import components.MyButton;
import view.SortingScreen;

public class ControlBtnListener extends MyActionListener{
	public ControlBtnListener(SortingScreen window) {
		super(window);
	}
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
