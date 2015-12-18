package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GraphicsPane extends JLayeredPane {

	private BufferedImage backgroundCanvas;
	private JPanel foregroundPanel;
	private BufferedImage UICanvas;
	private JPanel UIPanel;
	
	private ArrayList<Entity> entities;
	
	private JLabel bgLbl, uiLbl, score;
	
	public GraphicsPane(Dimension size) {
		entities = new ArrayList<>();
		setMinimumSize(size);
		
		backgroundCanvas = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = backgroundCanvas.createGraphics();
		g.setColor(new Color(200, 130, 0, 255));
		g.fillRect(0, 0, size.width, size.height);
		bgLbl = new JLabel(new ImageIcon(backgroundCanvas));
		bgLbl.setBounds(0, 0, size.width, size.height);
		add(bgLbl, new Integer(0));
		
		foregroundPanel = new JPanel(null);
		foregroundPanel.setBounds(0, 0, size.width, size.height);
		foregroundPanel.setOpaque(false);
		add(foregroundPanel, new Integer(1));
		
		UICanvas = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
		g = UICanvas.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 30);
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
}
