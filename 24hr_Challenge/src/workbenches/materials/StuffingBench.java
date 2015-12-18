package workbenches.materials;

import components.materials.Stuffing;
import workbenches.Workbench;

public class StuffingBench extends Workbench {

	public StuffingBench(int x, int y) {
		super("Stuffing Spinner", x, y, 40, 40);
		product = new Stuffing();
	}
}
