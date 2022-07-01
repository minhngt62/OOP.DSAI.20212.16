package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import sorting.CountingSort;
import utils.ArrayUtils;

public class MergeSortScreen extends SortingScreen{
	public MergeSortScreen(int[] array) {
		super(array);
		int[] array_copy = mainArray.clone();
		algo = new CountingSort(array_copy);
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
			int x = 0;
			int y = 0;
			int width;
			int height;
			int padding = 5;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				if (ArrayUtils.max(array) !=0) {
				height=(int) getHeight()/ArrayUtils.max(mainArray);}
				else {height = 0;}
				x = (getWidth()- ArrayUtils.min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(myGREEN);
				    g.fillRect(x, y-i*height+ getHeight(),ArrayUtils.min(width-padding,60),i*height);
				    x += ArrayUtils.min(width,60+padding) ;
				}
				x = 0;
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
		if (ArrayUtils.max(mainArray) !=0) {
			height=(int) main.getHeight()/ArrayUtils.max(mainArray);}
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
						if (i == 1) {
							  g.setColor(myGREEN);
						      g.fillRect(mainIndex*(60+padding)+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, y-sub.getArray()[mainIndex]*height+ main.getHeight(),ArrayUtils.min(width-padding,60),sub.getArray()[mainIndex]*height);
						      g.setColor(myGREEN);
						      g.fillRect(mainIndex*(60+padding)+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, ys-main.getArray()[mainIndex]*height+ getHeight()-30, ArrayUtils.min(width-padding,60),main.getArray()[mainIndex]*height);
						}
						else {
							 g.setColor(myGREEN);
						     g.fillRect(mainIndex*(60+padding)+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, y-main.getArray()[mainIndex]*height+ main.getHeight(),ArrayUtils.min(width-padding,60),main.getArray()[mainIndex]*height);
						     g.setColor(myGREEN);
						     g.fillRect(mainIndex*(60+padding)+(main.getWidth()- ArrayUtils.min(width,60+padding)*mainArray.length)/2, ys-sub.getArray()[mainIndex]*height+ getHeight()-30, ArrayUtils.min(width-padding,60),sub.getArray()[mainIndex]*height);			     
						}

					}
					
				}
				t.start();
				t.setDelay(delay);
		    }
	     };
	    return animation;
	}
	@Override
	public void updateMainArray(int[] array) {
		MergeSortScreen a = new MergeSortScreen(array);
		if (this.getExtendedState()==MAXIMIZED_BOTH) {
			a.setExtendedState(MAXIMIZED_BOTH);
		}
		else {
		    a.setSize(this.getWidth(),this.getHeight()); }
		dispose();
	}
	@Override
	public Color getColor() {
		return myPINK;
	}
}
