package workbenches;

import java.awt.Color;
import java.awt.Graphics2D;

import components.Component;
import components.toys.Toy;

public class OutputBench extends Workbench {

	private int score = 0;
	private final int PENALTY = 50;
	//used to determine colour, 0 = none (Yellow), 1 = toy (green), 2 = other (red)
	private byte inputSinceUpdate = 0;
	private int ticksSinceUpdate = 0;
	
	public OutputBench(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
	}
	
	@Override
	public void addComponent(Component comp){
		ticksSinceUpdate = 0;
		if(comp instanceof Toy){
			score += ((Toy) comp).getScore();
			inputSinceUpdate = 1;
			System.out.println("Sucessfully output a " + comp.getName() + ", adding " + ((Toy) comp).getScore() + " points.");
		} else {
			score -= PENALTY;
			inputSinceUpdate = 2;
			System.out.println(comp.getName() + " isn't supposed to go in there, -" + PENALTY + " points.");
		}
		System.out.println("SCORE: " + score);
	}


	@Override
	public void updateGraphics() {
		Graphics2D g = img.createGraphics();		
		if(inputSinceUpdate == 0){
			g.setColor(Color.YELLOW);
		} else if(inputSinceUpdate == 1){
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		ticksSinceUpdate++;
		if(ticksSinceUpdate >= 50){
			inputSinceUpdate = 0;
		}
	}

	public int getScore() {
		return score;
	}
}
