package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import components.MyColor;
import components.MyLabel;
import components.Visualizer;
import sorting.MergeSort;
import utils.ArrayUtils;

public class MergeSortScreen extends SortingScreen{
	public MergeSortScreen(int[] array) {
		super(array);
		createName("MERGE SORT");
		algo = new MergeSort(mainArray);
	}


	@Override
	public Visualizer sub(int[] array) {
		Visualizer sub =  new Visualizer(array) {
			int x = 0;
			int y = 0;
			int width;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				x = (getWidth()- ArrayUtils.min(width,60+padding)*array.length)/2;
				for (int i:array) {
					g.setColor(MyColor.myPINK);
				    g.fillRect(x, y-(int)(i*unitHeight)+ getHeight(),ArrayUtils.min(width-padding,60),(int)(i*unitHeight));
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
		JPanel animation =  new JPanel() {
			int mainIndex;
			int subIndex;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(step[0] >= 0&& step[1]>=0) {
				    mainIndex =step[0];
				    subIndex = step[1];
					g.setColor(MyColor.myGREEN);
				    g.fillRect(mainIndex*ArrayUtils.min(width,60+padding)+(main.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(main.getArray()[mainIndex]*unitHeight)
				    		   + main.getHeight(),ArrayUtils.min(width-padding,60),(int)(main.getArray()[mainIndex]*unitHeight));
				    g.setColor(Color.red);
				    g.fillRect(subIndex*ArrayUtils.min(width,60+padding)+(sub.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -(int)(sub.getArray()[subIndex]*unitHeight)
				    		   + sub.getHeight()+270,ArrayUtils.min(width-padding,60),(int)(sub.getArray()[subIndex]*unitHeight));
				}
				
			}
		};
		animation.setBounds(45, 30,getWidth()-200, 520);
		animation.setBackground(new Color(0,0,0,0));
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
		return MyColor.myPINK;
	}
	public int getMaxValue() {
		return 1000;
	}
	public String newHelpInfo() {
		 return "\n\nMerge Sort\n\n\n"
				 + "Merge sort is one of the most efficient sorting algorithms. "
				 + "It is based on the divide-and-conquer strategy - breaks down a big problem into smaller,"
				 + "more manageable pieces that look similar to the initial problem. "
				 + "It then solves these subproblems recursively and puts their solutions "
				 + "together to solve the original problem. Merge sort continuously cuts down a list into multiple sublists until each has only one item, then merges those sublists into a sorted list.\n\n"
				 + "Consider the Array has size n \n\n"
				 + "Time Complexity:\n\n"
				 + "     Worst Case: O(n*log(n))\n\n"
				 + "     Best Case: O(n*log(n))\n\n"
				 + "     Average case: O(n*log(n))\n\n"
				 + "Space Complexity: O(n)\n\n"; 
	}
}
