package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import sorting.RadixSort;
import utils.ArrayUtils;

public class RadixSortScreen extends SortingScreen {
	
	public RadixSortScreen(int[] array) {
		super(array);
		int[] array_copy = mainArray.clone();
		algo = new RadixSort(array_copy);
		algo.sort();
		step = algo.getNumSteps();
		mainArrayStep = algo.getArrayLog();
		subArrayStep = algo.getTempLog();
		animationArrayStep = algo.getPointerLog();
		infoArrayStep = algo.getGuideLog();
		processSlider.setMaximum(step-1);
	}
	
	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x =30;
			int padding  = 5;
			int height = 0;
			int counter = 0;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (ArrayUtils.max(array) !=0) {
				height=(int) getHeight()/mainArray.length;}
				else {height = 0;}
				for (int i: array) {
					Font oldFont = g.getFont(); 
					g.setColor(myYELLOW);
				    g.fillRect(x, getHeight()-60,60,30);
				    g.setColor(Color.BLACK);
				    g.drawLine(x,getHeight()-30 , x+60,getHeight()-30);
				    g.drawString(Integer.toString(counter), x+30, getHeight()-10);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(i), x+28, getHeight()-40);
				    g.setFont(oldFont);
				    x += 60+padding ;
				    counter++;
				}
				x = 30;
				counter = 0;
		    }
	     };
	    sub.setBounds(45, 300, getWidth()-200, 250);
	    return sub;
	}
	@Override
	public JPanel animation(Visualizer main, Visualizer sub, int[] step) {
		int width =(int) main.getWidth()/mainArray.length;
		int height;
		if (ArrayUtils.max(mainArray) !=0) {
			height=(int) main.getHeight()/ArrayUtils.max(mainArray);}
			else {height = 0;}
		int padding = 5;
		JPanel animation =  new JPanel() {
			int mainIndex;
			int subIndex;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(step[0] >= 0&& step[1]>=0) {
				    mainIndex =step[0];
				    subIndex = step[1];
					g.setColor(myGREEN);
				    g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -main.getArray()[mainIndex]*height
				    		   + main.getHeight(),ArrayUtils.min(width-padding,60),main.getArray()[mainIndex]*height);
				    g.setColor(Color.red);
				    g.fillRect(subIndex*(60+padding)+30,getHeight()-60, 60,30);
				    g.setColor(Color.WHITE);
				    g.setFont(new Font("Sans", Font.BOLD, 17));
				    g.drawString(Integer.toString(sub.getArray()[subIndex]),subIndex*(60+padding)+58, getHeight()-40);
				}
				
			}
		};
		animation.setBounds(45, 30,getWidth()-200, 520);
		animation.setBackground(new Color(0,0,0,0));
		return animation;
	}
	
	@Override
	public void updateMainArray(int[] array) {
		RadixSortScreen a = new RadixSortScreen(array);
		if (this.getExtendedState()==MAXIMIZED_BOTH) {
			a.setExtendedState(MAXIMIZED_BOTH);
		}
		else {
		    a.setSize(this.getWidth(),this.getHeight()); }
		dispose();
	}	
	
	@Override
	public Color getColor() {
		return myYELLOW;
	}
//	@Override
//	public JPanel animation(Visualizer main, Visualizer sub, int[] step,boolean reverse) {
//
//		int width =(int) main.getWidth()/mainArray.length;
//		int height;
//		if (ArrayUtils.max(mainArray) !=0) {
//			height=(int) main.getHeight()/ArrayUtils.max(mainArray);}
//			else {height = 0;}
//		int padding = 5;
//		JPanel animation =  new JPanel() {
//			int mainIndex;
//			int subIndex;
//			int x = 0; 
//			int y = 0;
//			int xs =0;
//			int ys = 0;
//			int speedXs;
//			int speedYs = -10;
//			int speedX;
//			int speedY = 10;
//			int delay;
//			Timer t = new Timer(delay, new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					 speedX = speedY*Math.abs(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()-
//			                  ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
//			         speedXs = -speedYs*Math.abs(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()-
//			                   ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)/238;
//					if (Math.abs(x) >Math.abs(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()-
//							          ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
//						speedX = 0;
//					}
//					if (y> 238) {
//						speedY =0;
//						setVisible(false);
//					}
//					if (Math.abs(xs) >Math.abs(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()- 
//							ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2)) {
//						speedXs = 0;
//					}
//					if (ys<-238) {
//						speedYs = 0;
//						setVisible(false);
//					}
//					if (mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()-
//			                   ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2>0) {
//						x-=speedX;
//						xs+=speedXs;
//					}
//					if(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()-
//			                   ArrayUtils.min(width,60+padding)*mainArray.length)/2-subIndex*(60+padding)-30-2<0) {
//						x +=speedX;
//						xs -= speedXs;
//					}
//					y+=speedY;
//					ys+=speedYs;
//					repaint();
//				}
//			});
//			@Override
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//					if(step[0] >= 0&& step[1]>=0) {
//						mainIndex =step[0];
//						subIndex = step[1];
//						delay = (int) ((speedY+1)*(1000-defaultSpeed*10)/Math.sqrt(Math.pow(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()- 
//								ArrayUtils.min(width,60+padding)*mainArray.length)/2,2)+Math.pow(subIndex*(60+padding)-30-2,2)));
//						if (reverse) {
//							  g.setColor(Color.RED);
//						      g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+x+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, y-sub.getArray()[subIndex]*height+ main.getHeight(),ArrayUtils.min(width-padding,60),sub.getArray()[subIndex]*height);
//						      g.setColor(myGREEN);
//						      g.fillRect(subIndex*(60+padding)+xs+30, ys-main.getArray()[mainIndex]*height+ getHeight()-30, 60,main.getArray()[mainIndex]*height);
//						}
//						else {
//							 g.setColor(myGREEN);
//						     g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+x+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, y-main.getArray()[mainIndex]*height+ main.getHeight(),ArrayUtils.min(width-padding,60),main.getArray()[mainIndex]*height);
//						     g.setColor(Color.RED);
//						     g.fillRect(subIndex*(60+padding)+xs+30, ys-sub.getArray()[subIndex]*height+ getHeight()-30, 60,sub.getArray()[subIndex]*height);			     
//						}
//						t.start();
//						t.setDelay(delay);
//					}
//				}
//	     };
//	    return animation;
//	}
}
