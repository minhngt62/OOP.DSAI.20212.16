package myswing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class MySlider extends JSlider{
	public MySlider(int orientation, int min, int max, int value,int width, int height,ChangeListener change) {
		super(orientation, min,max,value);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width,height));
		addChangeListener(change);
	}
}

