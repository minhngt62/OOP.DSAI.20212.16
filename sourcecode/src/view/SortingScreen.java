package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeListener;

import controller.SortingController.*;
import controller.HomeController.*;

public abstract class SortingScreen extends BaseScreen {
	protected int[] mainArray = {1,3,9,4}; // make up waiting for data package
	
	//Example for data needed from sorting package
	//Merge sort
	protected int[][] mainArrayStep = {{1,3,9,4},{0,0,9,4},{0,0,9,4},{1,3,9,4},{1,3,0,0},{1,3,0,0},{1,3,4,9}};
	protected int[][] subArrayStep = {{0,0,0,0},{1,3,0,0},{1,3,0,0},{0,0,0,0},{0,0,9,4},{0,0,4,9},{0,0,0,0}};
	protected int[][] animationArrayStep = {{1,1,0,0},{0,0,0,0},{1,1,0,0},{0,0,1,1},{0,0,0,0},{0,0,0,0},{0,0,1,1},{0,0,0,0}};
	protected String[] infoArrayStep = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7"};
	//Counting sort
	protected int[][] mainArrayStep1 = {{1,3,9,4},{0,3,9,4},{0,0,9,4},{0,0,0,4},{0,0,0,0},{1,0,0,0},{1,3,0,0},{1,3,4,0},{1,3,4,9}};
	protected int[][] subArrayStep1 = {{0,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,1},{1,0,1,1,0,0,0,0,1},{0,0,1,1,0,0,0,0,1},{0,0,0,1,0,0,0,0,1},{0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0}};
	protected int[][] animationArrayStep1 = {{1,0,0,0},{0,3,0,0},{0,0,9,0},{0,0,0,4},{1,0,0,0},{0,3,0,0},{0,0,4,0},{0,0,0,9},{0,0,0,0}};
	protected String[] infoArrayStep1 = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8","Step 9"};
	//Radix sort
	protected int[][] mainArrayStep2 = {{1,3,9,4},{0,3,9,4},{0,0,9,4},{0,0,0,4},{0,0,0,0},{1,0,0,0},{1,3,0,0},{1,3,4,0},{1,3,4,9}};
	protected int[][] subArrayStep2 = {{0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,1},{0,1,0,1,1,0,0,0,0,1},{0,0,0,1,1,0,0,0,0,1},{0,0,0,0,1,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0}};
	protected int[][] animationArrayStep2 = {{2,0,0,0},{0,4,0,0},{0,0,10,0},{0,0,0,5},{2,0,0,0},{0,4,0,0},{0,0,5,0},{0,0,0,10},{0,0,0,0}};
	protected String[] infoArrayStep2 = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8","Step 9"};
	
	protected int step = mainArrayStep().length;
	protected String inputArray;
	protected String sortInfo = "Nước Việt Nam có quyền hưởng tự do và độc lập, và sự thật đã thành một nước tự do độc lập.";
	protected int defaultSpeed = 50;
	protected boolean isPlay = true;    //if playing
	protected boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	
	JLayeredPane visualizer = new JLayeredPane();
    Visualizer main= main(mainArray);
    Visualizer sub =  sub(subArrayStep()[0]);
	JPanel animation = animation(main(mainArrayStep()[0]), sub(subArrayStep()[0]), animationArrayStep()[0],false);
	MySlider processSlider = new MySlider(JSlider.HORIZONTAL,0,step-1,0,350,20,new ProgressSliderListener(this));
	Timer timer =new Timer(1000-defaultSpeed*10,new TimerListener(this));
	DemonstratePane demonstratePane =  new DemonstratePane(380,65,myGREEN, sortInfo);
	MySideButton btnCreate = new MySideButton(150,33,myBLUE,"Create(A)",SwingConstants.LEFT,this);
	MySideButton btnRandom = new MySideButton(78,28,myBLUE,"Random",SwingConstants.CENTER,this);
	MyLabel A = new MyLabel("A =");
	MyTextField inputArrayField = new MyTextField();
	MySideButton btnGo = new MySideButton(50,28,myBLUE,"Go",SwingConstants.CENTER,this);
	MySideButton btnSort =new MySideButton(150,32,myBLUE,"Sort",SwingConstants.LEFT,this);
	
	public SortingScreen() {
		super();
		add(createCenter());
		add(createRightSideBar(), BorderLayout.EAST);
		add(createLeftSideBar(), BorderLayout.WEST);
		add(createBottom(), BorderLayout.SOUTH);
		addComponentListener( new WindowResize(this));
		setVisible(true);
	}
	
