package workbenches;

import components.Component;
import components.toys.Toy;

public class OutputBench extends Workbench {

	private static int SCORE = 0;
	private static final int PENALTY = 50;
	
	public OutputBench() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addComponent(Component comp){
		if(comp instanceof Toy){
			SCORE += ((Toy) comp).getScore();
			System.out.println("Sucessfully output a " + comp.getName() + ", adding " + ((Toy) comp).getScore() + " points.");
		} else {
			SCORE -= PENALTY;
			System.out.println(comp.getName() + " isn't supposed to go in there, -" + PENALTY + " points.");
		}
		System.out.println("SCORE: " + SCORE);
	}
}
