package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class RadixSortScreen extends SortingScreen {
	int x = 0;
	int y = 0;
	int width;
	int height;
	int padding = 5;
	@Override
	public String[] infoArrayStep() {
		return infoArrayStep2;
	}
	@Override
	public int[][] mainArrayStep(){
		return mainArrayStep2;
	}
	@Override
	public int[][] subArrayStep(){
		return subArrayStep2;
	}
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
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				x = (getWidth()- min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(myYELLOW);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				    
				}
				x = 0;
			}
		};
		return main;
	}
	
	@Override
	public JPanel sub(int[] array) {
		JPanel sub =  new JPanel() {
			int x =30;
			int counter = 0;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				for (int i: array) {
					g.setColor(Color.GRAY);
				    g.fillRect(x, y-i*height+ getHeight()-30,60,i*height);
				    g.setColor(Color.BLACK);
				    g.drawLine(x,getHeight()-30 , x+60,getHeight()-30);
				    g.drawString(Integer.toString(counter), x+30, getHeight()-10);
				    x += 60+padding ;
				    counter++;
				}
				x = 30;
		    }
	     };
	    return sub;
	}
	@Override
	public JPanel animation(int[] position1, int[] position2) {
		JPanel animation =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
			
		    }
	     };
	    return animation;
	}
}
