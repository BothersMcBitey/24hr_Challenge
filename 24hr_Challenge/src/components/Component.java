package components;

import java.util.HashMap;

public abstract class Component {

	public abstract void initializeComponent();
	
	public abstract HashMap<String, Integer> getRequiredComponents();
	
	public abstract String getName();
}
