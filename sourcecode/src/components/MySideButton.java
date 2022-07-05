package components;

import java.awt.Color;

import controller.SortingController;

public class MySideButton extends MyButton{
	public MySideButton(int x, int y, Color color, String text,int alignment,SortingController controller) {
		super(x,y,color);
		setText(text);
		setHorizontalAlignment(alignment);
		addMouseListener(new HoverMouseAdapter(this));
		addActionListener(controller.leftSideButtonClicked());
		setVisible(false);
	}
}
