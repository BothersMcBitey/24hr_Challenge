package components.toys;

import java.util.HashMap;

import components.materials.Stuffing;

public class TeddyBear extends Toy {

	private static HashMap<String, Integer> requiredComponents;
	private static boolean initialized = false;
	public static final String NAME = "Teddy Bear";
	
	public TeddyBear() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeComponent() {
		requiredComponents = new HashMap<String, Integer>();
		requiredComponents.put(Stuffing.NAME, 1);
		initialized = true;
	}

	@Override
	public HashMap<String, Integer> getRequiredComponents() {
		if(initialized){
			return requiredComponents;
		} else {
			initializeComponent();
			return requiredComponents;
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

}
