package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RadixSortScreen extends SortingScreen {
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
	public Visualizer main(int[] array) {
		Visualizer main =  new Visualizer(array) {
			int x = 0;
			int y = 0;
			int width;
			int height;
			int padding = 5;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/mainArray.length;
				if (max(mainArray) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				x = (getWidth()- min(width,60+padding)*mainArray.length)/2;
				for (int i:array) {
					g.setColor(myYELLOW);
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
			int x =30;
			int y = 0;
			int padding  = 5;
			int height = 0;
			int counter = 0;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				for (int i: array) {
					g.setColor(Color.YELLOW);
				    g.fillRect(x, y-i*height+ getHeight()-30,60,i*height);
				    g.setColor(Color.BLACK);
				    g.drawLine(x,getHeight()-30 , x+60,getHeight()-30);
				    g.drawString(Integer.toString(counter), x+30, getHeight()-10);
				    x += 60+padding ;
				    counter++;
				}
				x = 30;
				counter = 0;
		    }
	     };
	    sub.setBounds(45, 300, getWidth()-200, 250);
		sub.setBackground(new Color(0,0,0,0));
	    return sub;
	}
	@Override
	public JPanel animation(Visualizer main, Visualizer sub, int[] step) {

		int width =(int) main.getWidth()/mainArray.length;
		int height;
		if (max(mainArray) !=0) {
			height=(int) main.getHeight()/max(mainArray);}
			else {height = 0;}
		int padding = 5;
		JPanel animation =  new JPanel() {
			int mainIndex;
			int subIndex;
			boolean clear = false;
			int x = 0; 
			int y = 0;
			int xs =0;
			int ys = 0;
			int speedXs;
			int speedYs = -7;
			int speedX;
			int speedY = 7;
			Timer t = new Timer((1000-defaultSpeed*10)/100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 speedX = speedY+Math.abs(mainIndex*(60+padding)+(main.getWidth()-
			                  min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
			         speedXs = -speedYs+Math.abs(mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
					if (Math.abs(x) >Math.abs(mainIndex*(60+padding)+(main.getWidth()-
							          min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
						speedX = 0;
					}
					if (y> 232) {
						speedY =0;
					}
					if (Math.abs(xs) >Math.abs(mainIndex*(60+padding)+(main.getWidth()- 
							min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
						speedXs = 0;
					}
					if (ys<-232) {
						speedYs = 0;
					}
					if (mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2>0) {
						x-=speedX;
						xs+=speedXs;
					}
					if(mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2<0) {
						x +=speedX;
						xs -= speedXs;
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
					  subIndex = step[i-1]-1;
					  g.setColor(myYELLOW);
				      g.fillRect(mainIndex*(60+padding)+x+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, y-main.getArray()[mainIndex]*height+ main.getHeight(),min(width-padding,60),main.getArray()[mainIndex]*height);
				      g.setColor(myYELLOW);
				      g.fillRect(subIndex*(60+padding)+xs+30, ys-sub.getArray()[subIndex]*height+ getHeight()-30, 60,sub.getArray()[subIndex]*height);
				      t.start();
				      
					}
				}
		    }
	     };
	    return animation;
	}
	@Override
	public JPanel reverseAnimation(Visualizer main, Visualizer sub, int[] step) {

		int width =(int) main.getWidth()/mainArray.length;
		int height;
		if (max(mainArray) !=0) {
			height=(int) main.getHeight()/max(mainArray);}
			else {height = 0;}
		int padding = 5;
		JPanel animation =  new JPanel() {
			int mainIndex;
			int subIndex;
			boolean clear = false;
			int x = 0; 
			int y = 0;
			int xs =0;
			int ys = 0;
			int speedXs;
			int speedYs = -7;
			int speedX;
			int speedY = 7;
			Timer t = new Timer((1000-defaultSpeed*10)/100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 speedX = speedY+Math.abs(mainIndex*(60+padding)+(main.getWidth()-
			                  min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
			         speedXs = -speedYs+Math.abs(mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
					if (Math.abs(x) >Math.abs(mainIndex*(60+padding)+(main.getWidth()-
							          min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
						speedX = 0;
					}
					if (y> 232) {
						speedY =0;
					}
					if (Math.abs(xs) >Math.abs(mainIndex*(60+padding)+(main.getWidth()- 
							min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
						speedXs = 0;
					}
					if (ys<-232) {
						speedYs = 0;
					}
					if (mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2>0) {
						x-=speedX;
						xs+=speedXs;
					}
					if(mainIndex*(60+padding)+(main.getWidth()-
			                   min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2<0) {
						x +=speedX;
						xs -= speedXs;
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
					  subIndex = step[i-1]-1;
					  g.setColor(myYELLOW);
				      g.fillRect(mainIndex*(60+padding)+x+(main.getWidth()- min(width,60+padding)*mainArray.length)/2, y-sub.getArray()[subIndex]*height+ main.getHeight(),min(width-padding,60),sub.getArray()[subIndex]*height);
				      g.setColor(myYELLOW);
				      g.fillRect(subIndex*(60+padding)+xs+30, ys-main.getArray()[mainIndex]*height+ getHeight()-30, 60,main.getArray()[mainIndex]*height);
				      t.start();
				      
					}
				}
		    }
	     };
	     return animation;
	}
}
