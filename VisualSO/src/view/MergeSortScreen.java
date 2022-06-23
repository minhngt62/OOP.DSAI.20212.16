package view;


import java.awt.Graphics;
import javax.swing.JPanel;

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
				int index = 0;
				for (int i:array) {
					g.setColor(myPINK);
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
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				width =(int) getWidth()/array.length;
				if (max(array) !=0) {
				height=(int) getHeight()/max(mainArray);}
				else {height = 0;}
				x = (getWidth()- min(width,60+padding)*array.length)/2;
				int index = 0;
				for (int i:array) {
					g.setColor(myGREEN);
				    g.fillRect(x, y-i*height+ getHeight(),min(width-padding,60),i*height);
				    x += min(width,60+padding) ;
				}
				x = 0;
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