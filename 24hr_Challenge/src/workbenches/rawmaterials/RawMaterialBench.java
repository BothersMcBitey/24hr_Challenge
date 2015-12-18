package workbenches.rawmaterials;

import workbenches.Workbench;

public abstract class RawMaterialBench extends Workbench {
	
	public RawMaterialBench(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void AssembleProduct(){
		Assemble();
	}
}
