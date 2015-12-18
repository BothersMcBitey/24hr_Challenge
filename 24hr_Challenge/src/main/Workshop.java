package main;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import workbenches.OutputBench;
import workbenches.Workbench;
import workbenches.materials.StuffingBench;
import workbenches.rawmaterials.WoolBench;
import workbenches.toys.TeddyBench;

public class Workshop implements MouseInputListener{

	private JFrame frame;
	private GraphicsPane gPane;
	private Thread clock;
	
	private ArrayList<Workbench> workbenches;
	private final OutputBench FinishedToys;
	
	public Workshop(){
		frame = new JFrame("Santa's Sweatshop");
		frame.setLocation(20, 20);
		frame.setUndecorated(true);
		frame.setMinimumSize(new Dimension(900, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		
		gPane = new GraphicsPane(frame.getMinimumSize(), this);
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
		FinishedToys.addMouseListener(this);
		FinishedToys.addMouseMotionListener(this);
		
		TeddyBench teddyMaker = new TeddyBench(400, 300);
//		gPane.addConveyor(teddyMaker.addOutputTarget(FinishedToys));
		add(teddyMaker);
		
		StuffingBench stuffingMaker = new StuffingBench(200, 150);
//		gPane.addConveyor(stuffingMaker.addOutputTarget(teddyMaker));
		add(stuffingMaker);
		WoolBench woolMaker = new WoolBench(50, 400);
//		gPane.addConveyor(woolMaker.addOutputTarget(stuffingMaker));
		add(woolMaker);
		
		frame.setVisible(true);
		clock.start();
	}
	
	private void add(Workbench bench){
		workbenches.add(bench);
		gPane.addToForeground(bench);
		bench.addMouseListener(this);
		bench.addMouseMotionListener(this);
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
	
	//listener bit ================================================================================
	
	private boolean addingConveyor = false;
	private Workbench source;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getX() + ", " + arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource() instanceof Workbench){
			addingConveyor = true;
			source = (Workbench) arg0.getSource();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		gPane.drawLine(source.getCenter(), arg0.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
