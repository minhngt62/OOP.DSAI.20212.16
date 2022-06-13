import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Visualizer extends JLayeredPane {
	private String sortType;
	private JPanel main;
	private JPanel sub;
	private JPanel animation;
	
	public Visualizer(String type)  {
		super();
		this.sortType = type;

	}
	
	public JPanel main_visualize(int[] array) {
		main =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
			}
		};
		return main;
	}
	public JPanel sub_visualize(int[] array) {
		sub =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
			}
		};
		return sub;
	}
	public JPanel animation_visualize(int[] array) {
		animation =  new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				
			}
		};
		return animation;
	}
	
	public void visualize(int[] array) {
		main_visualize(array).setVisible(false);
		sub_visualize(array).setVisible(false);
		animation_visualize(array).setVisible(false);
		this.add(main,JLayeredPane.DEFAULT_LAYER );
		this.add(sub,JLayeredPane.DEFAULT_LAYER);
		this.add(animation, JLayeredPane.PALETTE_LAYER);
		main_visualize(array).setVisible(true);
		sub_visualize(array).setVisible(true);
		main_visualize(array).setVisible(false);
		animation_visualize(array).setVisible(true);
		sub_visualize(array).setVisible(false);
		animation_visualize(array).setVisible(false);
	}
}