	JLayeredPane createCenter() {
		//VISUALIZER
		visualizer.setPreferredSize(new Dimension(WIDTH-90,HEIGHT-90));
		visualizer.add(main, JLayeredPane.PALETTE_LAYER);
		visualizer.add(sub, JLayeredPane.PALETTE_LAYER);
		//visualizer.add(animation, JLayeredPane.DEFAULT_LAYER);
		//LEFTSIDEBAR
		//create button "Create(A)" belong to generate data button
		btnCreate.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		//random button belong to create button
		visualizer.add(btnRandom,JLayeredPane.MODAL_LAYER);
		//A= label belong to create button
		visualizer.add(A,JLayeredPane.MODAL_LAYER);
		//input array belong to create button
		visualizer.add(inputArrayField,JLayeredPane.MODAL_LAYER);
		//go button belong to create button
		visualizer.add(btnGo,JLayeredPane.MODAL_LAYER);
		visualizer.add(btnCreate,JLayeredPane.MODAL_LAYER);
		//sort button belong to generate data button
		btnSort.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		visualizer.add(btnSort,JLayeredPane.MODAL_LAYER);
		//RIGHTSIDEBAR
		visualizer.add(demonstratePane,JLayeredPane.MODAL_LAYER);
		return visualizer;
	}
	
	JPanel createBottom() {
		//CONTROL BAR
		JPanel controlBar = new JPanel(new BorderLayout());
		controlBar.setPreferredSize(new Dimension(1000,45));
		controlBar.setBackground(Color.BLACK);

		controlBar.add(createControlPane(), BorderLayout.CENTER);
		controlBar.add(createSpeedPane(), BorderLayout.WEST);
		controlBar.add(createGuidePane(), BorderLayout.EAST);
		return controlBar;
	}
	
