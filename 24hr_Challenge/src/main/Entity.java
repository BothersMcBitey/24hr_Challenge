package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public abstract class Entity extends JLayeredPane {

	protected BufferedImage img;
	protected JLabel imgLbl, nameLbl;
	
	public Entity(String name, int x, int y, int width, int height) {
		setLayout(null);
		setOpaque(false);
		setBounds(x, y, width, height);
		
		nameLbl = new JLabel(name);
		nameLbl.setBounds(0, 0, width, height);
		nameLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
		g.dispose();
		imgLbl = new JLabel(new ImageIcon(img));
		imgLbl.setBounds(0, 0, width, height);
		
		add(imgLbl, new Integer(0));
		add(nameLbl, new Integer(1));
	}
	
	public abstract void updateGraphics();
}
