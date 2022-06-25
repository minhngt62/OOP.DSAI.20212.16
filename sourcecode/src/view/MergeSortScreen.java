package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MergeSortScreen extends SortingScreen{
	
	@Override
	public String[] infoArrayStep() {
		return infoArrayStep;
	}
	@Override
	public int[][] mainArrayStep(){
		return mainArrayStep;
	}
	@Override
	public int[][] subArrayStep(){
		return subArrayStep;
	}
	public int[][] animationArrayStep(){
		return animationArrayStep;
	}
	
	int x = 0;
	int y = 0;
	int width;
	int height;
	int padding = 5;
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
					g.setColor(myPINK);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				}
				x = 0;
			}
			
		};
		main.setBounds(45, 30, getWidth()-200, 250);
		main.setBackground(new Color(0,0,0,0));
		return main;
	}
	
	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				x = (getWidth()- min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(myGREEN);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				}
				x = 0;
		    }
	     };
		sub.setBounds(45, 300, getWidth()-200, 250);
		sub.setBackground(new Color(0,0,0,0));
	    return sub;
	}
	@Override
	public JPanel animation(Visualizer main, Visualizer sub, int[] step,boolean reverse) {
		int width =(int) main.getWidth()/mainArray.length;
		int height;
		if (max(mainArray) !=0) {
			height=(int) main.getHeight()/max(mainArray);}
			else {height = 0;}
		int padding = 5;
		JPanel animation =  new JPanel() {
			int mainIndex;
			int y = 0;
			int ys = 0;
			int speedYs = -10;
			int speedY = 10;
			int delay;
			Timer t = new Timer(0, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (y> 238) {
						speedY =0;
						setVisible(false);
					}
					if (ys<-238) {
						speedYs = 0;
						setVisible(false);
					}
					y+=speedY;
					ys+=speedYs;
					repaint();
				}
			});
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 1; i< step.length+1;i++) {
					if(step[i-1] != 0) {
						mainIndex =i-1;
						delay = (int) ((speedY)*(1000-defaultSpeed*10)/238);
						if (reverse) {
							  g.setColor(myGREEN);
						      g.fillRect(mainIndex*(60+padding)+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, y-sub.getArray()[mainIndex]*height+ main.getHeight(),min(width-padding,60),sub.getArray()[mainIndex]*height);
						      g.setColor(myGREEN);
						      g.fillRect(mainIndex*(60+padding)+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, ys-main.getArray()[mainIndex]*height+ getHeight()-30, min(width-padding,60),main.getArray()[mainIndex]*height);
						}
						else {
							 g.setColor(myGREEN);
						     g.fillRect(mainIndex*(60+padding)+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, y-main.getArray()[mainIndex]*height+ main.getHeight(),min(width-padding,60),main.getArray()[mainIndex]*height);
						     g.setColor(myGREEN);
						     g.fillRect(mainIndex*(60+padding)+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, ys-sub.getArray()[mainIndex]*height+ getHeight()-30, min(width-padding,60),sub.getArray()[mainIndex]*height);			     
						}

					}
					
				}
				t.start();
				t.setDelay(delay);
		    }
	     };
	    return animation;
	}
}
