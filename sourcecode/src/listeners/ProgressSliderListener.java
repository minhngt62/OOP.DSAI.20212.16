package listeners;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import controller.SortingController;
import view.SortingScreen;

public class ProgressSliderListener extends MyChangeListener{
	SortingController controller;
	public ProgressSliderListener(SortingScreen window, SortingController controller) {
		super(window);
		this.controller =controller;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if( controller.getInfoArrayStep() != null) {
        window.getDemonstratePane().setText(controller.getInfoArrayStep()[controller.getCurStep()]);
        
        window.getVisualizer().remove(window.getAnimation());
        window.getVisualizer().remove(window.getContainer1());
	    
	    
	    window.setMain(window.main(controller.getMainArrayStep()[controller.getCurStep()],window.getColor()));
        window.getMain().setBounds(0, 0, window.getWidth()-200, 250);
        
        window.setSub(window.sub(controller.getSubArrayStep()[controller.getCurStep()]));
	    window.getSub().setBounds(0, 270, window.getWidth()-200, 250);
	    
	    window.setContainer(new JPanel(null));
	    window.getContainer1().setBounds(45, 30, window.getWidth()-200, 522);
	    
        window.setAnimation(window.animation(window.getMain(), window.getSub()
	    		, controller.getAnimationArrayStep()[controller.getCurStep()]));
		window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
	    
	    window.getVisualizer().add(window.getAnimation(), JLayeredPane.PALETTE_LAYER);
	    window.getContainer1().add(window.getMain());
	    window.getContainer1().add(window.getSub());
	    window.getVisualizer().add(window.getContainer1(), JLayeredPane.DEFAULT_LAYER);
	    controller.setCurStep( window.getProcessSlider().getValue());
		}
	}
}
