package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class Demonstration extends Screen implements Visualizable{
	protected int[] Array = {12,12,5,45,23,67,34,16}; // make up waiting for data package
	protected int[] stepArray = new int[20];
	protected String inputArray;
	protected String helpinfo = "Hello from the other side";
	protected String aboutinfo = "At least i could say that i try";
	protected String sortinfo = "Nước Việt Nam có quyền hưởng tự do và độc lập, và sự thật đã thành một nước tự do độc lập.";
	protected int defaultspeed = 50;
	protected boolean isPlay = false;    //if playing
	protected boolean isSorting = false;  // if in sorting process, else all the manipulate button will be ignored
	protected int curStep = 0;
	protected String curSortType;
	
	//File directory
	File directory = new File("").getAbsoluteFile();
	/*GUI COMPONENT*/
	//VISUALIZER
	JLayeredPane visualizer = new JLayeredPane();
	JPanel main = main(Array);
	JPanel sub = sub(Array);
	//CONTROLBAR
	JPanel controlbar = new JPanel(new BorderLayout());
	//controlpane
	JPanel controlpane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));	
	//process slide
	JSlider processslide = new JSlider(JSlider.HORIZONTAL,0,stepArray.length,0);
	//Timer to controlpane
	Timer t =new Timer(1000-defaultspeed*10,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (isSorting)
			    processslide.setValue(curStep+1);
				}
	});
	//play button
	Icon pauseicon= new ImageIcon(new ImageIcon(directory+"\\resource\\pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	Icon playicon = new ImageIcon(new ImageIcon(directory+"\\resource\\play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	myButton btnPlay = new myButton(30,45,Color.BLACK);
	//forward button
	Icon forwardicon = new ImageIcon(new ImageIcon(directory +"\\resource\\forward.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
	myButton btnForward = new myButton(30,45,Color.BLACK);
	//backward button
	Icon backwardicon = new ImageIcon(new ImageIcon(directory+"\\resource\\backward.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
	myButton btnBackward = new myButton(30,45,Color.BLACK);
	//end button
	Icon endicon = new ImageIcon(new ImageIcon(directory+"\\resource\\end.jpg").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
	myButton btnEnd = new myButton(30,45,Color.BLACK);
	//start button
	Icon starticon = new ImageIcon(new ImageIcon(directory+"\\resource\\start.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH));
	myButton btnStart = new myButton(30,45,Color.BLACK);
	//speedpane
	JPanel speedpane = new JPanel(new BorderLayout());
	JSlider speedslide = new JSlider(JSlider.HORIZONTAL,0,100,defaultspeed);
	JLabel count = new JLabel(Integer.toString(defaultspeed));
	//guidepane
	JPanel guidepane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	//Help button
	myButton btnHelp = new myButton(50,45,Color.BLACK);
	JFrame helpframe = new JFrame("Help");
	JLabel helpcontent = new JLabel(helpinfo, JLabel.CENTER);
	//About button
	myButton btnAbout = new myButton(50,45,Color.BLACK);
	JFrame aboutframe = new JFrame("About");
	JLabel aboutcontent = new JLabel(aboutinfo, JLabel.CENTER);
	//LEFTSIDEBAR
	JPanel leftsidebar = new JPanel(new BorderLayout());
	//Generate data button ">" belong to leftsidebar
	myButton btnGendata = new myButton(45,65,myBLUE);
	//create button "Create(A)" belong to generate data button
	myButton create = new myButton(150,33,myBLUE);
	//random button belong to create button
	myButton random = new myButton(78,28,myBLUE);
	//A= label belong to create button
	JLabel A = new JLabel("A =");
	//input array belong to create button
	JTextField inputArrayField = new JTextField();
	//go button belong to create button
	myButton go = new myButton(50,28,myBLUE);
	//sort button belong to generate data button
	myButton sort =new myButton(150,32,myBLUE);
	//RIGHTSIDEBAR
	JPanel rightsidebar = new JPanel(new BorderLayout());
	//demonstrate button "<" belong to rightsidebar
	myButton btnDemonstrate = new myButton(45,65,myGREEN);
	//demonstrate pane belong to demonstrate button
	DemonstratePane demonstratepane =  new DemonstratePane(380,65,myGREEN);
			
	public Demonstration() {
		super();
		//VISUALIZER
		visualizer.setPreferredSize(new Dimension(WIDTH-90,HEIGHT-90));
		visualizer.add(main, JLayeredPane.PALETTE_LAYER);
		visualizer.add(sub, JLayeredPane.PALETTE_LAYER);
		//visualizer.add(animation(Array), JLayeredPane.DEFAULT_LAYER);
		add(visualizer);
		
		//CONTROLBAR
		controlbar.setPreferredSize(new Dimension(1000,45));
		controlbar.setBackground(Color.BLACK);
		add(controlbar, "South");
				
		//controlpane
		controlpane.setBackground(Color.BLACK);
				
		//process slide
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
		btnPlay.setIcon(pauseicon);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSorting) {
				if (isPlay) {
					btnPlay.setIcon(pauseicon);
					isPlay = false;
					t.start();
					//TODO run the visualize
				}
				else {
					btnPlay.setIcon(playicon);
					isPlay = true;
					t.stop();
					//TODO pause the visualize
				}
				}
						
			}
		});
		//forward button
		btnForward.setIcon(forwardicon);
		btnForward.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//TODO next step
			if (isSorting)
				processslide.setValue(curStep+1);
			}
		});
		//backward button
		btnBackward.setIcon(backwardicon);
		btnBackward.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//TODO next step
			if (isSorting)
				processslide.setValue(curStep-1);
			}
		});
		
		//end button
		btnEnd.setIcon(endicon);
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO last step
				if (isSorting)
				processslide.setValue(Array.length);
			}
		});
		//start button
		btnStart.setIcon(starticon);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO first step
				if (isSorting)
				    processslide.setValue(0);
				}
			});
				
		controlpane.add(btnStart);
		controlpane.add(btnBackward);
		controlpane.add(btnPlay);
		controlpane.add(btnForward);
		controlpane.add(btnEnd);
		controlpane.add(processslide);
		controlbar.add(controlpane, BorderLayout.CENTER);
		//speedpane
		speedpane.setPreferredSize(new Dimension(255,45));
		speedpane.setBackground(Color.BLACK);
		speedpane.setBorder(BorderFactory.createEmptyBorder(5, 70, 0, 0));
		speedslide.setPreferredSize(new Dimension(150,20));
		speedslide.setBackground(Color.BLACK);
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
		guidepane.setPreferredSize(new Dimension(180,45));
		guidepane.setBackground(Color.BLACK);
		//Help button
		btnHelp.setText("Help");
		btnHelp.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpframe.setSize(300,400);
				helpframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				helpframe.setLocationRelativeTo(null);
				helpframe.setResizable(false);
				helpframe.add(helpcontent);
				helpframe.setVisible(true);
			}
		});
		//About button
		btnAbout.setText("About");
		btnAbout.setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
		btnAbout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				aboutframe.setSize(300,400);
				aboutframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				aboutframe.setLocationRelativeTo(null);
				aboutframe.setResizable(false);
				aboutframe.add(aboutcontent);
				aboutframe.setVisible(true);
			}
		});
		guidepane.add(btnHelp);
		guidepane.add(btnAbout);
		controlbar.add(guidepane, BorderLayout.EAST);
				
		//LEFTSIDEBAR
		leftsidebar.setPreferredSize(new Dimension(45,610));
		leftsidebar.setBackground(Color.BLACK);
		leftsidebar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//Generate data button ">" belong to leftsidebar
		btnGendata.setText(">");
		btnGendata.setFont(new Font("Sora",Font.BOLD,22));
		
		//create button "Create(A)" belong to generate data button
		create.setText("Create(A)");
		create.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		create.setHorizontalAlignment(SwingConstants.LEFT);
		create.addMouseListener(new HoverMouseAdapter(create));
		//random button belong to create button
		random.setText("Random");
		random.setHorizontalAlignment(SwingConstants.CENTER);
		random.addMouseListener(new HoverMouseAdapter(random));
		visualizer.add(random,JLayeredPane.MODAL_LAYER);
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
		A.setBackground(Color.WHITE);
		A.setHorizontalAlignment(SwingConstants.CENTER);
		A.setPreferredSize(new Dimension(28,28));
		visualizer.add(A,JLayeredPane.MODAL_LAYER);
		A.setVisible(false);
		//input array belong to create button
		inputArrayField.setCaretColor(Color.WHITE);
		inputArrayField.setBackground(Color.BLACK);
		inputArrayField.setForeground(Color.WHITE);
		inputArrayField.setPreferredSize(new Dimension(228,28));
		visualizer.add(inputArrayField,JLayeredPane.MODAL_LAYER);
		//TODO input Array
		inputArrayField.setVisible(false);
		//go button belong to create button
		go.setText("Go");
		go.setHorizontalAlignment(SwingConstants.CENTER);
		go.addMouseListener(new HoverMouseAdapter(go));
		visualizer.add(go,JLayeredPane.MODAL_LAYER);
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
				
		visualizer.add(create,JLayeredPane.MODAL_LAYER);
		create.setVisible(false);
		//sort button belong to generate data button
		sort.setText("Sort");
		sort.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		sort. setHorizontalAlignment(SwingConstants.LEFT);
		sort.addMouseListener(new HoverMouseAdapter(sort));
		//when you click to sort button
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO click sort button
				isSorting =true;
				t.stop();
				t.start();
			}
		});
				
		visualizer.add(sort,JLayeredPane.MODAL_LAYER);
		sort.setVisible(false);
		//when you click to generate data button => create button, sort button
		btnGendata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (btnGendata.getText().equals(">")) {
					btnGendata.setText("<");
					create.setVisible(true);
					sort.setVisible(true);
							
				}
				else {
					btnGendata.setText(">");
					create.setVisible(false);
					sort.setVisible(false);
					random.setVisible(false);
					A.setVisible(false);
					inputArrayField.setVisible(false);
					go.setVisible(false);
				}
						
			}
		});
		leftsidebar.add(btnGendata,BorderLayout.SOUTH);
		add(leftsidebar, BorderLayout.WEST);
				
		//RIGHTSIDEBAR
		rightsidebar.setPreferredSize(new Dimension(45,610));
		rightsidebar.setBackground(Color.BLACK);
		rightsidebar.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		//demonstrate button "<" belong to rightsidebar
		btnDemonstrate.setText("<");
		btnDemonstrate.setFont(new Font("Sora",Font.BOLD,22));
		//demonstrate pane belong to demonstrate button
		visualizer.add(demonstratepane,JLayeredPane.MODAL_LAYER);
		demonstratepane.setVisible(false);
		//when you click on demonstrate button => demonstrate pane
		btnDemonstrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (btnDemonstrate.getText().equals("<")) {
					btnDemonstrate.setText(">");
					demonstratepane.setVisible(true);
				}
				else {
					btnDemonstrate.setText("<");
					demonstratepane.setVisible(false);
				}
			}
		});
				
		rightsidebar.add(btnDemonstrate,"South");
		add(rightsidebar, BorderLayout.EAST);
		
		addComponentListener( new ComponentAdapter() {
		    @Override
		    public void componentResized( ComponentEvent e ) {
			    	sort.setBounds(3, getHeight()-175 , 150, 32);
					create.setBounds(3, getHeight() -208, 150, 33);
					random.setBounds(156, getHeight()-205, 78,28);
					A.setBounds(236, getHeight()-205, 28,28);
					inputArrayField.setBounds(266, getHeight()-205, 228,28);
					go.setBounds(496, getHeight()-205, 50,28);
					demonstratepane.setBounds(getWidth()-490,  getHeight() -208,380 , 65);
					main.setBounds(45, 30, getWidth()-200, 250);
					sub.setBounds(45, 300, getWidth()-200, 250);
					
					
			  
		    }
		} );
		setVisible(true);
	}
	static class FixedStateButtonModel extends DefaultButtonModel  {
        @Override
        public boolean isPressed() {
            return false;
        }
    }
	class HoverMouseAdapter extends MouseAdapter{
		JButton button;
		public HoverMouseAdapter(JButton btn) {
			this.button = btn;
		}
		public void mouseEntered(MouseEvent evt) {
		    button.setBackground(Color.BLACK);
		}
		public void mouseExited(MouseEvent evt){
			button.setBackground(new Color(102,102,255));
		}
	}
	static class myButton extends JButton{
		public myButton(int x, int y, Color color) {
			super();
			setModel(new FixedStateButtonModel());
			setRolloverEnabled(false);
			setMargin(new Insets(0,0,0,0));	
			setFocusable(false);
			setBackground(color);
			setForeground(Color.WHITE);
			setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			setPreferredSize(new Dimension(x,y));			
		}
	}
	private class DemonstratePane extends JTextArea{
		public DemonstratePane(int x, int y,Color color) {
			setOpaque(true);
			setBackground(color);
			setForeground(Color.WHITE);
			//TODO sortinfo from sorting
			setText(sortinfo);
			setEditable(false);
			setFocusable(false);
			setLineWrap(true);
			setWrapStyleWord(true);
			setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			setPreferredSize(new Dimension(x,y));
		}
	}

	public abstract JPanel main(int[] array);
	public abstract JPanel sub(int[] array);
	public abstract JPanel animation(int[] array);

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
