import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Screen {
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;
	private int[] Array = new int[20]; // make up waiting for data package
	//private Sorting sorting = new Sorting()  waiting for sorting package
	protected String inputArray;
	private String helpinfo = "Hello from the other side";
	private String aboutinfo = "At least i could say that i try";
	private String sortinfo = "Nước Việt Nam có quyền hưởng tự do và độc lập, và sự thật đã thành một nước tự do độc lập.";
	private int defaultspeed = 50;
	private boolean isPlay = false;    //if playing
	private boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	private String curSortType;
	
	public void run() {
		File directory = new File("").getAbsoluteFile();
		//WINDOW
		JFrame window = new JFrame("Sorting Visualizer");
		window.setSize(WIDTH,HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(true);
		
		//VISUALIZER
		Visualizer visualizer = new Visualizer(curSortType);
		visualizer.setLayout(new BorderLayout());;
		visualizer.setPreferredSize(new Dimension(WIDTH-90,HEIGHT-90));
		visualizer.setLayout(null);
		window.add(visualizer);
		
		//TOPBAR
		JPanel topbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,0));
		topbar.setBackground(Color.BLACK);
		topbar.setPreferredSize(new Dimension(1000,45));
		topbar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		window.add(topbar, "North");
		//Merge sort button
		JButton mergesortbutton = new JButton("MERGE SORT");
		mergesortbutton.setModel(new FixedStateButtonModel());
		mergesortbutton.setRolloverEnabled(false);
		mergesortbutton.setMargin(new Insets(0,0,0,0));	
		mergesortbutton.setFocusable(false);
		mergesortbutton.setBackground(Color.BLACK);
		mergesortbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mergesortbutton.setForeground(Color.GRAY);
		mergesortbutton.setFont(new Font("Sora",Font.BOLD,15));
		mergesortbutton.setPreferredSize(new Dimension(105,45));
		topbar.add(mergesortbutton);
		//Counting sort button
		JButton countingsortbutton = new JButton("COUNTING SORT");
		countingsortbutton.setModel(new FixedStateButtonModel());
		countingsortbutton.setRolloverEnabled(false);
		countingsortbutton.setMargin(new Insets(0,0,0,0));	
		countingsortbutton.setFocusable(false);
		countingsortbutton.setBackground(Color.BLACK);
		countingsortbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		countingsortbutton.setForeground(Color.GRAY);
		countingsortbutton.setFont(new Font("Sora",Font.BOLD,15));
		countingsortbutton.setPreferredSize(new Dimension(150,45));
		topbar.add(countingsortbutton);
		//Radix sort button
		JButton radixsortbutton = new JButton("RADIX SORT");
		radixsortbutton.setModel(new FixedStateButtonModel());
		radixsortbutton.setRolloverEnabled(false);
		radixsortbutton.setMargin(new Insets(0,0,0,0));	
		radixsortbutton.setFocusable(false);
		radixsortbutton.setBackground(Color.BLACK);
		radixsortbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		radixsortbutton.setForeground(Color.GRAY);
		radixsortbutton.setFont(new Font("Sora",Font.BOLD,15));
		radixsortbutton.setPreferredSize(new Dimension(100,45));
		topbar.add(radixsortbutton);
		//When click to choose sort algo
		mergesortbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergesortbutton.setForeground(Color.WHITE);
				countingsortbutton.setForeground(Color.GRAY);
				radixsortbutton.setForeground(Color.GRAY);
				//TODO change sortalgo
			}
		});
		countingsortbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergesortbutton.setForeground(Color.GRAY);
				countingsortbutton.setForeground(Color.WHITE);
				radixsortbutton.setForeground(Color.GRAY);
				//TODO change sortalgo
			}
		});
		radixsortbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergesortbutton.setForeground(Color.GRAY);
				countingsortbutton.setForeground(Color.GRAY);
				radixsortbutton.setForeground(Color.WHITE);
				//TODO change sortalgo
			}
		});
		
		//CONTROLBAR
		JPanel controlbar = new JPanel(new BorderLayout());
		controlbar.setPreferredSize(new Dimension(1000,45));
		controlbar.setBackground(Color.BLACK);
		window.add(controlbar, "South");
		
		//controlpane
		JPanel controlpane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));	
		controlpane.setBackground(Color.BLACK);
		
		//process slide
		JSlider processslide = new JSlider(JSlider.HORIZONTAL,0,Array.length,0);
		processslide.setBackground(Color.BLACK);
		processslide.setPreferredSize(new Dimension(350,20));
		processslide.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		processslide.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent e) {
		            	if (isSorting)
		            	curStep = processslide.getValue();
		     
		            }
		        });
		
		//play button
		Icon pauseicon= new ImageIcon(new ImageIcon(directory+"\\src\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		Icon playicon = new ImageIcon(new ImageIcon(directory+"\\src\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JButton playbutton = new JButton(pauseicon);
		playbutton.setModel(new FixedStateButtonModel());
		playbutton.setRolloverEnabled(false);
		playbutton.setMargin(new Insets(0,0,0,0));	
		playbutton.setFocusable(false);
		playbutton.setBackground(Color.BLACK);
		playbutton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		playbutton.setPreferredSize(new Dimension(50,45));
		
		Timer t =new Timer(1000-defaultspeed*10,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting)
				processslide.setValue(curStep+1);
			}
		});
		playbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting) {
				if (isPlay) {
					playbutton.setIcon(pauseicon);
					isPlay = false;
					t.start();
					//TODO run the visualize
				}
				else {
					playbutton.setIcon(playicon);
					isPlay = true;
					t.stop();
					//TODO pause the visualize
				}
				}
				
			}
		});
		//forward button
		Icon forwardicon = new ImageIcon(new ImageIcon(directory +"\\src\\forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		JButton forwardbutton = new JButton(forwardicon);
		forwardbutton.setModel(new FixedStateButtonModel());
		forwardbutton.setRolloverEnabled(false);
		forwardbutton.setMargin(new Insets(0,0,0,0));	
		forwardbutton.setFocusable(false);
		forwardbutton.setBackground(Color.BLACK);
		forwardbutton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		forwardbutton.setPreferredSize(new Dimension(30,45));
		forwardbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO next step
				if (isSorting)
				processslide.setValue(curStep+1);
			}
		});
		//backward button
		Icon backwardicon = new ImageIcon(new ImageIcon(directory+"\\src\\backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		JButton backwardbutton = new JButton(backwardicon);
		backwardbutton.setModel(new FixedStateButtonModel());
		backwardbutton.setRolloverEnabled(false);
		backwardbutton.setMargin(new Insets(0,0,0,0));	
		backwardbutton.setFocusable(false);
		backwardbutton.setBackground(Color.BLACK);
		backwardbutton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		backwardbutton.setPreferredSize(new Dimension(30,45));
		backwardbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO back step
				if (isSorting)
				processslide.setValue(curStep-1);
			}
		});
		//end button
		Icon endicon = new ImageIcon(new ImageIcon(directory+"\\src\\end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		JButton endbutton = new JButton(endicon);
		endbutton.setModel(new FixedStateButtonModel());
		endbutton.setRolloverEnabled(false);
		endbutton.setMargin(new Insets(0,0,0,0));	
		endbutton.setFocusable(false);
		endbutton.setBackground(Color.BLACK);
		endbutton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		endbutton.setPreferredSize(new Dimension(30,45));
		endbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO last step
				if (isSorting)
				processslide.setValue(Array.length);
			}
		});
		//start button
		Icon starticon = new ImageIcon(new ImageIcon(directory+"\\src\\start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
		JButton startbutton = new JButton(starticon);
		startbutton.setModel(new FixedStateButtonModel());
		startbutton.setRolloverEnabled(false);
		startbutton.setMargin(new Insets(0,0,0,0));	
		startbutton.setFocusable(false);
		startbutton.setBackground(Color.BLACK);
		startbutton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		startbutton.setPreferredSize(new Dimension(30,45));
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO first step
				if (isSorting)
				processslide.setValue(0);
			}
		});
		
		controlpane.add(startbutton);
		controlpane.add(backwardbutton);
		controlpane.add(playbutton);
		controlpane.add(forwardbutton);
		controlpane.add(endbutton);
		controlpane.add(processslide);
		controlbar.add(controlpane, BorderLayout.CENTER);
		//speedpane
		JPanel speedpane = new JPanel(new BorderLayout());
		speedpane.setPreferredSize(new Dimension(255,45));
		speedpane.setBackground(Color.BLACK);
		speedpane.setBorder(BorderFactory.createEmptyBorder(5, 70, 0, 0));
		JSlider speedslide = new JSlider(JSlider.HORIZONTAL,0,100,defaultspeed);
		speedslide.setPreferredSize(new Dimension(150,20));
		speedslide.setBackground(Color.BLACK);
		JLabel count = new JLabel(Integer.toString(defaultspeed));
		speedslide.addChangeListener(new ChangeListener() {
		       @Override
		       public void stateChanged(ChangeEvent e) {
		    	   
		            	defaultspeed =speedslide.getValue();
		                count.setText(Integer.toString(defaultspeed));
		                if (isSorting) {
		                t.stop();
		                t.setDelay(1000-defaultspeed*10);
		                t.start();
		                //TODO speed connect
		                }
		            }
		        });
				
		count.setForeground(Color.WHITE);
		speedpane.add(speedslide,BorderLayout.WEST);
		speedpane.add(count,BorderLayout.EAST);
		controlbar.add(speedpane, BorderLayout.WEST);
		
		//guidepane
		JPanel guidepane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		guidepane.setPreferredSize(new Dimension(180,45));
		guidepane.setBackground(Color.BLACK);
		//guidepane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		//Help button
		JButton helpbutton = new JButton("Help");
		helpbutton.setModel(new FixedStateButtonModel());
		helpbutton.setRolloverEnabled(false);
		helpbutton.setMargin(new Insets(0,0,0,0));	
		helpbutton.setFocusable(false);
		helpbutton.setBackground(Color.BLACK);
		helpbutton.setForeground(Color.WHITE);
		helpbutton.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		helpbutton.setPreferredSize(new Dimension(50,45));
		helpbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame helpframe = new JFrame("Help");
				helpframe.setSize(300,400);
				helpframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				helpframe.setLocationRelativeTo(null);
				helpframe.setResizable(false);
				JLabel helpcontent = new JLabel(helpinfo, JLabel.CENTER);
				helpframe.add(helpcontent);
				helpframe.setVisible(true);
			}
		});
		//About button
		JButton aboutbutton = new JButton("About");
		aboutbutton.setModel(new FixedStateButtonModel());
		aboutbutton.setRolloverEnabled(false);
		aboutbutton.setMargin(new Insets(0,0,0,0));	
		aboutbutton.setFocusable(false);
		aboutbutton.setBackground(Color.BLACK);
		aboutbutton.setForeground(Color.WHITE);
		aboutbutton.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		aboutbutton.setPreferredSize(new Dimension(50,45));
		aboutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame aboutframe = new JFrame("Help");
				aboutframe.setSize(300,400);
				aboutframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				aboutframe.setLocationRelativeTo(null);
				aboutframe.setResizable(false);
				JLabel aboutcontent = new JLabel(aboutinfo, JLabel.CENTER);
				aboutframe.add(aboutcontent);
				aboutframe.setVisible(true);
			}
		});
		guidepane.add(helpbutton);
		guidepane.add(aboutbutton);
		controlbar.add(guidepane, BorderLayout.EAST);
		
		//LEFTSIDEBAR
		JPanel leftsidebar = new JPanel(new BorderLayout());
		leftsidebar.setPreferredSize(new Dimension(45,610));
		leftsidebar.setBackground(Color.BLACK);
		leftsidebar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//Generate data button ">" belong to leftsidebar
		JButton gendatabutton = new JButton(">");
		gendatabutton.setModel(new FixedStateButtonModel());
		gendatabutton.setRolloverEnabled(false);
		gendatabutton.setMargin(new Insets(0,0,0,0));	
		gendatabutton.setFocusable(false);
		gendatabutton.setBackground(new Color(102,102,255));
		gendatabutton.setForeground(Color.WHITE);
		gendatabutton.setFont(new Font("Sora",Font.BOLD,22));
		gendatabutton.setPreferredSize(new Dimension(45,65));
		//create button "Create(A)" belong to generate data button
		JButton create =new JButton("Create(A)");
		create.setHorizontalAlignment(SwingConstants.LEFT);
		create.setRolloverEnabled(false);
		create.addMouseListener(new MouseAdapter() 
		      {
		         public void mouseEntered(MouseEvent evt) 
		         {
		            create.setBackground(Color.BLACK);
		         }
		         public void mouseExited(MouseEvent evt) 
		         {
		            create.setBackground(new Color(102,102,255));
		         }
		      });
		create.setModel(new FixedStateButtonModel());
		create.setFocusable(false);
		create.setBackground(new Color(102,102,255));
		create.setForeground(Color.WHITE);
		//random button belong to create button
		JButton random = new JButton("Random");
		random.setHorizontalAlignment(SwingConstants.CENTER);
		random.setRolloverEnabled(false);
		random.addMouseListener(new MouseAdapter() 
		      {
		         public void mouseEntered(MouseEvent evt) 
		         {
		            random.setBackground(Color.BLACK);
		         }
		         public void mouseExited(MouseEvent evt) 
		         {
		            random.setBackground(new Color(102,102,255));
		         }
		      });
		random.setModel(new FixedStateButtonModel());
		random.setFocusable(false);
		random.setBackground(new Color(102,102,255));
		random.setMargin(new Insets(0,0,0,0));
		random.setForeground(Color.WHITE);
		visualizer.add(random);
		random.setVisible(false);
		//when you click on random button
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO click random button
				isSorting = false;
				t.start();
				t.stop();
			}
		});
		
		//A= label belong to create button
		JLabel A = new JLabel("A =");
		A.setBackground(Color.WHITE);
		A.setHorizontalAlignment(SwingConstants.CENTER);
		visualizer.add(A);
		A.setVisible(false);
		//input array belong to create button
		JTextField inputArrayField = new JTextField();
		inputArrayField.setCaretColor(Color.WHITE);
		inputArrayField.setBackground(Color.BLACK);
		inputArrayField.setForeground(Color.WHITE);
		visualizer.add(inputArrayField);
		//TODO input Array
		inputArrayField.setVisible(false);
		//go button belong to create button
				JButton go = new JButton("Go");
				go.setHorizontalAlignment(SwingConstants.LEFT);
				go.setRolloverEnabled(false);
				go.addMouseListener(new MouseAdapter() 
				      {
				         public void mouseEntered(MouseEvent evt) 
				         {
				            go.setBackground(Color.BLACK);
				         }
				         public void mouseExited(MouseEvent evt) 
				         {
				            go.setBackground(new Color(102,102,255));
				         }
				      });
				go.setModel(new FixedStateButtonModel());
				go.setFocusable(false);
				go.setBackground(new Color(102,102,255));
				go.setForeground(Color.WHITE);
				visualizer.add(go);
				go.setVisible(false);
				//when click on go button
				go.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TODO click go button
						inputArray =inputArrayField.getText();
						isSorting = false;
						t.start();
						t.stop();
					}
				});
		// when you click create button => random button, A= label, input Array, go button
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random.setVisible(true);
				A.setVisible(true);
				inputArrayField.setVisible(true);
				go.setVisible(true);
			}
		});
		
		visualizer.add(create);
		create.setVisible(false);
		//sort button belong to generate data button
		JButton sort =new JButton("Sort");
		sort.setRolloverEnabled(false);
		sort.addMouseListener(new MouseAdapter() 
		      {
		         public void mouseEntered(MouseEvent evt) 
		         {
		            sort.setBackground(Color.BLACK);
		         }
		         public void mouseExited(MouseEvent evt) 
		         {
		            sort.setBackground(new Color(102,102,255));
		         }
		      });
		sort. setHorizontalAlignment(SwingConstants.LEFT);
		sort.setModel(new FixedStateButtonModel());
		sort.setFocusable(false);
		sort.setBackground(new Color(102,102,255));
		sort.setForeground(Color.WHITE);
		//when you click to sort button
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO click sort button
				isSorting =true;
				t.stop();
				t.start();
			}
		});
		
		visualizer.add(sort);
		sort.setVisible(false);
		//when you click to generate data button => create button, sort button
		gendatabutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (gendatabutton.getText().equals(">")) {
					gendatabutton.setText("<");
					
					create.setVisible(true);
					sort.setVisible(true);
					
				}
				else {
					gendatabutton.setText(">");
					create.setVisible(false);
					sort.setVisible(false);
					random.setVisible(false);
					A.setVisible(false);
					inputArrayField.setVisible(false);
					go.setVisible(false);
}
				
			}
		});
		leftsidebar.add(gendatabutton,"South");
		window.add(leftsidebar, BorderLayout.WEST);
		
		//RIGHTSIDEBAR
		JPanel rightsidebar = new JPanel(new BorderLayout());
		rightsidebar.setPreferredSize(new Dimension(45,610));
		rightsidebar.setBackground(Color.BLACK);
		rightsidebar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//demonstrate button "<" belong to rightsidebar
		JButton demonstratebutton = new JButton("<");
		demonstratebutton.setModel(new FixedStateButtonModel());
		demonstratebutton.setRolloverEnabled(false);
		demonstratebutton.setMargin(new Insets(0,0,0,0));	
		demonstratebutton.setFocusable(false);
		demonstratebutton.setBackground(new Color(102,255,102));
		demonstratebutton.setForeground(Color.WHITE);
		demonstratebutton.setFont(new Font("Sora",Font.BOLD,22));
		demonstratebutton.setPreferredSize(new Dimension(45,65));
		//demonstrate pane belong to demonstrate button
		JTextArea demonstratepane =  new JTextArea();
		demonstratepane.setOpaque(true);
		demonstratepane.setBackground(new Color(102,255,102));
		demonstratepane.setForeground(Color.WHITE);
		//TODO sortinfo from sorting
		demonstratepane.setText(this.sortinfo);
		demonstratepane.setEditable(false);
		demonstratepane.setFocusable(false);
		demonstratepane.setLineWrap(true);
		demonstratepane.setWrapStyleWord(true);
		demonstratepane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		visualizer.add(demonstratepane);
		demonstratepane.setVisible(false);
		//when you click on demonstrate button => demonstrate pane
		demonstratebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (demonstratebutton.getText().equals("<")) {
					demonstratebutton.setText(">");
					demonstratepane.setVisible(true);
				}
				else {
					demonstratebutton.setText("<");
					demonstratepane.setVisible(false);
				}
			}
		});
		
		rightsidebar.add(demonstratebutton,"South");
		window.add(rightsidebar, BorderLayout.EAST);
		
		// window solve for resize change
		window.addComponentListener( new ComponentAdapter() {
		    @Override
		    public void componentResized( ComponentEvent e ) {
		    	sort.setBounds(3, window.getHeight()-175 , 150, 32);
				create.setBounds(3, window.getHeight() -208, 150, 33);
				random.setBounds(156, window.getHeight()-205, 78,28);
				A.setBounds(236, window.getHeight()-205, 28,28);
				inputArrayField.setBounds(266, window.getHeight()-205, 228,28);
				go.setBounds(496, window.getHeight()-205, 50,28);
				demonstratepane.setBounds(window.getWidth()-490,  window.getHeight() -208,380 , 65);
				
		    }
		} );
		window.setVisible(true);

	}


}
