package main;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import workbenches.Workbench;

public class Conveyor extends JPanel{

	private BufferedImage img;
	private JLabel imglbl;
	
	private Workbench source;
	private Workbench destination;
	
	public Conveyor(Workbench source, Workbench destination){
		this.source = source;
		this.destination = destination;
	}

	public Workbench getSource() {
		return source;
	}

	public Workbench getDestination() {
		return destination;
	}
	
	public void setContent(int x, int y, BufferedImage img){
		setOpaque(false);
		setBounds(x, y, img.getWidth(), img.getHeight());
		this.img = img;
		imglbl = new JLabel(new ImageIcon(this.img));
		imglbl.setBounds(0, 0, img.getWidth(), img.getHeight());
		add(imglbl);
	}
}
