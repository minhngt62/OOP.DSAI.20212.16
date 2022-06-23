import gui.CountingSortFrame;
import gui.MergeSortFrame;
import gui.RadixSortFrame;
import gui.Screen;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSortFrame ms = new MergeSortFrame();
		ms.setVisible(false);
		CountingSortFrame cs = new CountingSortFrame();
		cs.setVisible(false);
		RadixSortFrame rs = new RadixSortFrame();
		rs.setVisible(true);

	}

}
