package workbenches.toys;

import components.materials.Stuffing;
import components.toys.TeddyBear;
import workbenches.Workbench;

public class TeddyBench extends Workbench {

	public TeddyBench(int x, int y) {
		super("Teddy Builder", x, y, 40, 40);
		product = new TeddyBear();
	}
}
