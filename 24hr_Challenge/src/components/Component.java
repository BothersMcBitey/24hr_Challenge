package components;

import java.util.HashMap;

import main.Entity;

public abstract class Component{

	public Component(String name, int x, int y, int width, int height) {
//		super(name, x, y, width, height);
	}
	
	public Component(){
		
	}

	public abstract void initializeComponent();
	
	public abstract HashMap<String, Integer> getRequiredComponents();
	
	public abstract String getName();
	
	/**
	 * in milis
	 * @return
	 */
	public abstract int getAssemblyTime();
}
