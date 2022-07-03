package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import myswing.MyColor;
import myswing.MyLabel;
import myswing.Visualizer;
import sorting.MergeSort;
import utils.ArrayUtils;

public class MergeSortScreen extends SortingScreen{
	public MergeSortScreen(int[] array) {
		super(array);
		createName("MERGE SORT");
		int[] array_copy = mainArray.clone();
		algo = new MergeSort(array_copy);
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
					g.setColor(MyColor.myPINK);
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
				    		   -main.getArray()[mainIndex]*height
				    		   + main.getHeight(),ArrayUtils.min(width-padding,60),main.getArray()[mainIndex]*height);
				    g.setColor(Color.red);
				    g.fillRect(subIndex*ArrayUtils.min(width,60+padding)+(sub.getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -sub.getArray()[subIndex]*height
				    		   + sub.getHeight()+270,ArrayUtils.min(width-padding,60),sub.getArray()[subIndex]*height);
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
		return 1000000000;
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
