package components.rawmaterials;

public class Wool extends RawMaterial {
	
	public static final String NAME = "Wool";
	private static final int assemblyTime = 100;
	
	public Wool(){
		//stub
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
