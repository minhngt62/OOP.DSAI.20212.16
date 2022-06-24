package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.SortingScreen.Visualizer;

public class CountingSortScreen extends SortingScreen {
	int x = 0;
	int y = 0;
	int width;
	int height;
	int padding = 5;
	@Override
	public String[] infoArrayStep() {
		return infoArrayStep1;
	}
	@Override
	public int[][] mainArrayStep(){
		return mainArrayStep1;
	}
	@Override
	public int[][] subArrayStep(){
		return subArrayStep1;
	}
	@Override
	public Visualizer main(int[] array) {
		Visualizer main =  new Visualizer(array) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				x = (getWidth()- min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(myORANGE);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				    
				}
				x = 0;
			}
		};
		return main;
	}
	
	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x =30;
			int counter = 1;
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
				counter = 1;
		    }
	     };
	    return sub;
	}
	@Override
	public JPanel animation(Visualizer main,Visualizer sub,int[] step) {
		JPanel animation =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
			
		    }
	     };
	    return animation;
	}
	@Override
	public JPanel reverseAnimation(Visualizer main,Visualizer sub,int[] step) {
		JPanel animation =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
					
		    }
	     };
	    return animation;
	}

}
