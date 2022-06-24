package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.SortingController.*;
import controller.HomeController.*;

public abstract class SortingScreen extends BaseScreen {
	protected int[] mainArray = {1,3,9,4}; // make up waiting for data package
	
	
	//Example for data needed from sorting package
	//Merge sort
	protected int[][] mainArrayStep = {{1,3,9,4},{0,0,9,4},{0,0,9,4},{1,3,9,4},{1,3,0,0},{1,3,0,0},{1,3,4,9}};
	protected int[][] subArrayStep = {{0,0,0,0},{1,3,0,0},{1,3,0,0},{0,0,0,0},{0,0,9,4},{0,0,4,9},{0,0,0,0}};
	protected String[] infoArrayStep = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7"};
	//Counting sort
	protected int[][] mainArrayStep1 = {{1,3,9,4},{0,3,9,4},{0,0,9,4},{0,0,0,4},{0,0,0,0},{1,0,0,0},{1,3,0,0},{1,3,4,0},{1,3,4,9}};
	protected int[][] subArrayStep1 = {{0,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,1},{1,0,1,1,0,0,0,0,1},{0,0,1,1,0,0,0,0,1},{0,0,0,1,0,0,0,0,1},{0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0}};
	protected String[] infoArrayStep1 = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8","Step 9"};
	//Radix sort
	protected int[][] mainArrayStep2 = {{1,3,9,4},{0,3,9,4},{0,0,9,4},{0,0,0,4},{0,0,0,0},{1,0,0,0},{1,3,0,0},{1,3,4,0},{1,3,4,9}};
	protected int[][] subArrayStep2 = {{0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,1},{0,1,0,1,1,0,0,0,0,1},{0,0,0,1,1,0,0,0,0,1},{0,0,0,0,1,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0}};
	protected int[][] animationArrayStep = {{2,0,0,0},{0,4,0,0},{0,0,10,0},{0,0,0,5},{2,0,0,0},{0,4,0,0},{0,0,5,0},{0,0,0,10},{0,0,0,0}};
	protected String[] infoArrayStep2 = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6","Step 7","Step 8","Step 9"};
	
	protected int step = mainArrayStep().length;
	protected String inputArray;
	protected String sortInfo = "Nước Việt Nam có quyền hưởng tự do và độc lập, và sự thật đã thành một nước tự do độc lập.";
	protected int defaultSpeed = 50;
	protected boolean isPlay = true;    //if playing
	protected boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	
    Visualizer main= main(mainArray);
    Visualizer sub =  sub(subArrayStep()[0]);
	JPanel animation = animation(main(mainArrayStep()[0]), sub(subArrayStep()[0]), animationArrayStep[0]);
			
