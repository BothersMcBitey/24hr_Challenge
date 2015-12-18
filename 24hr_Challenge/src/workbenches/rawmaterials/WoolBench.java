package workbenches.rawmaterials;

import java.awt.Color;
import java.awt.Graphics2D;

import components.rawmaterials.Wool;

public class WoolBench extends RawMaterialBench {
	
	public WoolBench(int x, int y) {
		super("Wool Shearer", x, y, 40, 40);
		product = new Wool();
	}
	
	@Override
	public void updateGraphics(){
		Graphics2D g = img.createGraphics();
		if(!assembling){
			g.setColor(Color.RED);
		} else {
			float prog = (float) timeAssembling / (float) product.getAssemblyTime();
			int colorOffset = (int) (prog*255);
			System.out.println(colorOffset);
			g.setColor(new Color(255 - (colorOffset), colorOffset , 0, 255));
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
	}
}
