package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import workbenches.Workbench;

public class GraphicsPane extends JLayeredPane {

	private BufferedImage backgroundCanvas;
	private JPanel foregroundPanel;
	private BufferedImage UICanvas;
	private JPanel UIPanel;
	
	private ArrayList<Entity> entities;
	private ArrayList<Conveyor> conveyors;
	
	private JLabel bgLbl, uiLbl, score;

	private final int SIDEBAR_WIDTH = 200;
	
	public GraphicsPane(Dimension size, MouseInputListener listener) {
		entities = new ArrayList<>();
		conveyors = new ArrayList<>();
		setMinimumSize(size);
		
		backgroundCanvas = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = backgroundCanvas.createGraphics();
		g.setColor(new Color(140, 85, 0, 255));
		g.fillRect(0, 0, SIDEBAR_WIDTH, size.height);
		g.setColor(new Color(200, 130, 0, 255));
		g.fillRect(SIDEBAR_WIDTH, 0, size.width - SIDEBAR_WIDTH, size.height);
		g.setColor(Color.BLACK);
		g.drawLine(SIDEBAR_WIDTH, 0, SIDEBAR_WIDTH, size.height);
		g.drawRect(0, 0, size.width - 1, size.height - 1);
		bgLbl = new JLabel(new ImageIcon(backgroundCanvas));
		bgLbl.setBounds(0, 0, size.width, size.height);
		add(bgLbl, new Integer(0));
		
		foregroundPanel = new JPanel(null);
		foregroundPanel.setBounds(SIDEBAR_WIDTH, 0, size.width - SIDEBAR_WIDTH, size.height);
		foregroundPanel.setOpaque(false);
		add(foregroundPanel, new Integer(1));
		
		UICanvas = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
		g = UICanvas.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 30);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 100, 30);
		g.dispose();
		uiLbl = new JLabel(new ImageIcon(UICanvas));
		uiLbl.setBounds(0, 0, size.width, size.height);
		add(uiLbl, new Integer(2));
		
		UIPanel = new JPanel(null);
		UIPanel.setOpaque(false);
		UIPanel.setBounds(0, 0, size.width, size.height);
		score = new JLabel("Score: " + 0);
		score.setBounds(0, 0, 100, 30);
		UIPanel.add(score);
		add(UIPanel, new Integer(3));
	}

	public void addToForeground(Entity e){
		entities.add(e);
		foregroundPanel.add(e);
	}
	
	public void updateGraphics(int score){
		this.score.setText("Score: " + score);
		for(Entity e : entities){
			e.updateGraphics();
		}
	}
	
	public void addConveyor(Conveyor conveyor){
		conveyors.add(conveyor);
		Workbench source = conveyor.getSource();
		Workbench destination = conveyor.getDestination();
		System.out.println(source.getName() + " -> " + destination.getName());
		int x;
		int width = destination.getX() - (source.getX() + source.getWidth());
		if(width < 0){
			width *= -1;
			x = destination.getX() + destination.getWidth();
		} else {
			x = source.getX() + source.getWidth();
		}
		int y;
		int height = (destination.getY() +destination.getHeight()/2) - (source.getY() + source.getHeight()/2);
		if(height < 0){
			height *= -1;
			y = destination.getY() + destination.getHeight()/2;
		} else {
			y = source.getY() + source.getHeight()/2;
		}
		System.out.println(x + "," + y + "," + width + "," + height);
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.BLACK);
		if(destination.getY() < source.getY()){
			g.drawLine(0, height, width, 0);
		} else {
			g.drawLine(0, 0, width, height);
		}
		g.dispose();
		conveyor.setContent(x, y, img);
		foregroundPanel.add(conveyor);
	}

	public void drawLine(Point center, Point point) {
		Graphics2D g = UICanvas.createGraphics();
//		g.drawLine(center.x + SIDE, arg1, arg2, arg3);
	}
}
