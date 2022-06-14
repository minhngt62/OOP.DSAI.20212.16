package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class CountingSortFrame extends Demonstration {
	@Override
	public JPanel main(int[] array) {
		JPanel main =  new JPanel() {
			int x = 0;
			int y = 0;
			int width;
			int height;
			int padding = 5;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				height=(int) getHeight()/max(array);
				x = (getWidth()- min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(myORANGE);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				    
				}
				x = 0;
			}
		};
		//main.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return main;
	}
	
	@Override
	public JPanel sub(int[] array) {
		JPanel sub =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
			
		    }
	     };
	    //sub.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    return sub;
	}
	@Override
	public JPanel animation(int[] array) {
		JPanel animation =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
			
		    }
	     };
	    //animation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    return animation;
	}

}
