package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import view.BaseScreen.DemonstratePane;

public class SortingController {
	public static class DemonstrateListener implements ActionListener{
		DemonstratePane demonstratePane;
		public DemonstrateListener(DemonstratePane demonstratePane) {
			super();
			this.demonstratePane = demonstratePane;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JButton )e.getSource()).getText().equals("<")) {
				((JButton )e.getSource()).setText(">");
				demonstratePane.setVisible(true);
			}
			else {
				((JButton )e.getSource()).setText("<");
				demonstratePane.setVisible(false);
			}
		}
	}
	public static class BtnCreateListener implements ActionListener{
		JButton btnRandom;
		JLabel A;
		JTextField inputArrayField;
		JButton btnGo;
		public BtnCreateListener(JButton btnRandom, JLabel A, JTextField inputArrayField, JButton btnGo) {
			super();
			this.A =A;
			this.btnGo = btnGo;
			this.btnRandom = btnRandom;
			this.inputArrayField = inputArrayField;
		}
		public void actionPerformed(ActionEvent e) {
			btnRandom.setVisible(true);
			A.setVisible(true);
			inputArrayField.setVisible(true);
			btnGo.setVisible(true);
		}
	}
	public static class GenDataListener implements ActionListener{
		JButton btnRandom;
		JLabel A;
		JTextField inputArrayField; 
        JButton btnGo;
        JButton btnCreate;
        JButton btnSort;
		public GenDataListener(JButton btnRandom, JLabel A, JTextField inputArrayField, 
				               JButton btnGo,JButton btnCreate, JButton btnSort) {
			super();
			this.btnRandom =btnRandom;
			this.A=A; 
			this.inputArrayField= inputArrayField; 
            this.btnGo = btnGo;
            this.btnCreate = btnCreate;
            this.btnSort =btnSort;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JButton )e.getSource()).getText().equals(">")) {
				((JButton )e.getSource()).setText("<");
				btnCreate.setVisible(true);
				btnSort.setVisible(true);
			}
			else {
				((JButton )e.getSource()).setText(">");
				btnCreate.setVisible(false);
				btnSort.setVisible(false);
				btnRandom.setVisible(false);
				A.setVisible(false);
				inputArrayField.setVisible(false);
				btnGo.setVisible(false);
			}
		}
	}
}
