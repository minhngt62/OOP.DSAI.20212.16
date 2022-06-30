package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

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

import sorting.SortingAlgorithm;
import util.ArrayUtils;
import util.RandomArray;
import view.SortingScreen.Visualizer;
import controller.SortingController;

public abstract class SortingScreen extends BaseScreen {
	protected int[] mainArray ;
	
	protected int[][] mainArrayStep;
	protected int[][] subArrayStep;
	protected int[][] animationArrayStep;
	protected String[] infoArrayStep;

	protected int step;
	protected String sortInfo;
	protected int defaultSpeed = 50;
	protected boolean isPlay = true;    //if playing
	protected boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	
	SortingAlgorithm algo;
	
	JLayeredPane visualizer;
	JPanel container;
    Visualizer main;
    Visualizer sub;
	JPanel animation;
	MySlider processSlider;
	Timer timer;
	DemonstratePane demonstratePane;
	MySideButton btnCreate;
	MySideButton btnRandom;
	MyLabel A;
	MyTextField inputArrayField;
	MySideButton btnGo;
	MySideButton btnSort;
	Color color;
	
	public SortingScreen(int[] array) {
		super();
		this.mainArray = array;
		controller = new SortingController(this);
		if (mainArray == null){
			mainArray = RandomArray.random_array((new Random()).nextInt(100)+1);}
		add(createCenter());
		add(createRightSideBar(), BorderLayout.EAST);
		add(createLeftSideBar(), BorderLayout.WEST);
		add(createBottom(), BorderLayout.SOUTH);
		addComponentListener(((SortingController)controller).new WindowResize());
		setVisible(true);
	}
	