	JPanel createControlPane() {
		//controlpane
		JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));	
		controlPane.setBackground(Color.BLACK);
		
		//process slide
		processSlider.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
				
		//play button
		Icon pauseIcon= new ImageIcon(new ImageIcon(directory+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		MyControlButton btnPlay = new MyControlButton(30,45,Color.BLACK,pauseIcon,"Play",this);
		//forward button
		Icon forwardIcon = new ImageIcon(new ImageIcon(directory +"\\resource\\forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnForward = new MyControlButton(30,45,Color.BLACK,forwardIcon,"Forward",this);
		//backward button
		Icon backwardIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnBackward = new MyControlButton(30,45,Color.BLACK,backwardIcon,"Backward",this);
		btnBackward.setIcon(backwardIcon);		
		//end button
		Icon endIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnEnd = new MyControlButton(30,45,Color.BLACK,endIcon,"End",this);
		//start button
		Icon startIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnStart = new MyControlButton(30,45,Color.BLACK,startIcon,"Start",this);
				
		controlPane.add(btnStart);
		controlPane.add(btnBackward);
		controlPane.add(btnPlay);
		controlPane.add(btnForward);
		controlPane.add(btnEnd);
		controlPane.add(processSlider);
		
		return controlPane;
	}
	JPanel createSpeedPane() {
		//speed pane
		JPanel speedPane = new JPanel(new BorderLayout());
		JLabel count = new JLabel(Integer.toString(defaultSpeed));
		MySlider speedSlider = new MySlider(JSlider.HORIZONTAL,0,100,defaultSpeed,150,20,new SpeedSliderListener(this,count));

		speedPane.setPreferredSize(new Dimension(255,45));
		speedPane.setBackground(Color.BLACK);
		speedPane.setBorder(BorderFactory.createEmptyBorder(5, 70, 0, 0));
						
		count.setForeground(Color.WHITE);
		speedPane.add(speedSlider,BorderLayout.WEST);
		speedPane.add(count,BorderLayout.EAST);
		return speedPane;
	}
	JPanel createGuidePane() {
		//guidepane
		JPanel guidePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		guidePane.setPreferredSize(new Dimension(180,45));
		guidePane.setBackground(Color.BLACK);
		//Help button
		MyButton btnHelp = new MyButton(50,45,Color.BLACK);
		btnHelp.setText("Help");
		btnHelp.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnHelp.addActionListener(new HelpAboutListener("Help", helpInfo));
		//About button
		MyButton btnAbout = new MyButton(50,45,Color.BLACK);
		btnAbout.setText("About");
		btnAbout.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnAbout.addActionListener(new HelpAboutListener("About",aboutInfo));
		guidePane.add(btnHelp);
		guidePane.add(btnAbout);
		return guidePane;
	}
	
	JPanel createLeftSideBar() {
		//LEFTSIDEBAR 
		JPanel leftSideBar = new JPanel(new BorderLayout());
		leftSideBar.setPreferredSize(new Dimension(45,610));
		leftSideBar.setBackground(Color.BLACK);
		leftSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//Generate data button ">" belong to leftsidebar
		MyButton btnGenData = new MyButton(45,65,myBLUE);
		btnGenData.setText(">");
		btnGenData.setFont(new Font("Sora",Font.BOLD,22));
		btnGenData.addActionListener(new LeftSideBarBtnListener(this));
		leftSideBar.add(btnGenData,BorderLayout.SOUTH);
		return leftSideBar;
	}
	
	JPanel createRightSideBar() {
		//RIGHTSIDEBAR
		JPanel rightSideBar = new JPanel(new BorderLayout());
		rightSideBar.setPreferredSize(new Dimension(45,610));
		rightSideBar.setBackground(Color.BLACK);
		rightSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//demonstrate button "<" belong to rightsidebar
		MyButton btnDemonstrate = new MyButton(45,65,myGREEN);
		btnDemonstrate.setText("<");
		btnDemonstrate.setFont(new Font("Sora",Font.BOLD,22));
		btnDemonstrate.addActionListener(new DemonstrateListener(demonstratePane));
		rightSideBar.add(btnDemonstrate,"South");
		return rightSideBar;
	}
	public class MyLabel extends JLabel{
		public MyLabel(String name) {
			super(name);
			setBackground(Color.WHITE);
			setHorizontalAlignment(SwingConstants.CENTER);
			setPreferredSize(new Dimension(28,28));
			setVisible(false);
		}
	}
	public class MyTextField extends JTextField {
		public MyTextField() {
			super();
			setCaretColor(Color.WHITE);
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			setPreferredSize(new Dimension(228,28));
			setVisible(false);
		}
	}
	public class MySideButton extends MyButton{
		public MySideButton(int x, int y, Color color, String text,int alignment, SortingScreen window) {
			super(x,y,color);
			setText(text);
			setHorizontalAlignment(alignment);
			addMouseListener(new HoverMouseAdapter(this));
			addActionListener(new LeftSideBarBtnListener(window));
			setVisible(false);
		}
	}
	public class MySlider extends JSlider{
		public MySlider(int orientation, int min, int max, int value,int width, int height,ChangeListener change) {
			super(orientation, min,max,value);
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(width,height));
			addChangeListener(change);
		}
	}
	public class MyControlButton extends MyButton{
		public MyControlButton(int x, int y, Color color,Icon icon,String id,SortingScreen window) {
			super(x,y,color);
			setIcon(icon);
			setId(id);
			addActionListener(new ControlBtnListener(window));
		}
	}
	public class Visualizer extends JPanel{
		private int[] array;
		public Visualizer(int[] array) {
			super();
			this.array = array;
		}
		public int[] getArray() {
			return this.array;
		}
	}
	
	public abstract Visualizer main(int[] array);
	public abstract Visualizer sub(int[] array);
	public abstract JPanel animation(Visualizer main, Visualizer sub,int[] step, boolean reverse);
	public abstract int[][] mainArrayStep();
	public abstract int[][] subArrayStep();
	public abstract int[][] animationArrayStep();
	public abstract String[] infoArrayStep();
	
	public int max(int... array) {
		int max = array[0];
		for (int i: array) {
			if (i >max) {
				max =i;			
			}	
		}
		return max;
	}
	public int min(int ... array) {
		int min = array[0];
		for (int i: array) {
			if (i <min) {
				min =i;			
			}	
		}
		return min;
	}
	
	public void setInputArray(String a) {
		inputArray = a;
	}
	public int getDefaultSpeed() {
		return this.defaultSpeed;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getSortInfo() {
		return sortInfo;
	}
	public void setSortInfo(String sortInfo) {
		this.sortInfo = sortInfo;
	}
	public boolean isPlay() {
		return isPlay;
	}
	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}
	public boolean isSorting() {
		return isSorting;
	}
	public void setSorting(boolean isSorting) {
		this.isSorting = isSorting;
	}
	public int getCurStep() {
		return curStep;
	}
	public void setCurStep(int curStep) {
		this.curStep = curStep;
	}
	public JSlider getProcessSlider() {
		return processSlider;
	}
	public void setProcessSlider(MySlider processSlider) {
		this.processSlider = processSlider;
	}
	public String getInputArray() {
		return inputArray;
	}
	public void setDefaultSpeed(int defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}
	public MyButton getBtnCreate() {
		return btnCreate;
	}
	public void setBtnCreate(MySideButton btnCreate) {
		this.btnCreate = btnCreate;
	}
	public MyButton getBtnRandom() {
		return btnRandom;
	}
	public void setBtnRandom(MySideButton btnRandom) {
		this.btnRandom = btnRandom;
	}
	public JLabel getA() {
		return A;
	}
	public void setA(MyLabel a) {
		A = a;
	}
	public JTextField getInputArrayField() {
		return inputArrayField;
	}
	public void setInputArrayField(MyTextField inputArrayField) {
		this.inputArrayField = inputArrayField;
	}
	public MyButton getBtnGo() {
		return btnGo;
	}
	public void setBtnGo(MySideButton btnGo) {
		this.btnGo = btnGo;
	}
	public MyButton getBtnSort() {
		return btnSort;
	}
	public void setBtnSort(MySideButton btnSort) {
		this.btnSort = btnSort;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JLayeredPane getVisualizer() {
		return visualizer;
	}
	public void setVisualizer(JLayeredPane visualizer) {
		this.visualizer = visualizer;
	}
	public Visualizer getMain() {
		return main;
	}
	public void setMain(Visualizer main) {
		this.main = main;
	}
	public Visualizer getSub() {
		return sub;
	}
	public void setSub(Visualizer sub) {
		this.sub = sub;
	}
	public JPanel getAnimation() {
		return animation;
	}
	public void setAnimation(JPanel animation) {
		this.animation = animation;
	}
	public DemonstratePane getDemonstratePane() {
		return demonstratePane;
	}
	public void setDemonstratePane(DemonstratePane demonstratePane) {
		this.demonstratePane = demonstratePane;
	}
}