	public SortingScreen() {
		super();
		//VISUALIZER
		JLayeredPane visualizer = new JLayeredPane();
		visualizer.setPreferredSize(new Dimension(WIDTH-90,HEIGHT-90));
		visualizer.add(main, JLayeredPane.PALETTE_LAYER);
		visualizer.add(sub, JLayeredPane.PALETTE_LAYER);
		//visualizer.add(animation, JLayeredPane.DEFAULT_LAYER);
		add(visualizer);
		
		//RIGHTSIDEBAR
		JPanel rightSideBar = new JPanel(new BorderLayout());
		rightSideBar.setPreferredSize(new Dimension(45,610));
		rightSideBar.setBackground(Color.BLACK);
		rightSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//demonstrate button "<" belong to rightsidebar
		MyButton btnDemonstrate = new MyButton(45,65,myGREEN);
		btnDemonstrate.setText("<");
		btnDemonstrate.setFont(new Font("Sora",Font.BOLD,22));
		//demonstrate pane belong to demonstrate button
		//demonstrate pane belong to demonstrate button
		DemonstratePane demonstratePane =  new DemonstratePane(380,65,myGREEN, sortInfo);
		visualizer.add(demonstratePane,JLayeredPane.MODAL_LAYER);
		demonstratePane.setVisible(false);
		//when you click on demonstrate button => demonstrate pane
		btnDemonstrate.addActionListener(new DemonstrateListener(demonstratePane));
				
		rightSideBar.add(btnDemonstrate,"South");
		add(rightSideBar, BorderLayout.EAST);
		
		//CONTROLBAR
		JPanel controlBar = new JPanel(new BorderLayout());
		controlBar.setPreferredSize(new Dimension(1000,45));
		controlBar.setBackground(Color.BLACK);
		add(controlBar, "South");
				
		//controlpane
		JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));	
		controlPane.setBackground(Color.BLACK);
		
		//process slide
		JSlider processSlider = new JSlider(JSlider.HORIZONTAL,0,step-1,0);
		//Timer to controlpane
		Timer timer =new Timer(1000-defaultSpeed*10,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting && curStep <step ) {				
				    processSlider.setValue(curStep+1);	
				    }
					}
		});

		processSlider.setBackground(Color.BLACK);
		processSlider.setPreferredSize(new Dimension(350,20));
		processSlider.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		processSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				        curStep = processSlider.getValue();
				        demonstratePane.setText(infoArrayStep()[curStep]);
				        visualizer.remove(main);
				        visualizer.remove(animation);
					    visualizer.remove(sub);
					    main = main(mainArrayStep()[curStep]);
					    main.setBounds(45, 30, getWidth()-200, 250);
					    sub =  sub(subArrayStep()[curStep]);
					    sub.setBounds(45, 300, getWidth()-200, 250);
						animation = animation(main, sub, animationArrayStep[curStep]);
					    animation.setBounds(45, 30, getWidth()-200, 520);
					    visualizer.add(animation, JLayeredPane.DEFAULT_LAYER);
						visualizer.add(main, JLayeredPane.PALETTE_LAYER);
						visualizer.add(sub, JLayeredPane.PALETTE_LAYER);
				     }
			});
				
		//play button
		Icon pauseIcon= new ImageIcon(new ImageIcon(directory+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon playIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		MyButton btnPlay = new MyButton(30,45,Color.BLACK);
		btnPlay.setIcon(pauseIcon);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting) {
				if (!isPlay) {
					btnPlay.setIcon(pauseIcon);
					isPlay = true;
					timer.start();
					
				}
				else {
					btnPlay.setIcon(playIcon);
					isPlay = false;
					timer.stop();
					
				}
				}
						
			}
		});
		//forward button
		Icon forwardIcon = new ImageIcon(new ImageIcon(directory +"\\resource\\forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyButton btnForward = new MyButton(30,45,Color.BLACK);
		btnForward.setIcon(forwardIcon);
		btnForward.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (isSorting)
				processSlider.setValue(curStep+1);
			}
		});
		//backward button
		Icon backwardIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyButton btnBackward = new MyButton(30,45,Color.BLACK);
		btnBackward.setIcon(backwardIcon);
		btnBackward.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
			 if (isSorting)
				processSlider.setValue(curStep-1);
			    if(curStep>0) {
			    visualizer.remove(animation);
			    animation = reverseAnimation(main, sub, animationArrayStep[curStep]);
			    animation.setBounds(45, 30, getWidth()-200, 520);
			    visualizer.add(animation, JLayeredPane.DEFAULT_LAYER);}
			}
		});
		
		//end button
		Icon endIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyButton btnEnd = new MyButton(30,45,Color.BLACK);
		btnEnd.setIcon(endIcon);
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting)
				processSlider.setValue(step);
			}
		});
		//start button
		Icon startIcon = new ImageIcon(new ImageIcon(directory+"\\resource\\start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		MyButton btnStart = new MyButton(30,45,Color.BLACK);
		btnStart.setIcon(startIcon);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting)
				    processSlider.setValue(0);
				}
			});
				
		controlPane.add(btnStart);
		controlPane.add(btnBackward);
		controlPane.add(btnPlay);
		controlPane.add(btnForward);
		controlPane.add(btnEnd);
		controlPane.add(processSlider);
		controlBar.add(controlPane, BorderLayout.CENTER);
		//speed pane
		JPanel speedPane = new JPanel(new BorderLayout());
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,0,100,defaultSpeed);
		JLabel count = new JLabel(Integer.toString(defaultSpeed));
		speedPane.setPreferredSize(new Dimension(255,45));
		speedPane.setBackground(Color.BLACK);
		speedPane.setBorder(BorderFactory.createEmptyBorder(5, 70, 0, 0));
		speedSlider.setPreferredSize(new Dimension(150,20));
		speedSlider.setBackground(Color.BLACK);
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) { 	   
				   defaultSpeed =speedSlider.getValue();
				   count.setText(Integer.toString(defaultSpeed));
				   if (isSorting) {
				       timer.stop();
				       timer.setDelay(1000-defaultSpeed*10);
				       timer.start();
				       if (!isPlay) {
				    	   timer.stop();
				       }
				   }
			}
		});
						
		count.setForeground(Color.WHITE);
		speedPane.add(speedSlider,BorderLayout.WEST);
		speedPane.add(count,BorderLayout.EAST);
		controlBar.add(speedPane, BorderLayout.WEST);
				
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
		controlBar.add(guidePane, BorderLayout.EAST);
				
		//LEFTSIDEBAR
		JPanel leftSideBar = new JPanel(new BorderLayout());
		leftSideBar.setPreferredSize(new Dimension(45,610));
		leftSideBar.setBackground(Color.BLACK);
		leftSideBar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//Generate data button ">" belong to leftsidebar
		MyButton btnGenData = new MyButton(45,65,myBLUE);
		btnGenData.setText(">");
		btnGenData.setFont(new Font("Sora",Font.BOLD,22));
		
		//create button "Create(A)" belong to generate data button
		MyButton btnCreate = new MyButton(150,33,myBLUE);
		btnCreate.setText("Create(A)");
		btnCreate.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		btnCreate.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreate.addMouseListener(new HoverMouseAdapter(btnCreate));
		//random button belong to create button
		MyButton btnRandom = new MyButton(78,28,myBLUE);
		btnRandom.setText("Random");
		btnRandom.setHorizontalAlignment(SwingConstants.CENTER);
		btnRandom.addMouseListener(new HoverMouseAdapter(btnRandom));
		visualizer.add(btnRandom,JLayeredPane.MODAL_LAYER);
		btnRandom.setVisible(false);
		//when you click on random button
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSorting = false;
				timer.start();
				timer.stop();
			}
		});
				
		//A= label belong to create button
		JLabel A = new JLabel("A =");
		A.setBackground(Color.WHITE);
		A.setHorizontalAlignment(SwingConstants.CENTER);
		A.setPreferredSize(new Dimension(28,28));
		visualizer.add(A,JLayeredPane.MODAL_LAYER);
		A.setVisible(false);
		//input array belong to create button
		JTextField inputArrayField = new JTextField();
		inputArrayField.setCaretColor(Color.WHITE);
		inputArrayField.setBackground(Color.BLACK);
		inputArrayField.setForeground(Color.WHITE);
		inputArrayField.setPreferredSize(new Dimension(228,28));
		visualizer.add(inputArrayField,JLayeredPane.MODAL_LAYER);
		inputArrayField.setVisible(false);
		//go button belong to create button
		MyButton btnGo = new MyButton(50,28,myBLUE);
		btnGo.setText("Go");
		btnGo.setHorizontalAlignment(SwingConstants.CENTER);
		btnGo.addMouseListener(new HoverMouseAdapter(btnGo));
		visualizer.add(btnGo,JLayeredPane.MODAL_LAYER);
		btnGo.setVisible(false);
		//when click on go button
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArray =inputArrayField.getText();
				isSorting = false;
				timer.start();
				timer.stop();
			}
		});
		// when you click create button => random button, A= label, input Array, go button
		btnCreate.addActionListener(new BtnCreateListener(btnRandom,A,inputArrayField,btnGo));
				
		visualizer.add(btnCreate,JLayeredPane.MODAL_LAYER);
		btnCreate.setVisible(false);
		//sort button belong to generate data button
		MyButton btnSort =new MyButton(150,32,myBLUE);
		btnSort.setText("Sort");
		btnSort.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		btnSort. setHorizontalAlignment(SwingConstants.LEFT);
		btnSort.addMouseListener(new HoverMouseAdapter(btnSort));
		//when you click to sort button
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSorting =true;
				timer.stop();
				timer.start();
			}
		});
				
		visualizer.add(btnSort,JLayeredPane.MODAL_LAYER);
		btnSort.setVisible(false);
		//when you click to generate data button => create button, sort button
		btnGenData.addActionListener(new GenDataListener(btnRandom, A, inputArrayField, 
				                                         btnGo,btnCreate, btnSort));
		leftSideBar.add(btnGenData,BorderLayout.SOUTH);
		add(leftSideBar, BorderLayout.WEST);
				

		
		addComponentListener( new ComponentAdapter() {
		    @Override
		    public void componentResized( ComponentEvent e ) {
			    	btnSort.setBounds(3, getHeight()-175 , 150, 32);
					btnCreate.setBounds(3, getHeight() -208, 150, 33);
					btnRandom.setBounds(156, getHeight()-205, 78,28);
					A.setBounds(236, getHeight()-205, 28,28);
					inputArrayField.setBounds(266, getHeight()-205, 228,28);
					btnGo.setBounds(496, getHeight()-205, 50,28);
					demonstratePane.setBounds(getWidth()-490,  getHeight() -208,380 , 65);
					main.setBounds(45, 30, getWidth()-200, 250);
					sub.setBounds(45, 300, getWidth()-200, 250);
					visualizer.remove(animation);
					//animation.setBounds(45, 30, getWidth()-200, 520);
					
					
			  
		    }
		} );
		setVisible(true);
	}
	
	JPanel createCenter() {
		//VISUALIZER
		return new JPanel();
	}
	
	JPanel createBottom() {
		//CONTROL BAR
		return new JPanel();
	}
	
	JPanel createLeftSideBar() {
		//LEFTSIDEBAR 
		return new JPanel();
	}
	
	JPanel createRightSideBar() {
		//RIGHTSIDEBAR
		return new JPanel();
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
	public abstract JPanel animation(Visualizer main, Visualizer sub,int[] step);
	public abstract JPanel reverseAnimation(Visualizer main, Visualizer sub,int[] step);
	public abstract int[][] mainArrayStep();
	public abstract int[][] subArrayStep();
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

}
