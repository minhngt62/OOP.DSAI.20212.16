package components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import utils.ColorUtils;

class HoverMouseAdapter extends MouseAdapter{
	JButton button;
	public HoverMouseAdapter(JButton btn) {
		this.button = btn;
	}
	public void mouseEntered(MouseEvent evt) {
	    button.setBackground(Color.BLACK);
	}
	public void mouseExited(MouseEvent evt){
		button.setBackground(ColorUtils.myBLUE);
	}
}
