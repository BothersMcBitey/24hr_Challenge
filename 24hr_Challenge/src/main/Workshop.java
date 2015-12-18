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
	private Thread clock;
	
	private ArrayList<Workbench> workbenches;
	private final OutputBench FinishedToys;
	
	public Workshop(){
		frame = new JFrame("Santa's Sweatshop");
		frame.setLocation(20, 20);
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		clock = new Thread(){
			public void run(){
				System.out.println("Starting the sweatshop.");
				while(true){
					processTurn();
				}
			}
		};
		
		FinishedToys = new OutputBench(); 
		workbenches = new ArrayList<Workbench>();
		
		TeddyBench teddyMaker = new TeddyBench();
		teddyMaker.addOutputTarget(FinishedToys);
		workbenches.add(teddyMaker);
		StuffingBench stuffingMaker = new StuffingBench();
		stuffingMaker.addOutputTarget(teddyMaker);
		workbenches.add(stuffingMaker);
		WoolBench woolMaker = new WoolBench();
		woolMaker.addOutputTarget(stuffingMaker);
		workbenches.add(woolMaker);
		
		frame.setVisible(true);
		clock.start();
	}
	
	private void processTurn(){
		for(Workbench wb : workbenches){
			if(!wb.isAssembling()){
				wb.AssembleProduct();
			} else {
				wb.checkProgress();
			}
		}
		frame.validate();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
