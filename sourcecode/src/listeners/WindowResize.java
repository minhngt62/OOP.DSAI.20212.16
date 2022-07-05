package listeners;


import java.awt.event.ComponentEvent;

import view.SortingScreen;

public class WindowResize extends MyComponentAdapter{
	public WindowResize(SortingScreen window) {
		super(window);
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
			window.getAnimation().setBounds(45, 30, window.getWidth()-200, 520);
			window.getContainer1().setBounds(45, 30, window.getWidth()-200, 522);
			window.getMain().setBounds(0, 0, window.getWidth()-200, 250);
			window.getSub().setBounds(0, 270, window.getWidth()-200, 250);
			window.getErrorLabel().setBounds((window.getWidth()-450)/2, 5, 300, 20);
    }
}
