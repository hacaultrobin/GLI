package fr.istic.m2gl.camembert.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;

import fr.istic.m2gl.camembert.model.Model;

public class CamembertView extends JComponent {

	private static final long serialVersionUID = 1L;
	
	private Model m;
	
	private String title;
	private float totalValue;
	private Collection<String> fieldsName;

	/* Objets graphique */
	private List<Arc2D> arcs = new ArrayList<>(); // Representation graphique des champs du camembert
	private Arc2D blank;
	
	public CamembertView(Model m) {
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
			
			Arc2D field = new Arc2D.Double(getWidth()/2-100, getHeight()/2-100, 200, 200, start, delta, Arc2D.PIE);
			graph.setColor(new Color(new Random().nextInt()));
			graph.fill(field);
			graph.draw(field);
			
			arcs.add(field);
			
			start += delta;
		}

		blank = new Arc2D.Double(getWidth()/2-65, getHeight()/2-65, 130, 130, 0, 360, Arc2D.PIE);
		graph.setColor(Color.WHITE);
		graph.fill(blank);
		graph.draw(blank);
		
		Arc2D center = new Arc2D.Double(getWidth()/2-50, getHeight()/2-50, 100, 100, 0, 360, Arc2D.PIE);
		graph.setColor(new Color(00,73,84));
		graph.fill(center);
		graph.draw(center);

		graph.setColor(Color.GRAY);
		graph.setFont(new Font("Arial", Font.BOLD, 11));
		graph.drawString(title, getWidth()/2-title.length()*3, getHeight()/2-10);

		graph.setColor(Color.WHITE);
		String total = totalValue+" €";
		graph.drawString(total, getWidth()/2-total.length()*3, getHeight()/2+10);
	}
	
	/**
	 * @param clickedPoint - position du click utilisateur
	 * @return la position de l'arc du camembert cliqué par l'utilisatuer dans la liste des "fieldsName"
	 */
	public int getIndexOfClickedField(Point2D clickedPoint) {
		if (blank.contains(clickedPoint)) {
			// Clicked point is on center of charts
			return -1;
		}

		for (int i = 0; i < arcs.size(); i++) {
			if (arcs.get(i).contains(clickedPoint)) {
				//arcs.get(i).setArc(getWidth()/2-100, getHeight()/2-100, 250, 250, arcs.get(i).getAngleStart(), arcs.get(i).getAngleExtent(), Arc2D.PIE);
				return i;
			}
		}
		
		return -1;
	}
	
}
