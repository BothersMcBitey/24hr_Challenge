package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import workbenches.OutputBench;
import workbenches.Workbench;
import workbenches.materials.StuffingBench;
import workbenches.rawmaterials.WoolBench;
import workbenches.toys.TeddyBench;

public class Workshop {

	private JFrame frame;
	private GraphicsPane gPane;
	private Thread clock;
	
	private ArrayList<Workbench> workbenches;
	private final OutputBench FinishedToys;
	
	public Workshop(){
		frame = new JFrame("Santa's Sweatshop");
		frame.setLocation(20, 20);
		frame.setUndecorated(true);
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gPane = new GraphicsPane(frame.getMinimumSize());
		frame.setContentPane(gPane);
		
		clock = new Thread(){
			public void run(){
				System.out.println("Starting the sweatshop.");
				while(true){
					processTurn();
				}
			}
		};
		
		FinishedToys = new OutputBench("Output", 600, 200, 40, 40); 
		workbenches = new ArrayList<Workbench>();
		gPane.addToForeground(FinishedToys);
		
		TeddyBench teddyMaker = new TeddyBench(400, 300);
		teddyMaker.addOutputTarget(FinishedToys);
		add(teddyMaker);
		
		StuffingBench stuffingMaker = new StuffingBench(200, 150);
		stuffingMaker.addOutputTarget(teddyMaker);
		add(stuffingMaker);
		WoolBench woolMaker = new WoolBench(50, 400);
		woolMaker.addOutputTarget(stuffingMaker);
		add(woolMaker);
		
		frame.setVisible(true);
		clock.start();
	}
	
	private void add(Workbench bench){
		workbenches.add(bench);
		gPane.addToForeground(bench);
	}
	
	private void processTurn(){
		for(Workbench wb : workbenches){
			if(!wb.isAssembling()){
				wb.AssembleProduct();
			} else {
				wb.checkProgress();
			}
		}
		gPane.updateGraphics(FinishedToys.getScore());
		frame.repaint();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
