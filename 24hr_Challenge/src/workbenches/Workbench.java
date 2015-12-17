package workbenches;

import java.util.ArrayList;
import java.util.HashMap;

import components.Component;

public abstract class Workbench {

	protected Component product;
	protected HashMap<String, Integer> storedComponents;
	
	protected ArrayList<Workbench> outputTargets;
	
	public Workbench(){
		
	}
	
	public void setProduct(Component product){
		
	}
}
