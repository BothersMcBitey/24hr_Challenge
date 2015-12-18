package workbenches.rawmaterials;

import java.awt.Color;
import java.awt.Graphics2D;

import components.rawmaterials.Wool;

public class WoolBench extends RawMaterialBench {
	
	public WoolBench(int x, int y) {
		super("Wool Shearer", x, y, 40, 40);
		product = new Wool();
	}
}
