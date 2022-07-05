package controller;

import listeners.SortListener;

public class HomeController{
	public SortListener sortSelection(String name) {
		return new SortListener(name);
	}
}
