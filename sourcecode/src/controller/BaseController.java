package controller;

import listeners.BackListener;
import listeners.ExitListener;
import listeners.HelpAboutListener;
import view.BaseScreen;


public class BaseController {
	BaseScreen window;
	public BaseController(BaseScreen window) {
		this.window = window;
	}	

	public HelpAboutListener helpButtonClicked(String name,String helpInfo) {
		return new HelpAboutListener(name,helpInfo);
	}
	public BackListener backButtonClicked() {
		return new BackListener();
	}
	public ExitListener exitButtonClicked() {
		return new ExitListener();
	}
}
