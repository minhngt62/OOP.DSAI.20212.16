package listeners;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import view.SortingScreen;

public class ProgressSliderListener extends MyChangeListener{
	public ProgressSliderListener(SortingScreen window) {
		super(window);
	}
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
