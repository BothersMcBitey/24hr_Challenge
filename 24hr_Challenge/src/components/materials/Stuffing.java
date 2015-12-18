package components.materials;

import java.util.HashMap;

import components.Component;
import components.rawmaterials.Wool;

public class Stuffing extends Component {
	
	private static HashMap<String, Integer> requiredComponents;
	private static boolean initialized = false;
	public static final String NAME = "Stuffing";
	private static final int assemblyTime = 200;

	public Stuffing() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeComponent() {
		requiredComponents = new HashMap<String, Integer>();
		requiredComponents.put(Wool.NAME, 1);
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

	@Override
	public int getAssemblyTime() {
		return assemblyTime;
	}
}
