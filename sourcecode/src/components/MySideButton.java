package components;

import java.awt.Color;

import controller.SortingController;
import controller.SortingController.LeftSideBarBtnListener;

public class MySideButton extends MyButton{
	public MySideButton(int x, int y, Color color, String text,int alignment,SortingController controller) {
		super(x,y,color);
		setText(text);
		setHorizontalAlignment(alignment);
		addMouseListener(new HoverMouseAdapter(this));
		addActionListener(controller.new LeftSideBarBtnListener());
		setVisible(false);
	}
}
