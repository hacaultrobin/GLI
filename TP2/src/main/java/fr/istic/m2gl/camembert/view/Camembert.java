package fr.istic.m2gl.camembert.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.util.Collection;
import java.util.Random;

import javax.swing.JComponent;

import fr.istic.m2gl.camembert.model.Model;

public class Camembert extends JComponent {

	private static final long serialVersionUID = 1L;
	private Model m;
	private String title;
	private float totalValue;
	private Collection<String> fieldsName;
	
	public Camembert(Model m) {
		this.m = m;
		m.setCamembertChangedCmd(() -> loadData());
		
		loadData();		
	}
	
	private void loadData() {
		title = m.getTitle();
		totalValue = m.getTotalValues();
		fieldsName = m.getFieldsNames();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;
		
		float start = 0;
		float delta = 0;
		for (String fieldName : fieldsName) {
			float value = m.getValue(fieldName);
			delta = (360 * value / totalValue);
			
			Arc2D field = new Arc2D.Double(App.WIDTH/2-100, App.HEIGHT/2-100, 200, 200, start, delta, Arc2D.PIE);
			graph.setColor(new Color(new Random().nextInt()));
			graph.fill(field);
			graph.draw(field);

			graph.setColor(Color.GRAY);
			graph.setFont(new Font("Arial", Font.BOLD, 11));
			graph.drawString(fieldName, start, 10);
			
			start += delta;
		}

		Arc2D blank = new Arc2D.Double(App.WIDTH/2-65, App.HEIGHT/2-65, 130, 130, 0, 360, Arc2D.PIE);
		graph.setColor(Color.WHITE);
		graph.fill(blank);
		graph.draw(blank);
		
		Arc2D center = new Arc2D.Double(App.WIDTH/2-50, App.HEIGHT/2-50, 100, 100, 0, 360, Arc2D.PIE);
		graph.setColor(new Color(00,73,84));
		graph.fill(center);
		graph.draw(center);

		graph.setColor(Color.GRAY);
		graph.setFont(new Font("Arial", Font.BOLD, 11));
		graph.drawString(title, App.WIDTH/2, App.HEIGHT/2-10);

		graph.setColor(Color.WHITE);
		graph.drawString(totalValue+" €", App.WIDTH/2, App.HEIGHT/2+10);
		
	}
}