	JLayeredPane createCenter() {
		//VISUALIZER
		visualizer = new JLayeredPane();
		container = new JPanel();
		container.setLayout(null);
		main = new Visualizer(mainArray);
		container.add(main);
		sub =  new Visualizer(mainArray);
		container.add(sub);
		animation= new JPanel();
		visualizer.add(container, JLayeredPane.DEFAULT_LAYER);
		//LEFTSIDEBAR
		//create button "Create(A)" belong to generate data button
		btnCreate = new MySideButton(150,33,myBLUE,"Create(A)",SwingConstants.LEFT);
		btnCreate.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		visualizer.add(btnCreate,JLayeredPane.MODAL_LAYER);
		//random button belong to create button
		btnRandom = new MySideButton(78,28,myBLUE,"Random",SwingConstants.CENTER);
		visualizer.add(btnRandom,JLayeredPane.MODAL_LAYER);
		//A= label belong to create button
		A = new MyLabel("A =");
		visualizer.add(A,JLayeredPane.MODAL_LAYER);
		//input array belong to create button
		inputArrayField = new MyTextField();
		visualizer.add(inputArrayField,JLayeredPane.MODAL_LAYER);
		//go button belong to create button
		btnGo = new MySideButton(50,28,myBLUE,"Go",SwingConstants.CENTER);
		visualizer.add(btnGo,JLayeredPane.MODAL_LAYER);
		//sort button belong to generate data button
		btnSort =new MySideButton(150,32,myBLUE,"Sort",SwingConstants.LEFT);
		btnSort.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		visualizer.add(btnSort,JLayeredPane.MODAL_LAYER);
		//RIGHTSIDEBAR
		demonstratePane =  new DemonstratePane(380,65,myGREEN, sortInfo);
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
		processSlider = new MySlider(JSlider.HORIZONTAL,0,0,0,350,20,((SortingController)controller).new ProgressSliderListener());
		processSlider.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		timer =new Timer(1000-defaultSpeed*10,((SortingController)controller).new TimerListener());
		
		//play button
		Icon pauseIcon= new ImageIcon(new ImageIcon(directory+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		MyControlButton btnPlay = new MyControlButton(30,45,Color.BLACK,pauseIcon,"Play");
		//forward button
		Icon forwardIcon = new ImageIcon(new ImageIcon(directory +"\\resource\\forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnForward = new MyControlButton(30,45,Color.BLACK,forwardIcon,"Forward");
		//backward button
		Icon backwardIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnBackward = new MyControlButton(30,45,Color.BLACK,backwardIcon,"Backward");
		btnBackward.setIcon(backwardIcon);		
		//end button
		Icon endIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnEnd = new MyControlButton(30,45,Color.BLACK,endIcon,"End");
		//start button
		Icon startIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyControlButton btnStart = new MyControlButton(30,45,Color.BLACK,startIcon,"Start");
				
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
		MySlider speedSlider = new MySlider(JSlider.HORIZONTAL,0,100,defaultSpeed,150,20,((SortingController)controller).new SpeedSliderListener(count));

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
		btnHelp.addActionListener(((SortingController)controller).new HelpAboutListener("Help", helpInfo));
		//About button
		MyButton btnAbout = new MyButton(50,45,Color.BLACK);
		btnAbout.setText("About");
		btnAbout.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnAbout.addActionListener(((SortingController)controller).new HelpAboutListener("About",aboutInfo));
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
		btnGenData.addActionListener(((SortingController)controller).new LeftSideBarBtnListener());
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
		btnDemonstrate.addActionListener(((SortingController)controller).new DemonstrateListener());
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
		public MySideButton(int x, int y, Color color, String text,int alignment) {
			super(x,y,color);
			setText(text);
			setHorizontalAlignment(alignment);
			addMouseListener(new HoverMouseAdapter(this));
			addActionListener(((SortingController)controller).new LeftSideBarBtnListener());
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
		public MyControlButton(int x, int y, Color color,Icon icon,String id) {
			super(x,y,color);
			setIcon(icon);
			setId(id);
			addActionListener(((SortingController)controller).new ControlBtnListener());
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
	
	public Visualizer main(int[] array, Color color) {
		int width =((int) getWidth()-200)/mainArray.length;
		int height;
		if (ArrayUtils.max(mainArray) !=0) {
			height=(int) 250/ArrayUtils.max(mainArray);}
			else {height = 0;}
		int padding = 5;
		Visualizer main =  new Visualizer(array) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i< array.length;i++) {
					g.setColor(color);
				    g.fillRect(i*ArrayUtils.min(width,60+padding)+(getWidth()
				    		   -ArrayUtils.min(width,60+padding)*mainArray.length)/2,
				    		   -getArray()[i]*height
				    		   + getHeight(),ArrayUtils.min(width-padding,60),getArray()[i]*height);
				}
				
			}
		};
		main.setBounds(45, 30, getWidth()-200, 250);
		return main;
	}
	public abstract Visualizer sub(int[] array);
	public abstract JPanel animation(Visualizer main, Visualizer sub,int[] step);
	public abstract void updateMainArray(int[] array);
		
	public int[] getMainArray() {
		return mainArray;
	}
	public void setMainArray(int[] mainArray) {
		this.mainArray =mainArray;
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

	public int[][] getMainArrayStep() {
		return mainArrayStep;
	}

	public void setMainArrayStep(int[][] mainArrayStep) {
		this.mainArrayStep = mainArrayStep;
	}

	public int[][] getSubArrayStep() {
		return subArrayStep;
	}

	public void setSubArrayStep(int[][] subArrayStep) {
		this.subArrayStep = subArrayStep;
	}

	public int[][] getAnimationArrayStep() {
		return animationArrayStep;
	}

	public void setAnimationArrayStep(int[][] animationArrayStep) {
		this.animationArrayStep = animationArrayStep;
	}

	public String[] getInfoArrayStep() {
		return infoArrayStep;
	}

	public void setInfoArrayStep(String[] infoArrayStep) {
		this.infoArrayStep = infoArrayStep;
	}

	public JPanel getContainer1() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public abstract Color getColor();

}
