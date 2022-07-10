package visualso.listener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import visualso.view.SortingScreen;

public class DemonstrateListener extends MyActionListener{
	public DemonstrateListener(SortingScreen window) {
		super(window);
	}
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
