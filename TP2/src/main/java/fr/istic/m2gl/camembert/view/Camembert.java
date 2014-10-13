package fr.istic.m2gl.camembert.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import javax.swing.JComponent;

import fr.istic.m2gl.camembert.model.Model;

public class Camembert extends JComponent {

	private static final long serialVersionUID = 1L;

	private Model m;
	
	public Camembert(Model m) {
		this.m = m;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graph = (Graphics2D) g;
		
		Arc2D center = new Arc2D.Double(App.WIDTH/2-50, App.HEIGHT/2-50, 100, 100, 0, 360, Arc2D.PIE);
		graph.setColor(Color.BLUE);
		graph.fill(center);
		graph.draw(center);
	}
}
